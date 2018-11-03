package com.pallas.EditingAlgorithms;

import com.pallas.Helpers.MatrixOperations;
import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class Gradient implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        int[][] matrix = getImageMatrix(image);

        //Calculate both components of the gradient at each pixel
        int[][] gradientX = MatrixOperations.getGradientMatrixX(matrix);
        int[][] gradientY = MatrixOperations.getGradientMatrixY(matrix);

        //Find the magnitude of the gradient at each point
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) Math.sqrt(Math.pow(gradientX[i][j], 2) + Math.pow(gradientY[i][j], 2));
            }
        }

        Pixel[][] pixels = new Pixel[matrix.length][matrix[0].length];

        //Create the final image
        for(int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {

                //Check for divide by 0
                if(gradientX[x][y] == 0) {
                    gradientX[x][y] = 1;
                }
                
                double angle = Math.atan(gradientY[x][y] / gradientX[x][y]);

                //Set the color components of the pixel according to the angle of its gradient
                Pixel pixel = new Pixel();
                pixel.setRedComponent((int) Math.abs(matrix[x][y] * Math.sin(angle)));
                pixel.setGreenComponent((int) Math.abs(matrix[x][y] * Math.cos(angle)));
                pixel.setBlueComponent(0);

                pixels[x][y] = pixel;
            }
        }

        return ImageHandler.createImageFromMatrix(pixels);
    }

    /**
     * Returns a 2D array with the average pixel values for the given image (grayscale).
     * @param image The image to get the grayscaled pixel values for.
     * @return An array with the values of each pixel for a grayscaled image.
     */
    private int[][] getImageMatrix(BufferedImage image) {
        PImage grayscale = new PImage(image).grayscale();

        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(grayscale.getImage());

        int[][] matrix = new int[pixelMatrix.length][pixelMatrix[0].length];

        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[x].length; y++) {
                //Color component is irrelevant since image has been grayscaled
                matrix[x][y] = pixelMatrix[x][y].getRedComponent();
            }
        }

        return matrix;
    }
}
