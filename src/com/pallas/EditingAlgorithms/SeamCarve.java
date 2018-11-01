package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;
import com.pallas.ImageLoading.Point;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.*;

public class SeamCarve implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        BufferedImage resultWithSeam = deepCopy(image);

        PImage cost = new PImage(image).accumulatedCostMatrix();
        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(cost.getImage());

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
        }

        for(Point p : seam) {
            resultWithSeam.setRGB(p.x, p.y, new Color(255, 0, 0).getRGB());
        }

        return resultWithSeam;
    }

    private static int minimumValueRestrictedToRange(Pixel[] row, int beg, int end) {
        beg = Math.max(beg, 0);
        end = Math.min(end, row.length-1);

        Pixel min = new Pixel();
        min.red = 255;

        int minIndex = row.length;

        for(int i = beg; i <= end; i++) {
            if(row[i].red < min.red) {
                min = row[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
