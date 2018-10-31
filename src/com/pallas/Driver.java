package com.pallas;

import com.pallas.ImageLoading.PImage;

public class Driver {

    public static void main(String[] args) {
        final String saban = "http://2f13yq12csmv2yraq925m73i-wpengine.netdna-ssl.com/wp-content/uploads/2011/03/sabanstatue1.jpg";
        final String spiral = "https://www.researchgate.net/profile/Neil_Harrison/publication/5608780/figure/fig1/AS:214351891755010@1428116881418/High-contrast-flashing-checkerboard-task-used-as-a-potent-stimulus-of-primary-visual.png";

        PImage img = new PImage(saban);

        img.gradient();
        img.saveToFile("gradient.png");

    }
}
