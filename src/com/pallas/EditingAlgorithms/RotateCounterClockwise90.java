package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.PImage;

import java.awt.image.BufferedImage;

//TODO: Fix algorithm
public class RotateCounterClockwise90 implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        /*Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);*/

        PImage img = new PImage(image);
        img.rotate180().rotateClockwise90();
        return img.getImage();
    }
}
