package helperMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFix {

    //rotate
    public static BufferedImage getRotatedImg(BufferedImage img, int rotateAngle){
        int w= img.getWidth();
        int h= img.getHeight();

        BufferedImage newImage = new BufferedImage(w,h,img.getType());
        Graphics2D graphics2D = newImage.createGraphics();

        graphics2D.rotate(Math.toRadians(rotateAngle),w/2,h/2); //rotate at center axis.
        graphics2D.drawImage(img,0,0,null);
        graphics2D.dispose();

        return newImage;

    }

    //stacking sprites

    public static BufferedImage buildImage(BufferedImage[] images) {

        int w= images[0].getWidth();
        int h= images[0].getHeight();

        BufferedImage newImage = new BufferedImage(w,h,images[0].getType());
        Graphics2D graphics2D = newImage.createGraphics();

        for(BufferedImage image: images) {

            graphics2D.drawImage(image, 0, 0, null);
        }
             graphics2D.dispose();

          return newImage;
        }


        //Rotate top layer image and animate
    public static BufferedImage[] getBuildRotateImg(BufferedImage [] images,BufferedImage secondImage, int rotateAngle){
        int w= images[0].getWidth();
        int h= images[0].getHeight();
        BufferedImage[] array=new BufferedImage[images.length];

        for(int i=0; i<images.length; i++) {
            BufferedImage newImage = new BufferedImage(w, h, images[0].getType());
            Graphics2D graphics2D = newImage.createGraphics();
            //draw water image
            graphics2D.drawImage(images[i], 0, 0, null);
            //do the rotation for the water
            graphics2D.rotate(Math.toRadians(rotateAngle), w / 2, h / 2);
            //draw second image
            graphics2D.drawImage(secondImage, 0, 0, null);

            graphics2D.dispose();

            array[i] = newImage;
        }

        return array;
    }

}
