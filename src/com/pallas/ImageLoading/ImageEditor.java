package com.pallas.ImageLoading;

import java.awt.image.BufferedImage;

/**
 * A class for editing images
 */
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

    //TODO: Fix algorithm
    public static BufferedImage rotateCounterClockwise90(BufferedImage image) {
        /*Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);*/

        return ImageEditor.rotateClockwise90(ImageEditor.rotate180(image));
    }

    public static BufferedImage rotateClockwise90(BufferedImage image) {
        Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix[0].length][matrix.length];

        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[matrix.length - i - 1][j] = matrix[j][i];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);
    }

    //TODO: Fix algorithm
    public static BufferedImage rotate180(BufferedImage image) {
        /*Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][matrix[i].length - j - 1] = matrix[i][j];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);*/

        return ImageEditor.rotateClockwise90(ImageEditor.rotateClockwise90(image));
    }
}
