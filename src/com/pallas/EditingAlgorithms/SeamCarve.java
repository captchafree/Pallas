package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;
import com.pallas.ImageLoading.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SeamCarve implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        BufferedImage resultWithSeam = ImageHandler.deepCopy(new PImage(image).rotateClockwise90().getImage());

        PImage cost = new PImage(image).rotateCounterClockwise90().energyMap();
        int[][] pixelMatrix = AccumulatedCost.calculateCostMatrix(ImageHandler.getImageMatrix(cost.getImage()));

        for(int[] i : pixelMatrix) {
            for(int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();

        Point[] seam = new Point[pixelMatrix.length];

        Point initialPoint = new Point();
        initialPoint.x = minimumValueRestrictedToRange(pixelMatrix[pixelMatrix.length-1], 0, pixelMatrix[pixelMatrix.length-1].length-1);
        initialPoint.y = pixelMatrix.length-1;

        seam[pixelMatrix.length - 1] = initialPoint;

        for(int i = pixelMatrix.length - 2; i >= 0; i--) {
            Point p = new Point();
            p.x = minimumValueRestrictedToRange(pixelMatrix[i],seam[i+1].x - 1, seam[i+1].x + 1);
            p.y = i;
            seam[i] = p;

            resultWithSeam.setRGB(p.y, p.x, new Color(255, 0, 0).getRGB());
        }

        Pixel[][] resultMatrix = ImageHandler.getImageMatrix(resultWithSeam);

        new PImage(ImageHandler.createImageFromMatrix(resultMatrix)).saveToFile("test2_seam.png");

        return new PImage(ImageHandler.createImageFromMatrix(removeSeam(resultMatrix, seam))).rotateCounterClockwise90().getImage();
    }

    private static int minimumValueRestrictedToRange(int[] row, int beg, int end) {
        beg = Math.max(beg, 0);
        end = Math.min(end, row.length-1);

        int min = Integer.MAX_VALUE;

        int minIndex = 0;

        for(int i = beg; i <= end; i++) {
            if(row[i] < min) {
                min = row[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private static Pixel[][] removeSeam(Pixel[][] image, Point[] seam) {
        Pixel[][] result = new Pixel[image.length][image[0].length-1];

        int offset = 0;

        for(int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if(seam[i].x == j) {
                    offset = -1;
                } else {
                    result[i][j + offset] = image[i][j];
                }
            }
            offset = 0;
        }

        return result;
    }
}
