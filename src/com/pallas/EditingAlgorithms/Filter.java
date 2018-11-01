package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class Filter implements EditAlgorithm {

    int[][] filter = new int[][] {
        {-2, -1, 0},
        {-1, 1, 1},
        {0, 1, 2},
    };

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(image);
        Pixel[][] result = new Pixel[pixelMatrix.length][pixelMatrix[0].length];

        for(int i = 0; i < pixelMatrix.length; i++) {
            for(int j = 0; j < pixelMatrix[i].length; j++) {
                Pixel p = new Pixel();
                if(i == 0 || j == 0) {
                    p.setRGB(255, 255, 255);
                } else {
                    for(int k = 0; k < 3; k++) {
                        for(int l = 0; l < 3; l++) {
                            p.setRedComponent(p.getRedComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].getRedComponent())));
                            p.setGreenComponent(p.getGreenComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].getGreenComponent())));
                            p.setBlueComponent(p.getBlueComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].getBlueComponent())));
                        }
                    }
                }

                p.setRedComponent(Math.max(0, Math.min(255, p.getRedComponent())));
                p.setGreenComponent(Math.max(0, Math.min(255, p.getGreenComponent())));
                p.setBlueComponent(Math.max(0, Math.min(255, p.getBlueComponent())));

                result[i][j] = p;
            }
        }

        return ImageHandler.createImageFromMatrix(result);
    }
}
