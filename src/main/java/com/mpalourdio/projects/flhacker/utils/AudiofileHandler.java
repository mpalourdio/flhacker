/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mpalourdio.projects.flhacker.utils;

import io.github.seujorgenochurras.image.ascii.AsciiParser;
import io.github.seujorgenochurras.image.ascii.ParserBuilder;
import io.github.seujorgenochurras.image.ascii.algorithm.pixel.bright.Algorithms;
import io.github.seujorgenochurras.image.ascii.algorithm.pixel.color.DefaultColorType;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AudiofileHandler {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String TMP_RESIZED_ARTWORK = TMP_DIR + "/resized.jpg";
    private static final int TARGET_SIZE = 80;
    private static final File TMP_RESIZED_FILE = new File(TMP_RESIZED_ARTWORK);

    public static void setUp() {
        FileUtils.deleteQuietly(TMP_RESIZED_FILE);
    }

    public static void tearDown() {
        setUp();
    }

    public static void extractResizeSaveArtwork(String audioFilePath)
            throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        var audioFile = new File(audioFilePath);
        var extractedArtwork = AudioFileIO.read(audioFile)
                .getTag()
                .getFirstArtwork()
                .getImage();

        var scaledArtwork = ((BufferedImage) extractedArtwork).getScaledInstance(TARGET_SIZE, TARGET_SIZE, Image.SCALE_DEFAULT);
        var resized = new BufferedImage(TARGET_SIZE, TARGET_SIZE, BufferedImage.TYPE_INT_RGB);
        resized.getGraphics().drawImage(scaledArtwork, 0, 0, null);

        ImageIO.write(resized, "png", TMP_RESIZED_FILE);
    }

    public static void generateAsciiArt() {
        var symbols = new String[]{" ", ".", "-", "I", "W", "@"};
        var parserConfig = ParserBuilder.startBuild()
                .parserAlgorithm(Algorithms.HUMAN_EYE_ALGORITHM)
                .scaled()
                .height(TARGET_SIZE)
                .width(TARGET_SIZE)
                .getScale()
                .symbols(symbols)
                .colorAlgorithm(DefaultColorType.ANSI)
                .build();

        var asciiArt = AsciiParser.parse(TMP_RESIZED_ARTWORK, parserConfig);

        System.out.println(asciiArt);
    }
}
