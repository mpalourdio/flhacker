/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mpalourdio.projects.flhacker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.mpalourdio.projects.flhacker.FlhackerApplication.TMP_DIR;

public class Img2Ascii {

    static final String TMP_ASCIIART_TXT = TMP_DIR + "/asciiart.txt";
    private final PrintWriter prntwrt;
    private final FileWriter filewrt;

    public Img2Ascii() throws IOException {
        filewrt = new FileWriter(TMP_ASCIIART_TXT, false);
        prntwrt = new PrintWriter(filewrt);
    }

    public void convertToAscii(String imgname) throws IOException {
        var img = ImageIO.read(new File(imgname));

        for (var i = 0; i < img.getHeight(); i++) {
            for (var j = 0; j < img.getWidth(); j++) {
                var pixcol = new Color(img.getRGB(j, i));
                var pixval = (pixcol.getRed() * 0.30) + (pixcol.getBlue() * 0.59) + (pixcol.getGreen() * 0.11);
                print(strChar(pixval));
            }
            prntwrt.println("");
            prntwrt.flush();
            filewrt.flush();
        }
    }

    private String strChar(double g) {
        var str = " ";
        if (g >= 240) {
            str = " ";
        } else if (g >= 210) {
            str = ".";
        } else if (g >= 190) {
            str = "*";
        } else if (g >= 170) {
            str = "+";
        } else if (g >= 120) {
            str = "^";
        } else if (g >= 110) {
            str = "&";
        } else if (g >= 80) {
            str = "8";
        } else if (g >= 60) {
            str = "#";
        } else {
            str = "@";
        }
        return str;
    }

    private void print(String str) throws IOException {
        prntwrt.print(str);
        prntwrt.flush();
        filewrt.flush();
    }
}
