/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mpalourdio.projects.flhacker

import com.mpalourdio.projects.flhacker.utils.AudiofileHandler
import com.mpalourdio.projects.flhacker.utils.CliHandler
import org.apache.commons.cli.CommandLine
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FlhackerApplication {

    @Bean
    fun run(): CommandLineRunner {
        return CommandLineRunner { args ->
            val cmd: CommandLine = CliHandler.run(args)
            val audiofileHandler = AudiofileHandler(cmd.getOptionValue(CliHandler.FILE_CMD_LONG_OPTION))
            try {
                audiofileHandler.setUp()
                audiofileHandler.extractResizeSaveArtwork()
                audiofileHandler.generateAsciiArt()
            } catch (e: Exception) {
                println(e.message)
            }
            finally {
                audiofileHandler.tearDown()
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<FlhackerApplication>(*args)
}
