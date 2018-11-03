package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.Pixel;

import java.awt.image.BufferedImage;

public class Filter implements EditAlgorithm {

    private static int[][] filter = new int[][] {
        {-2, -1, 0},
        {-1, 1, 1},
        {0, 1, 2},
    };

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        Pixel[][] pixelMatrix = ImageHandler.getImageMatrix(image);
        Pixel[][] result = new Pixel[pixelMatrix.length][pixelMatrix[0].length];

        //Apply the filter to the pixel matrix
        for(int x = 0; x < pixelMatrix.length; x++) {
            for(int y = 0; y < pixelMatrix[x].length; y++) {
                Pixel p = new Pixel();
                if(x == 0 || y == 0) {
                    p.setRGB(255, 255, 255);
                } else {
                    //Calculate the value of the current pixel with the filter applied to it.
                    for(int k = 0; k < 3; k++) {
                        for(int l = 0; l < 3; l++) {
                            p.setRedComponent(p.getRedComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[x][y].getRedComponent())));
                            p.setGreenComponent(p.getGreenComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[x][y].getGreenComponent())));
                            p.setBlueComponent(p.getBlueComponent() + Math.max(0, Math.min(255, filter[k][l] * pixelMatrix[x][y].getBlueComponent())));
                        }
                    }
                }

                //Clip components to a value in [0, 255]
                p.setRedComponent(Math.max(0, Math.min(255, p.getRedComponent())));
                p.setGreenComponent(Math.max(0, Math.min(255, p.getGreenComponent())));
                p.setBlueComponent(Math.max(0, Math.min(255, p.getBlueComponent())));

                result[x][y] = p;
            }
        }

        return ImageHandler.createImageFromMatrix(result);
    }
}
