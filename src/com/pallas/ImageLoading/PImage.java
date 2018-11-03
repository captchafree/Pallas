package com.pallas.ImageLoading;

import com.pallas.EditingAlgorithms.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Wraps the BufferedImage class with ImageHandler and ImageEditor functionality.
 */
public class PImage {

    private BufferedImage image;

    public PImage(URL url) {
        this.image = ImageHandler.getImage(url);
    }

    public PImage(BufferedImage image) {
        this.image = ImageHandler.deepCopy(image);
    }

    public PImage(File file) {
        try {
            this.image = ImageIO.read(file);
        } catch(IOException e) {
            this.image = null;
        }
    }

    public PImage(PImage image) {
        this(image.getImage());
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

    public PImage maskRed() {
        EditAlgorithm algorithm = new RedMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage maskGreen() {
        EditAlgorithm algorithm = new GreenMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage maskBlue() {
        EditAlgorithm algorithm = new BlueMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage gradient() {
        EditAlgorithm algorithm = new Gradient();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage energyMap() {
        EditAlgorithm algorithm = new EnergyMap();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage seamCarve() {
        EditAlgorithm algorithm = new SeamCarve();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage filter() {
        EditAlgorithm algorithm = new Filter();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    public PImage performEdit(EditAlgorithm algorithm) {
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
