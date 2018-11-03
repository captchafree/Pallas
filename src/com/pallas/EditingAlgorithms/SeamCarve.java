package com.pallas.EditingAlgorithms;

import com.pallas.Helpers.MatrixOperations;
import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;
import com.pallas.ImageLoading.Point;

import java.awt.image.BufferedImage;

public abstract class SeamCarve {

    /**
     * Returns the list of points in the least interesting seam of an image
     * @param image The image to get the seam of
     * @return The list of points that are in the least interesting seam of the image
     */
    protected Point[] getSeam(final BufferedImage image) {

        //Get the accumulated cost matrix of the image
        PImage energyMap = new PImage(image).energyMap();
        int[][] costMatrix = MatrixOperations.calculateCostMatrix(ImageHandler.getImageMatrix(energyMap.getImage()));

        //Will hold the list of points in the seam
        Point[] seam = new Point[costMatrix[0].length];

        //Find the pixel with the lowest cost in the bottom row of the image
        Point initialPoint = new Point();
        initialPoint.x = minimumValueRestrictedToRange(costMatrix, costMatrix[0].length - 1, 0, costMatrix.length - 1);
        initialPoint.y = costMatrix[0].length-1;
        seam[costMatrix[0].length - 1] = initialPoint;

        //Calculate the pixel to be removed at each row
        for(int y = costMatrix[0].length - 2; y >= 0; y--) {
            Point p = new Point();
            p.x = minimumValueRestrictedToRange(costMatrix, y,seam[y+1].x - 1, seam[y+1].x + 1);
            p.y = y;
            seam[y] = p;

            //Draw the seam on the image in red
            //image.setRGB(p.x, p.y, new Color(255, 0, 0).getRGB());
        }

        return seam;
    }

    /**
     * Finds the index of the minimum pixel of the three adjacent pixels above it
     * @param matrix The image to look at
     * @param y The y value of the row to check
     * @param beg The beginning index to check (inclusive)
     * @param end The end index to check (inclusive)
     * @return
     */
    protected int minimumValueRestrictedToRange(int[][] matrix, int y, int beg, int end) {
        //Clips the indices so that they are in a valid range
        beg = Math.max(beg, 0);
        end = Math.min(end, matrix.length - 1);

        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        //Calculate the pixel with the lowest cost
        for(int x = beg; x <= end; x++) {
            if(matrix[x][y] < min) {
                min = matrix[x][y];
                minIndex = x;
            }
        }

        return minIndex;
    }

    /**
     * Removes a list of pixels from an image
     * @param image The image to be carved
     * @param seam An array of the points that should be removed
     * @return An array of pixels representing an image with the seam removed
     */
    protected Pixel[][] removeSeam(Pixel[][] image, Point[] seam) {
        Pixel[][] result = new Pixel[image.length - 1][image[0].length];

        int offset = 0;

        //Copy each pixel from the original image into the new image with the given seam removed
        for(int y = 0; y < image[0].length; y++) {
            for (int x = 0; x < image.length; x++) {
                if(seam[y].x == x) {
                    offset = -1;
                } else {
                    result[x + offset][y] = image[x][y];
                }
            }
            offset = 0;
        }

        return result;
    }
}
