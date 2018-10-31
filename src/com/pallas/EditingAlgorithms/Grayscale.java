package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class Grayscale implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        Pixel[][] matrix = ImageHandler.getImageMatrix(image);

        for(Pixel[] row : matrix) {
            for(Pixel pixel : row) {
                pixel.grayScale();
            }
        }

        return ImageHandler.createImageFromMatrix(matrix);
    }
}
