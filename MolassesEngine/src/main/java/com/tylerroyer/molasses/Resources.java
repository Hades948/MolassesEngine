// This class features resource scaling code found here: https://blog.idrsolutions.com/2019/05/image-scaling-in-java/
package com.tylerroyer.molasses;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Toolkit;
import java.util.HashMap;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Helper class for resource IO.
 */
public class Resources {
    private static HashMap<String, BufferedImage> graphical;
    private static final String GRAPHICAL_PATH = "/res/";
    public static double scaleX, scaleY;

    public static void init() {
        Resources.graphical = new HashMap<>();
        
        double userScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double userScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Resources.scaleX = userScreenWidth / Config.devScreenWidth;
        Resources.scaleY = userScreenHeight / Config.devScreenHeight;
    }

    /**
     * Clear resources from the heap.
     */
    static void unloadAllResources() {
        graphical.clear();
    }

    /**
     * Loads a graphical image onto the heap.
     * @param name The name of the image file.
     * @return A pointer to the loaded image for convenience.
     */
    public static BufferedImage loadGraphicalImage(String name) {
        BufferedImage image = null;
        try {
            image = scaleImage(ImageIO.read(Resources.class.getResourceAsStream(GRAPHICAL_PATH + name)));
            graphical.put(name, image);
        } catch (IOException e) {e.printStackTrace();}

        return image;
    }

    static BufferedImage loadEngineGraphicalImage(String name) {
        BufferedImage image = null;
        try {
            image = scaleImage(ImageIO.read(Resources.class.getResourceAsStream(name)));
            graphical.put(name, image);
        } catch (IOException e) {e.printStackTrace();}

        return image;
    }

    public static BufferedImage scaleImage(BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage((int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(image, scaledImage);
        
        return scaledImage;
    }

    /**
     * @param name The name of the image file.
     * @return A pointer to the loaded image with the given name.
     */
    public static BufferedImage getGraphicalResource(String name) {
        return graphical.get(name);
    }
}