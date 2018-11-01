package com.pallas.Testing;

import com.pallas.ImageLoading.PImage;

import java.io.File;

public class Driver {

    public static void main(String[] args) throws Exception {
        final String saban = "http://2f13yq12csmv2yraq925m73i-wpengine.netdna-ssl.com/wp-content/uploads/2011/03/sabanstatue1.jpg";
        final String spiral = "https://www.researchgate.net/profile/Neil_Harrison/publication/5608780/figure/fig1/AS:214351891755010@1428116881418/High-contrast-flashing-checkerboard-task-used-as-a-potent-stimulus-of-primary-visual.png";
        final String moraine = "https://polarsteps.s3.amazonaws.com/u_84958/60661467-16dc-46a4-9fdc-6b5f9b31f2ff_big-thumbnail84958_047540470239152066_1512552969480";
        final String rio = "https://www.cruisebe.com/sites/default/files/portofcallobject/commons/a/a1/1_cristor_redentor_2014.jpg";
        final String person = "https://cdn.iphonephotographyschool.com/wp-content/uploads/iPhone-Photos-People-In-Landscapes-27@2x.jpg";

        PImage img = new PImage(moraine);


        for(int i = 0; i < 300; i++) {
            img.seamCarve();
            img = new PImage(img.getImage());
            System.out.println("Carved " + i + " times");
        }

        /*img = new PImage(moraine);
        img.energyMap();
        img.saveToFile("energyMap.png");

        img = new PImage(spiral);
        img.maskRed();
        img.saveToFile("redMask.png");

        img = new PImage(spiral);
        img.maskGreen();
        img.saveToFile("greenMask.png");

        img = new PImage(spiral);
        img.maskBlue();
        img.saveToFile("blueMask.png");*/

    }
}
