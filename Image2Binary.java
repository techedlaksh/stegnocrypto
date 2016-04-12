import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.xml.bind.DatatypeConverter;
import javax.imageio.ImageIO;


public class Image2Binary {
 public static void main(String args[]) {
      File input= new File("images/pic0.jpg");
      File output = new File("binary_pic0.txt");

    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input));
         BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
        int read;
        while ((read=bis.read()) != -1) {
              String text = Integer.toString(read,2);
              while (text.length() < 8) {
                    text="0"+text;
              }
              bw.write(text);
              bw.write("\n");

        }            
    } catch (IOException e) {
            System.err.println(e);
    }
 }
}