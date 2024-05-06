/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mpalourdio.projects.flhacker.utils

import io.github.seujorgenochurras.image.ascii.AsciiParser
import io.github.seujorgenochurras.image.ascii.ParserBuilder
import io.github.seujorgenochurras.image.ascii.algorithm.pixel.bright.Algorithms
import io.github.seujorgenochurras.image.ascii.algorithm.pixel.color.DefaultColorType
import org.apache.commons.io.FileUtils
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.exceptions.CannotReadException
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException
import org.jaudiotagger.tag.TagException
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object AudiofileHandler {
    private val TMP_DIR: String = System.getProperty("java.io.tmpdir")
    private val TMP_RESIZED_ARTWORK = "$TMP_DIR/resized.jpg"
    private const val TARGET_SIZE = 80
    private val TMP_RESIZED_FILE = File(TMP_RESIZED_ARTWORK)

    @JvmStatic
    fun setUp() {
        FileUtils.deleteQuietly(TMP_RESIZED_FILE)
    }

    @JvmStatic
    fun tearDown() {
        setUp()
    }

    @JvmStatic
    @Throws(
        CannotReadException::class,
        TagException::class,
        InvalidAudioFrameException::class,
        ReadOnlyFileException::class,
        IOException::class
    )
    fun extractResizeSaveArtwork(audioFilePath: String?) {
        val audioFile = File(audioFilePath)
        val extractedArtwork = AudioFileIO.read(audioFile)
            .tag
            .firstArtwork
            .image

        val scaledArtwork =
            (extractedArtwork as BufferedImage).getScaledInstance(TARGET_SIZE, TARGET_SIZE, Image.SCALE_DEFAULT)
        val resized = BufferedImage(TARGET_SIZE, TARGET_SIZE, BufferedImage.TYPE_INT_RGB)
        resized.graphics.drawImage(scaledArtwork, 0, 0, null)

        ImageIO.write(resized, "png", TMP_RESIZED_FILE)
    }

    @JvmStatic
    fun generateAsciiArt() {
        val symbols = arrayOf(" ", ".", "-", "I", "W", "@")
        val parserConfig = ParserBuilder.startBuild()
            .parserAlgorithm(Algorithms.HUMAN_EYE_ALGORITHM)
            .scaled()
            .height(TARGET_SIZE)
            .width(TARGET_SIZE)
            .scale
            .symbols(*symbols)
            .colorAlgorithm(DefaultColorType.ANSI)
            .build()

        val asciiArt = AsciiParser.parse(TMP_RESIZED_ARTWORK, parserConfig)

        println(asciiArt)
    }
}
