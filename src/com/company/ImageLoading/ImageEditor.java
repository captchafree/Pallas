package com.company.ImageLoading;

import java.awt.image.BufferedImage;

public class ImageEditor {

    public static BufferedImage toGrayScale(BufferedImage image) {
        Pixel[][] matrix = ImageHandler.getImageMatrix(image);

        for(Pixel[] row : matrix) {
            for(Pixel pixel : row) {
                pixel.grayScale();
            }
        }

        return ImageHandler.createImageFromMatrix(matrix);
    }
}
