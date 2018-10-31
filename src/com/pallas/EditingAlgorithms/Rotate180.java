package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.PImage;

import java.awt.image.BufferedImage;

//TODO: Fix algorithm
public class Rotate180 implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        /*Pixel[][] matrix = ImageHandler.getImageMatrix(image);
        Pixel[][] newMatrix = new Pixel[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][matrix[i].length - j - 1] = matrix[i][j];
            }
        }

        return ImageHandler.createImageFromMatrix(newMatrix);*/

        PImage img = new PImage(image);
        img.rotateClockwise90().rotateClockwise90();
        return img.getImage();
    }
}
