package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class RedMask implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {

        Pixel[][] pixels = ImageHandler.getImageMatrix(image);

        for(Pixel[] row : pixels) {
            for(Pixel pixel : row) {
                pixel.maskRed();
            }
        }

        return ImageHandler.createImageFromMatrix(pixels);
    }
}
