package com.pallas.ImageLoading;

import java.awt.image.BufferedImage;

/**
 * Wraps the BufferedImage class with ImageHandler and ImageEditor functionality.
 */
public class PImage {

    private BufferedImage image;

    public PImage(String url) {
        this.image = ImageHandler.getImage(url);
    }

    public PImage(BufferedImage image) {
        this.image = image;
    }

    public PImage rotateClockwise90() {
        this.image = ImageEditor.rotateClockwise90(this.image);
        return this;
    }

    public PImage rotateCounterClockwise90() {
        this.image = ImageEditor.rotateCounterClockwise90(this.image);
        return this;
    }

    public PImage rotate180() {
        this.image = ImageEditor.rotate180(this.image);
        return this;
    }

    public PImage grayscale() {
        this.image = ImageEditor.toGrayScale(this.image);
        return this;
    }

    public String toBase64() {
        return ImageHandler.toBase64(this.image);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean saveImageToFile(String filename) {
        return ImageHandler.saveImageToFile(this.image, filename);
    }
}
