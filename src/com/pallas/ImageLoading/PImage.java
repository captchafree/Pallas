package com.pallas.ImageLoading;

import com.pallas.EditingAlgorithms.*;

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
        EditAlgorithm algorithm = new RotateClockwise90();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage rotateCounterClockwise90() {
        EditAlgorithm algorithm = new RotateCounterClockwise90();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage rotate180() {
        EditAlgorithm algorithm = new Rotate180();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage grayscale() {
        EditAlgorithm algorithm = new Grayscale();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage gradient() {
        EditAlgorithm algorithm = new Gradient();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public String toBase64() {
        return ImageHandler.toBase64(this.image);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean saveToFile(String filename) {
        return ImageHandler.saveImageToFile(this.image, filename);
    }
}
