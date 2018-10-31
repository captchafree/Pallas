package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class Gradient implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        int[][] matrix = getImageMatrix(image);

        int[][] gradientX = getGradientMatrixX(matrix);
        int[][] gradientY = getGradientMatrixY(matrix);


        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) Math.sqrt(Math.pow(gradientX[i][j], 2) + Math.pow(gradientY[i][j], 2));
            }
        }

        Pixel[][] pixels = new Pixel[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if(gradientX[i][j] == 0) gradientX[i][j] = 1;

                double angle = Math.atan(gradientY[i][j] / gradientX[i][j]);

                Pixel pixel = new Pixel();
                pixel.red = (int) Math.abs(matrix[i][j] * Math.sin(angle));
                pixel.green = (int) Math.abs(matrix[i][j] * Math.cos(angle));
                pixel.blue = 0;
                pixels[i][j] = pixel;
            }
        }


        return ImageHandler.createImageFromMatrix(pixels);
    }

    private int[][] getImageMatrix(BufferedImage image) {
        PImage img = new PImage(image);
        img.grayscale();

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(img.getImage());

        int[][] matrix = new int[pixelMatrix.length][pixelMatrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = pixelMatrix[i][j].red;
            }
        }

        return matrix;
    }

    private int[][] getGradientMatrixX(int[][] pixels) {
        int[][] result = new int[pixels.length][pixels[0].length];

        for(int i = 1; i < pixels.length-1; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                result[i][j] = (pixels[i+1][j] - pixels[i-1][j]) / 2;
            }
        }

        return result;
    }

    private int[][] getGradientMatrixY(int[][] pixels) {
        int[][] result = new int[pixels.length][pixels[0].length];

        for(int i = 0; i < pixels.length; i++) {
            for(int j = 1; j < pixels[i].length-1; j++) {
                result[i][j] = (pixels[i][j+1] - pixels[i][j-1]) / 2;
            }
        }

        return result;
    }
}
