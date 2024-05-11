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


class CliHandler(args: Array<String>) {
    private val fileCmdLongOption: String = "file"
    var filePath: String = ""
        private set

    init {
        val options = Options()
        val input = Option(
            "f",
            fileCmdLongOption,
            true,
            "The audio file path which contains the artwork to print"
        )
        input.isRequired = true
        options.addOption(input)

        val parser: CommandLineParser = DefaultParser()
        val formatter = HelpFormatter()
        val cmd = { o: Options, a: Array<String> -> parser.parse(o, a) }

        try {
            filePath = cmd(options, args).getOptionValue(fileCmdLongOption)
        } catch (e: ParseException) {
            System.err.println(e.message)
            formatter.printHelp("Specify the file path like below", options)
        }
    }
}
