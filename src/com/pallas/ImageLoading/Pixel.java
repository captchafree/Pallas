package com.pallas.ImageLoading;

public class Pixel {

    private int value;

    public Pixel(int r, int g, int b) {
        this.setRGB(r, g, b);
    }

    public Pixel(int value) {
        this.value = value;
    }

    public Pixel() {
        this.value = 0;
    }

    public void grayscale() {
        int red, green, blue;
        red = this.getRedComponent();
        green = this.getGreenComponent();
        blue = this.getBlueComponent();

        int average = (red + green + blue) / 3;
        value = (average << 16) | (average << 8) | average;
    }

    public void maskRed() {
        setRGB(getRedComponent(), 0, 0);
    }

    public void maskGreen() {
        setRGB(0, getGreenComponent(), 0);
    }

    public void maskBlue() {
        setRGB(0, 0, getBlueComponent());
    }

    public void setRedComponent(int value) {
        setRGB(value, getGreenComponent(), getBlueComponent());
    }

    public void setGreenComponent(int value) {
        setRGB(getRedComponent(), value, getBlueComponent());
    }

    public void setBlueComponent(int value) {
        setRGB(getRedComponent(), getGreenComponent(), value);
    }

    public int getRedComponent() {
        return (value >> 16) & 0xff;
    }

    public int getGreenComponent() {
        return (value >> 8) & 0xff;
    }

    public int getBlueComponent() {
        return (value) & 0xff;
    }

    public void setRGB(int r, int g, int b) {
        value = (r << 16) | (g << 8) | b;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getAverageValueRGB() {
        return (getRedComponent() + getGreenComponent() + getBlueComponent()) / 3;
    }

    @Override
    public String toString() {
        return "(" + getRedComponent() + ", " + getGreenComponent() + ", " + getBlueComponent() + ")";
    }
}
