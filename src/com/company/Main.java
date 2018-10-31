package com.company;

import com.company.ImageLoading.ImageEditor;
import com.company.ImageLoading.ImageHandler;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageHandler.getImage("https://pbs.twimg.com/profile_images/1046968391389589507/_0r5bQLl_400x400.jpg");

        ImageHandler.saveImage(image, "dog.png");


        ImageHandler.saveImage(ImageEditor.toGrayScale(image), "dog_test.png");
    }
}
