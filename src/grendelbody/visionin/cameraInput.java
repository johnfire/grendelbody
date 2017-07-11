/*
 * Copyright (C) 2017 christopherrehm.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package grendelbody.visionin;

import com.hopding.jrpicam.*;
import com.hopding.jrpicam.enums.Exposure;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class cameraInput extends Thread /*throws FailedToRunRaspistillException*/ {
    int theTime;
    RPiCamera piCamera;
    LocalTime myTime;
    
    public void cameraInput() throws FailedToRunRaspistillException{
        
        // Create a Camera that saves images to the Pi's Pictures directory.
        
        piCamera = new RPiCamera("home/rawFotos");
    
        piCamera.setWidth(800).setHeight(600)// Set Camera to produce 500x500 images.
        .setBrightness(75)                // Adjust Camera's brightness setting.
        .setExposure(Exposure.AUTO)       // Set Camera's exposure.
        .setTimeout(2)                    // Set Camera's timeout.
        .setAddRawBayer(true);            // Add Raw Bayer data to image files created by Camera.
        
        // Sets all Camera options to their default settings, overriding any changes previously made.
        //piCamera.setToDefaults();
   
    }
    
    @Override
    public void run(){
        try {
            myTime = java.time.LocalTime.now();
            piCamera.takeStill(myTime + ".jpg");
            Thread.sleep(1000);
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(cameraInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
