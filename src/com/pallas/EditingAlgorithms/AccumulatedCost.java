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

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(img.getImage());
        int[][] matrix = new int[pixelMatrix.length][pixelMatrix[0].length];

        for(int i = 0 ; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = pixelMatrix[i][j].getRedComponent();
            }
        }

        return null;//ImageHandler.createImageFromMatrix(calculateCostMatrix(matrix));
    }

    public static int[][] calculateCostMatrix(int[][] matrix) {
        int[][] result = matrix;

        int max = 0;
        for(int i = 0 ; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if(i == 0) {
                    result[i][j] = matrix[i][j];
                } else if(j == 0) {
                    result[i][j] = result[i][j] + Math.min(result[i-1][j], result[i-1][j+1]);
                } else if (j == matrix[0].length - 1) {
                    result[i][j] = result[i][j] + Math.min(result[i-1][j-1], result[i-1][j]);
                } else {
                    result[i][j] = result[i][j] + Math.min(Math.min(result[i-1][j-1], result[i-1][j]), result[i-1][j+1]);
                }

                if(result[i][j] > max) {
                    max = result[i][j];
                }
            }
        }

        /*for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = (int) ((((double) result[i][j]) / (double) max) * 255);
            }
        }*/

        return result;
    }

    public static int[][] calculateCostMatrix(Pixel[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];

        for(int i = 0 ; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = matrix[i][j].getRedComponent();
            }
        }

        int max = 0;
        for(int y = 0; y < result[0].length; y++) {
            for (int x = 0; x < result.length; x++) {
                if(y == 0) {
                    result[x][y] = matrix[x][y].getRedComponent();
                } else if(x == 0) {
                    result[x][y] = result[x][y] + Math.min(result[x][y-1], result[x+1][y-1]);
                } else if (x == result.length - 1) {
                    result[x][y] = result[x][y] + Math.min(result[x-1][y-1], result[x][y-1]);
                } else {
                    result[x][y] = result[x][y] + Math.min(Math.min(result[x-1][y-1], result[x][y-1]), result[x+1][y-1]);
                }

                if(result[x][y] > max) {
                    max = result[x][y];
                }
            }
        }

        /*for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = (int) ((((double) result[i][j]) / (double) max) * 255);
            }
        }*/

        return result;
    }
}
