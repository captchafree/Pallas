package com.pallas.EditingAlgorithms;

import com.pallas.Helpers.MatrixOperations;
import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnergyMap implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        Map<String, int[][]> matrix = getImageMatrix(image);

        //Get a matrix with red, blue, and green's color component values.
        int[][] matrixRed = matrix.get("red");
        int[][] matrixGreen = matrix.get("green");
        int[][] matrixBlue = matrix.get("blue");

        int[][] redGradientX = MatrixOperations.getGradientMatrixX(matrixRed);
        int[][] redGradientY = MatrixOperations.getGradientMatrixY(matrixRed);

        int[][] greenGradientX = MatrixOperations.getGradientMatrixX(matrixGreen);
        int[][] greenGradientY = MatrixOperations.getGradientMatrixY(matrixGreen);

        int[][] blueGradientX = MatrixOperations.getGradientMatrixX(matrixBlue);
        int[][] blueGradientY = MatrixOperations.getGradientMatrixY(matrixBlue);

        //Calculate the magnitude of the gradient at each pixel
        int[][] energyMap = new int[matrixRed.length][matrixRed[0].length];

        for(int i = 0; i < matrixRed.length; i++) {
            for(int j = 0; j < matrixRed[i].length; j++) {
                energyMap[i][j] = Math.abs(redGradientX[i][j]) + Math.abs(redGradientY[i][j]);
                energyMap[i][j] += Math.abs(greenGradientX[i][j]) + Math.abs(greenGradientY[i][j]);
                energyMap[i][j] += Math.abs(blueGradientX[i][j]) + Math.abs(blueGradientY[i][j]);
            }
        }

        //Clip the color values to [0, 255]
        for(int i = 0; i < energyMap.length; i++) {
            for (int j = 0; j < energyMap[i].length; j++) {
                energyMap[i][j] = Math.min(energyMap[i][j], 255);
            }
        }

        return ImageHandler.createImageFromMatrix(energyMap);
    }

    /**
     * Returns a map with values for the keys "red", "green", and "blue". Each key contains the respective color value for the image.
     * @param image The image to get the individual color matrices.
     * @return A map containing the three color matrices.
     */
    private Map<String, int[][]> getImageMatrix(BufferedImage image) {
        PImage img = new PImage(image);

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(img.getImage());

        int[][] matrixRed = new int[pixelMatrix.length][pixelMatrix[0].length];
        int[][] matrixGreen = new int[pixelMatrix.length][pixelMatrix[0].length];
        int[][] matrixBlue = new int[pixelMatrix.length][pixelMatrix[0].length];

        //Extract the red, green, and blue components from each pixel
        for(int i = 0; i < pixelMatrix.length; i++) {
            for(int j = 0; j < pixelMatrix[i].length; j++) {
                matrixRed[i][j] = pixelMatrix[i][j].getRedComponent();
                matrixGreen[i][j] = pixelMatrix[i][j].getGreenComponent();
                matrixBlue[i][j] = pixelMatrix[i][j].getBlueComponent();
            }
        }

        Map<String, int[][]> result = new HashMap<>();
        result.put("red", matrixRed);
        result.put("green", matrixGreen);
        result.put("blue", matrixBlue);

        return result;
    }
}
