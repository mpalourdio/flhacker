/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mpalourdio.projects.flhacker;

import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class FlhackerApplication {

    static final String TMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String TMP_RESIZED_ARTWORK = TMP_DIR + "/resized.jpg";
    private static final int TARGET_SIZE = 80;
    public static final String FILE_CMD_LONG_OPTION = "file";

    public static void main(String[] args) throws IOException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        SpringApplication.run(FlhackerApplication.class, args);

        var options = new Options();
        var input = new Option("f", FILE_CMD_LONG_OPTION, true, "input file path which containe the artwork to print");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        var formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        var resizedFile = new File(TMP_RESIZED_ARTWORK);

        try {
            var audioFile = new File(cmd.getOptionValue(FILE_CMD_LONG_OPTION));
            var extractedArtwork = AudioFileIO.read(audioFile)
                    .getTag()
                    .getFirstArtwork()
                    .getImage();

            var scaledArtwork = ((BufferedImage) extractedArtwork).getScaledInstance(TARGET_SIZE, TARGET_SIZE, Image.SCALE_DEFAULT);
            var resized = new BufferedImage(TARGET_SIZE, TARGET_SIZE, BufferedImage.TYPE_INT_RGB);
            resized.getGraphics().drawImage(scaledArtwork, 0, 0, null);

            ImageIO.write(resized, "png", resizedFile);

            var img2Ascii = new Img2Ascii();
            img2Ascii.convertToAscii(TMP_RESIZED_ARTWORK);

            System.out.println(Files.readString(Path.of(Img2Ascii.TMP_ASCIIART_TXT)));
        } finally {
            FileUtils.deleteQuietly(new File(Img2Ascii.TMP_ASCIIART_TXT));
            FileUtils.deleteQuietly(resizedFile);
        }
    }
}
