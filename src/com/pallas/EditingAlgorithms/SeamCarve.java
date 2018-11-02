package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;
import com.pallas.ImageLoading.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SeamCarve implements EditAlgorithm {

    private static int index = 2;

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        BufferedImage resultWithSeam = new PImage(image).getImage();

        PImage energyMap = new PImage(image);
        energyMap.energyMap();
        int[][] costMatrix = AccumulatedCost.calculateCostMatrix(ImageHandler.getImageMatrix(energyMap.getImage()));

        /*System.out.println(costMatrix.length + " x " + costMatrix[0].length);
        for(int y = 0; y < costMatrix[0].length; y++) {
            for (int x = 0; x < costMatrix.length; x++) {
                //System.out.print("(" + x + ", " + y + ")");
                //System.out.print(costMatrix[x][y] + "\t\t");
            } //System.out.println();
        }*/

        Point[] seam = new Point[costMatrix[0].length];

        Point initialPoint = new Point();
        //System.out.println("Checking on row " + (costMatrix[0].length-1) + " between the x values of " + 0 + " and " + (costMatrix.length - 1));
        initialPoint.x = minimumValueRestrictedToRange(costMatrix, costMatrix[0].length - 1, 0, costMatrix.length - 1);
        initialPoint.y = costMatrix[0].length-1;

        seam[costMatrix[0].length - 1] = initialPoint;

        //System.out.println(seam[seam.length-1]);

        for(int y = costMatrix[0].length - 2; y >= 0; y--) {
            Point p = new Point();
            p.x = minimumValueRestrictedToRange(costMatrix, y,seam[y+1].x - 1, seam[y+1].x + 1);
            p.y = y;
            seam[y] = p;

            resultWithSeam.setRGB(p.x, p.y, new Color(255, 0, 0).getRGB());
        }

        Pixel[][] resultMatrix = ImageHandler.getImageMatrix(resultWithSeam);

        //new PImage(ImageHandler.createImageFromMatrix(resultMatrix)).saveToFile("test_seam" + index + ".png");
        //index++;

        return new PImage(ImageHandler.createImageFromMatrix(removeSeam(resultMatrix, seam))).getImage();
    }

    private static int minimumValueRestrictedToRange(int[][] matrix, int y, int beg, int end) {
        beg = Math.max(beg, 0);
        end = Math.min(end, matrix.length - 1);

        //System.out.println("beg = " + beg + " and end = " + end);

        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        for(int x = beg; x <= end; x++) {
            if(matrix[x][y] < min) {
                min = matrix[x][y];
                minIndex = x;
            }
        }

        return minIndex;
    }

    private static Pixel[][] removeSeam(Pixel[][] image, Point[] seam) {
        Pixel[][] result = new Pixel[image.length - 1][image[0].length];

        int offset = 0;

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
