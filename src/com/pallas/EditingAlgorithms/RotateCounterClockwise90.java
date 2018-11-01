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

        int w = image.getWidth();
        int h = image .getHeight();
        BufferedImage dest = new BufferedImage(h, w, image.getType());
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                dest.setRGB(y, w - x - 1, image.getRGB(x, y));
        return dest;
    }
}
