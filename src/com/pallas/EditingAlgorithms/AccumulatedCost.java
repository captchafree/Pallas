package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class AccumulatedCost implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        PImage img = new PImage(image);
        img.energyMap();

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(image);
        int[][] matrix = new int[pixelMatrix.length][pixelMatrix[0].length];

        for(int i = 0 ; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = pixelMatrix[i][j].getRedComponent();
            }
        }

        return ImageHandler.createImageFromMatrix(calculateCostMatrix(matrix));
    }

    private int[][] calculateCostMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];

        for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i == 0) {
                    result[i][j] = matrix[i][j];
                } else if(j == 0) {
                    result[i][j] = matrix[i][j] + Math.min(matrix[i-1][j], matrix[i-1][j+1]);
                } else if (j == matrix[0].length - 1) {
                    result[i][j] = matrix[i][j] + Math.min(matrix[i-1][j-1], matrix[i-1][j]);
                } else {
                    result[i][j] = matrix[i][j] + Math.min(Math.min(matrix[i-1][j-1], matrix[i-1][j]), matrix[i-1][j+1]);
                }
            }
        }

        return result;
    }
}
