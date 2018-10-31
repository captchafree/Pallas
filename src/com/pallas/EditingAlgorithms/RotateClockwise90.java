package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class RotateClockwise90 implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix[0].length][matrix.length];

        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[matrix.length - i - 1][j] = matrix[j][i];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);
    }
}
