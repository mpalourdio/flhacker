/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mpalourdio.projects.flhacker;

import com.mpalourdio.projects.flhacker.utils.AudiofileHandler;
import com.mpalourdio.projects.flhacker.utils.CliHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlhackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlhackerApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            AudiofileHandler.setUp();
            var cmd = CliHandler.run(args);
            try {
                AudiofileHandler.extractResizeSaveArtwork(cmd.getOptionValue(CliHandler.FILE_CMD_LONG_OPTION));
                AudiofileHandler.generateAsciiArt();
            } finally {
                AudiofileHandler.tearDown();
            }
        };
    }
}
