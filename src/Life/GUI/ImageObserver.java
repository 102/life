/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Life.GUI;

import java.awt.Image;

/**
 *
 * @author student
 */
public class ImageObserver implements java.awt.image.ImageObserver{

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
    
}
