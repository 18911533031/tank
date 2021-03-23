package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ImageUtilTest {

    @Test
    private void test(){
        try {
            BufferedImage image = ImageIO.read(new File("E:\\GitHub\\tank\\src\\images\\bulletD.gif"));
            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageUtilTest.class.getClassLoader().getResourceAsStream("images\\bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}