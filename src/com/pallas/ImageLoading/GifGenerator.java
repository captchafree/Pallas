package com.pallas.ImageLoading;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GifGenerator {

    private FileImageOutputStream output = null;
    private GifSequenceWriter writer = null;

    public GifGenerator(String filename, int imageType, int delay, boolean doesRepeat) {
        try {
            this.output = new FileImageOutputStream(new File(filename));
            this.writer = new GifSequenceWriter(output, imageType, delay, doesRepeat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addImage(BufferedImage image) {
        try {
            writer.writeToSequence(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            writer.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
