package com.pallas.EditingAlgorithms;

import com.pallas.ImageLoading.ImageHandler;
import com.pallas.ImageLoading.PImage;
import com.pallas.ImageLoading.Pixel;
import com.pallas.ImageLoading.Point;

import java.awt.image.BufferedImage;

public class SeamCarveHorizontal extends SeamCarve implements EditAlgorithm {

    @Override
    public BufferedImage performEdit(BufferedImage image) {
        BufferedImage resultWithSeam = new PImage(image).getImage();

        //Calculate the seam of the image
        Point[] seam = getSeam(resultWithSeam);

        //Store the image in an array
        Pixel[][] resultMatrix = ImageHandler.getImageMatrix(resultWithSeam);

        //Return the image that resulted from removing the seam
        return new PImage(ImageHandler.createImageFromMatrix(removeSeam(resultMatrix, seam))).getImage();
    }
}
