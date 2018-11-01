package com.pallas.ImageLoading;

public class Pixel {

    public int red, green, blue, alpha;

    public void grayScale() {
        int average = (red + blue + green) / 3;
        this.red = this.green = this.blue = average;
    }

    public void maskRed() {
        this.green = 0;
        this.blue = 0;
    }

    public void maskGreen() {
        this.red = 0;
        this.blue = 0;
    }

    public void maskBlue() {
        this.red = 0;
        this.green = 0;
    }

    @Override
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }
}
