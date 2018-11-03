package com.pallas.EditingAlgorithms;

import java.awt.image.BufferedImage;

public interface EditAlgorithm {

    /**
     * Performs an edit to an image.
     * @param image The image to be edited.
     * @return The image that results from applying an editing algorithm to the specified image.
     */
    BufferedImage performEdit(BufferedImage image);
}
