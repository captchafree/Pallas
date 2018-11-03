package com.pallas.ImageLoading;

import com.pallas.EditingAlgorithms.EditAlgorithm;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GifGenerator {

    private String filename;
    private int imageType;
    private int delay;
    private boolean doesRepeat;

    private FileImageOutputStream output = null;
    private GifSequenceWriter writer = null;

    /**
     * Create a GifGenerator with the specified options.
     * @param filename The file to save the gif to.
     * @param imageType The type of the images in the gif.
     * @param delay The delay between each image in ms.
     * @param doesRepeat True if the gif should repeat, false otherwise.
     */
    public GifGenerator(String filename, int imageType, int delay, boolean doesRepeat) {
        this.filename = filename;
        this.imageType = imageType;
        this.delay = delay;
        this.doesRepeat = doesRepeat;

        this.init();
    }

    /**
     * Initializes the object's streams.
     */
    private void init() {
        try {
            this.output = new FileImageOutputStream(new File(filename));
            this.writer = new GifSequenceWriter(output, imageType, delay, doesRepeat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a gif with the specified options. This method modifies the image passed into it.
     * @param image The image to be in the gif.
     * @param algorithmToApply The algorithm to repeatedly apply to the image.
     * @param iterations The number of times to apply the algorithm to the image.
     */
    public void createGif(BufferedImage image, EditAlgorithm algorithmToApply, int iterations) {
        this.createGif(new PImage(image), algorithmToApply, iterations);
    }

    /**
     * Create a gif with the specified options. This method modifies the image passed into it.
     * @param image The image to be in the gif.
     * @param algorithmToApply The algorithm to repeatedly apply to the image.
     * @param iterations The number of times to apply the algorithm to the image.
     */
    public void createGif(PImage image, EditAlgorithm algorithmToApply, int iterations) {
        this.addImage(image);

        for(int i = 0; i < iterations; i++) {
            image.applyAlgorithm(algorithmToApply);
            this.addImage(image);
            System.out.println("Completed " + i + " iterations.");
        }

        this.save();
    }

    /**
     * Add an image to the gif.
     * @param image The image to add to the gif.
     */
    public void addImage(PImage image) {
        this.addImage(image.getImage());
    }

    /**
     * Add an image to the gif.
     * @param image The image to add to the gif.
     */
    public void addImage(BufferedImage image) {
        try {
            writer.writeToSequence(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the gif to a file.
     */
    public void save() {
        try {
            writer.close();
            output.close();

            this.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
