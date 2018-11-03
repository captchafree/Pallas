package com.pallas.Testing;

import com.pallas.EditingAlgorithms.*;
import com.pallas.ImageLoading.GifGenerator;
import com.pallas.ImageLoading.PImage;

import java.net.URL;

//TODO: Create object that takes in new image size (Must be smaller or equal to current size) and seam carve the image to match the new size.
public class Driver {

    public static void main(String[] args) throws Exception {
        final String saban = "http://2f13yq12csmv2yraq925m73i-wpengine.netdna-ssl.com/wp-content/uploads/2011/03/sabanstatue1.jpg";
        final String spiral = "https://www.researchgate.net/profile/Neil_Harrison/publication/5608780/figure/fig1/AS:214351891755010@1428116881418/High-contrast-flashing-checkerboard-task-used-as-a-potent-stimulus-of-primary-visual.png";
        final String moraine = "https://polarsteps.s3.amazonaws.com/u_84958/60661467-16dc-46a4-9fdc-6b5f9b31f2ff_big-thumbnail84958_047540470239152066_1512552969480";
        final String rio = "https://www.cruisebe.com/sites/default/files/portofcallobject/commons/a/a1/1_cristor_redentor_2014.jpg";
        final String person = "https://images.unsplash.com/photo-1496144809960-342ada684885?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=dbfa2c27cb7b32ecccf19d2b89b052e0&auto=format&fit=crop&w=2250&q=80";
        final String castle = "https://upload.wikimedia.org/wikipedia/commons/c/cb/Broadway_tower_edit.jpg";

        PImage img = new PImage(new URL(person));

        GifGenerator generator = new GifGenerator("Live_Carve_Person.gif", img.getImage().getType(), 50, true);
        generator.createGif(img, new SeamCarveVertical(), 50);

        img.saveToFile("person_carved.png");
    }
}
