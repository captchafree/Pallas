package com.pallas;

import com.pallas.ImageLoading.PImage;

public class Driver {

    public static void main(String[] args) {
        String url = "https://pbs.twimg.com/profile_images/1046968391389589507/_0r5bQLl_400x400.jpg";
        PImage img = new PImage(url);

        img.rotate180().grayscale().rotateClockwise90().rotateCounterClockwise90().rotateCounterClockwise90().rotateClockwise90().rotate180();
        img.saveImageToFile("testImage.png");
    }
}
