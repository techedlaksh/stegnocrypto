import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
// import java.util.Base64;
import javax.xml.bind.DatatypeConverter;
import javax.imageio.ImageIO;

// import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Image2Base64 {
 public static void main(String args[]) {
   try {
     BufferedImage image = ImageIO.read(new File("photo675614861676357572.jpg"));
     // ObjectToByteArray(image);
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
     ImageIO.write(image, "png", baos);
     // String encryptedValue = new String(Base64.getEncoder().encode(baos));
     String base64String = DatatypeConverter.printBase64Binary(baos.toByteArray());
	 byte[] bytearray = DatatypeConverter.parseBase64Binary(base64String);
	 System.out.println(base64String);
     // String encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
     // System.out.println(DatatypeConverter.parseBase64Binary(encodedImage));
   } catch (Exception e) {
     e.printStackTrace();
   }
 }
}