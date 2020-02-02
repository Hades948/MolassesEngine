package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.util.HashMap;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Helper class for resource IO.
 */
public class Resources {
    private static HashMap<String, GraphicalResource> graphical;
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
            image = ImageIO.read(Resources.class.getResourceAsStream(GRAPHICAL_PATH + name));
            GraphicalResource resource = new StaticGraphicalResource(image);
            resource.scaleResource(scaleX, scaleY);
            graphical.put(name, resource);
        } catch (IOException e) {e.printStackTrace();}

        return image;
    }

    static BufferedImage loadEngineGraphicalImage(String name) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Resources.class.getResourceAsStream(name));
            GraphicalResource resource = new StaticGraphicalResource(image);
            resource.scaleResource(scaleX, scaleY);
            graphical.put(name, resource);
        } catch (IOException e) {e.printStackTrace();}

        return image;
    }

    /**
     * @param name The name of the image file.
     * @return A pointer to the loaded image with the given name.
     */
    public static GraphicalResource getGraphicalResource(String name) {
        return graphical.get(name);
    }

    public static void addGraphicalResource(String name, GraphicalResource resource) {
        graphical.put(name, resource);
    }
}
