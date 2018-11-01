package com.pallas;

import com.pallas.ImageLoading.PImage;

public class Driver {

    public static void main(String[] args) {
        final String saban = "http://2f13yq12csmv2yraq925m73i-wpengine.netdna-ssl.com/wp-content/uploads/2011/03/sabanstatue1.jpg";
        final String spiral = "https://www.researchgate.net/profile/Neil_Harrison/publication/5608780/figure/fig1/AS:214351891755010@1428116881418/High-contrast-flashing-checkerboard-task-used-as-a-potent-stimulus-of-primary-visual.png";
        final String moraine = "https://polarsteps.s3.amazonaws.com/u_84958/60661467-16dc-46a4-9fdc-6b5f9b31f2ff_big-thumbnail84958_047540470239152066_1512552969480";
        final String rio = "https://www.cruisebe.com/sites/default/files/portofcallobject/commons/a/a1/1_cristor_redentor_2014.jpg";

        PImage img = new PImage(rio);
        img.saveToFile("original.png");
        img.gradient();
        img.saveToFile("gradient.png");

        img = new PImage(rio);
        img.energyMap();
        img.saveToFile("energyMap.png");

        img = new PImage(rio);
        img.maskRed();
        img.saveToFile("redMask.png");

        img = new PImage(rio);
        img.maskGreen();
        img.saveToFile("greenMask.png");

        img = new PImage(rio);
        img.maskBlue();
        img.saveToFile("blueMask.png");
    }
}
