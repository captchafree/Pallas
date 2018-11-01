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
                    p.red = 255;
                    p.green = 255;
                    p.blue = 255;
                } else {
                    for(int k = 0; k < 3; k++) {
                        for(int l = 0; l < 3; l++) {
                            p.red += Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].red));
                            p.green += Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].green));
                            p.blue += Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[i][j].blue));
                        }
                    }
                }

                p.red = Math.max(0, Math.min(255, p.red));
                p.green = Math.max(0, Math.min(255, p.green));
                p.blue = Math.max(0, Math.min(255, p.blue));

                result[i][j] = p;
            }
        }

        return ImageHandler.createImageFromMatrix(result);
    }
}
