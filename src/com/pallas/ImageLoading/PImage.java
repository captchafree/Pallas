package com.pallas.ImageLoading;

import com.pallas.EditingAlgorithms.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Wraps the BufferedImage class with ImageHandler and ImageEditor functionality.
 * Also contains different editing algorithms that can be applied to the wrapped image.
 */
public class PImage {

    private BufferedImage image;

    /**
     * Create a PImage object with the image at a given URL.
     * @param url The url to retrieve the image from.
     */
    public PImage(URL url) {
        this.image = ImageHandler.getImage(url);
    }

    /**
     * Create a PImage object with a specified image.
     * @param image The image to use.
     */
    public PImage(BufferedImage image) {
        this.image = ImageHandler.deepCopy(image);
    }

    /**
     * Create a PImage object with the image at the given file path.
     * @param file A file representation of the image to use.
     */
    public PImage(File file) {
        try {
            this.image = ImageIO.read(file);
        } catch(IOException e) {
            this.image = null;
        }
    }

    /**
     * Copy constructor
     * @param pimage The PImage to copy
     */
    public PImage(PImage pimage) {
        this(pimage.getImage());
    }

    /**
     * Rotate the image clockwise.
     * @return itself
     */
    public PImage rotateClockwise90() {
        EditAlgorithm algorithm = new RotateClockwise90();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Rotate the image counter-clockwise.
     * @return itself
     */
    public PImage rotateCounterClockwise90() {
        EditAlgorithm algorithm = new RotateCounterClockwise90();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Rotate the image 180 degrees.
     * @return itself
     */
    public PImage rotate180() {
        EditAlgorithm algorithm = new Rotate180();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Grayscale the image.
     * @return itself
     */
    public PImage grayscale() {
        EditAlgorithm algorithm = new Grayscale();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Set the green and blue color components to 0.
     * @return itself
     */
    public PImage maskRed() {
        EditAlgorithm algorithm = new RedMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Set the red and blue color components to 0.
     * @return itself
     */
    public PImage maskGreen() {
        EditAlgorithm algorithm = new GreenMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Set the red and green color components to 0.
     * @return itself
     */
    public PImage maskBlue() {
        EditAlgorithm algorithm = new BlueMask();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Create an image representation of the image's gradient.
     * @return itself
     */
    public PImage gradient() {
        EditAlgorithm algorithm = new Gradient();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Create the energy map of the image.
     * @return itself
     */
    public PImage energyMap() {
        EditAlgorithm algorithm = new EnergyMap();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Apply the seam carve algorithm to the image. This algorithm makes images width decrease by one.
     * @return itself
     */
    public PImage seamCarve() {
        EditAlgorithm algorithm = new SeamCarve();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Apply a filter to the image.
     * @return itself
     */
    public PImage filter() {
        EditAlgorithm algorithm = new Filter();
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Perform a specified edit algorithm to the image
     * @return itself
     */
    public PImage applyAlgorithm(EditAlgorithm algorithm) {
        this.image = algorithm.performEdit(this.image);
        return this;
    }

    /**
     * Convert the image to a Base64 String representation.
     * @return The Base64 representation of the image.
     */
    public String toBase64() {
        return ImageHandler.toBase64(this.image);
    }

    /**
     * Get the BufferedImage wrapped by the object.
     * @return The BufferedImage wrapped by the class.
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Saves the BufferedImage to the specified file.
     * @param filename
     * @return True if the file saved successfully, false otherwise.
     */
    public boolean saveToFile(String filename) {
        return ImageHandler.saveImageToFile(this.image, filename);
    }
}
