import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.xml.bind.DatatypeConverter;
import javax.imageio.ImageIO;

public class Image2Binary {
  public static void bmpFunction(String input){
      Path path = Paths.get(input);
      File ip = new File(input);
      System.out.println(path.getFileName());
          try {BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ip));
            // BufferedWriter bw = new BufferedWriter(new FileWriter("bmp/" + path.getFileName() + ".jpg"));
            // System.out.println(bis.read());
        
        int[] pix=new int[1280*1024];
      for(int i=0; i<pix.length; i++) {
        int b = bis.read();
        pix[i]=0xff000000|b;
      }
      BufferedImage bim=new BufferedImage(1280, 1024, BufferedImage.TYPE_INT_RGB);
      bim.setRGB(0, 0, 1280, 1024, pix, 0, 1280);
        try {
          ImageIO.write(bim, "bmp", new File("bmp/" + path.getFileName() +".bmp"));
        } catch (IOException ex) { ex.printStackTrace(); }
  }catch(IOException e){System.err.println(e);}
  }

  public static void main(String args[]) {
    try {
      Files.walk(Paths.get("images/")).forEach(filePath -> {
        if (Files.isRegularFile(filePath)) {
            String f_name = filePath.getFileName().toString();
            System.out.println(f_name);
            String f_nameWithoutExt = f_name.replaceFirst("[.][^.]+$", "");
            File input = new File("images/" + f_name);
            File output = new File("binary/binary_" + f_nameWithoutExt + ".txt");
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
              bmpFunction(output.toString());
        } catch (IOException e) {
            System.err.println(e);
          }
        }
      });
    } catch(IOException e) {
        e.printStackTrace();
      }
  }
}