package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Util {
    public static BufferedImage copyBufferedImage(BufferedImage src) {
        ColorModel colorModel = src.getColorModel();
        boolean isAlphaRemultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = src.copyData(null);
        return new BufferedImage(colorModel, raster, isAlphaRemultiplied, null);
    }
}