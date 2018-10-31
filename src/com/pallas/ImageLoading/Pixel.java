package com.pallas.ImageLoading;

public class Pixel {

    public int red, green, blue, alpha;

    public void grayScale() {
        int average = (red + blue + green) / 3;
        this.red = this.green = this.blue = average;
    }

    @Override
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }
}
