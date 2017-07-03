/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.visionin;

import basicstuff.ObjectStatus;

/**
 *
 * @author christopherrehm
 */
public class VisionIn extends basicstuff.BasicObject {
    
    int MyId = 003;
   
    @Override
    public void run() {

       ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("visionin cell");
        Thread visionThread = new Thread(myStats);
        visionThread.start(); 
        this.systemMessageStartUp("starting vision in cell");
    }
}
