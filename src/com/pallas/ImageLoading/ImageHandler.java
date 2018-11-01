package com.pallas.ImageLoading;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Base64;

/**
 * A class for loading/creating images
 */
public class ImageHandler {

    /**
     * Returns the image located at the URL
     * @param url The URL to retrieve the image from
     * @return The image at the URL
     * @throws IOException If the image cannot be read
     */
    public static BufferedImage getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean saveImageToFile(BufferedImage image, String filename) {
        if(image == null) {
            return false;
        }

        String formatType = filename.substring(filename.indexOf(".") + 1);

        try {
            BufferedImage imageToSave = image;
            File file = new File(filename);
            ImageIO.write(imageToSave, formatType, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Pixel[][] getImageMatrix(BufferedImage image) {
        Pixel[][] matrix = new Pixel[image.getWidth()][image.getHeight()];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {

                int value = image.getRGB(i, j);
                int red = (value >> 16) & 0x000000FF;
                int green = (value >> 8) & 0x000000FF;
                int blue = (value) & 0x000000FF;

                Pixel p = new Pixel();
                p.red = red;
                p.green = green;
                p.blue = blue;

                matrix[i][j] = p;
            }
        }

        return matrix;
    }

    public static BufferedImage createImageFromMatrix(Pixel[][] matrix) {
        try {
            BufferedImage image = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length; j++) {

                    Pixel p = matrix[i][j];
                    int red = p.red;
                    int green = p.green;
                    int blue = p.blue;

                    Color newColor = new Color(red, green, blue);
                    image.setRGB(i, j, newColor.getRGB());
                }
            }
            return image;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage createImageFromMatrix(int[][] matrix) {
        try {
            BufferedImage image = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                    int c = matrix[i][j];
                    Color newColor = new Color(c, c, c);
                    image.setRGB(i, j, newColor.getRGB());
                }
            }
            return image;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toBase64(BufferedImage image) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}
