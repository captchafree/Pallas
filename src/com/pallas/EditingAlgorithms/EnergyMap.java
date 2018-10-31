package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnergyMap implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        System.out.println("Getting image matrix");
        Map<String, int[][]> matrix = getImageMatrix(image);

        int[][] matrixRed = matrix.get("red");
        int[][] matrixGreen = matrix.get("green");
        int[][] matrixBlue = matrix.get("blue");

        int[][] energyMap = new int[matrixRed.length][matrixRed[0].length];

        int[][] redGradientX = getGradientMatrixX(matrixRed);
        int[][] redGradientY = getGradientMatrixY(matrixRed);

        int[][] greenGradientX = getGradientMatrixX(matrixGreen);
        int[][] greenGradientY = getGradientMatrixY(matrixGreen);

        int[][] blueGradientX = getGradientMatrixX(matrixBlue);
        int[][] blueGradientY = getGradientMatrixY(matrixBlue);

        for(int i = 0; i < matrixRed.length; i++) {
            for(int j = 0; j < matrixRed[i].length; j++) {
                energyMap[i][j] = Math.abs(redGradientX[i][j]) + Math.abs(redGradientY[i][j]);
                energyMap[i][j] += Math.abs(greenGradientX[i][j]) + Math.abs(greenGradientY[i][j]);
                energyMap[i][j] += Math.abs(blueGradientX[i][j]) + Math.abs(blueGradientY[i][j]);
            }
        }

        for(int i = 0; i < energyMap.length; i++) {
            for (int j = 0; j < energyMap[i].length; j++) {
                energyMap[i][j] = Math.min(energyMap[i][j], 255);
            }
        }

        System.out.println("Creating Image");
        return ImageHandler.createImageFromMatrix(energyMap);
    }

    private Map<String, int[][]> getImageMatrix(BufferedImage image) {
        PImage img = new PImage(image);

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(img.getImage());

        int[][] matrixRed = new int[pixelMatrix.length][pixelMatrix[0].length];
        int[][] matrixGreen = new int[pixelMatrix.length][pixelMatrix[0].length];
        int[][] matrixBlue = new int[pixelMatrix.length][pixelMatrix[0].length];

        for(int i = 0; i < pixelMatrix.length; i++) {
            for(int j = 0; j < pixelMatrix[i].length; j++) {
                matrixRed[i][j] = pixelMatrix[i][j].red;
                matrixGreen[i][j] = pixelMatrix[i][j].green;
                matrixBlue[i][j] = pixelMatrix[i][j].blue;
            }
        }

        Map<String, int[][]> result = new HashMap<>();
        result.put("red", matrixRed);
        result.put("green", matrixGreen);
        result.put("blue", matrixBlue);

        return result;
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
