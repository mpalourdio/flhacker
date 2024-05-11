/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mpalourdio.projects.flhacker.utils

import org.apache.commons.cli.*
import kotlin.system.exitProcess

object CliHandler {
    const val FILE_CMD_LONG_OPTION: String = "file"

    fun run(args: Array<String>): CommandLine {
        val options = Options()
        val input =
            Option("f", FILE_CMD_LONG_OPTION, true, "The audio file path which contains the artwork to print")

        input.isRequired = true
        options.addOption(input)

        val parser: CommandLineParser = DefaultParser()
        val formatter = HelpFormatter()
        val cmd: CommandLine

        try {
            cmd = parser.parse(options, args)
        } catch (e: ParseException) {
            println(e.message)
            formatter.printHelp("cli", options)
            exitProcess(1)
        }

        return cmd
    }
}
