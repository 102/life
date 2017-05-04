/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Life.GUI;

import Life.Board;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author q
 */
public class ZoomPanel extends JLabel implements MMObserver{
    private int zx, zy;

    @Override
    public void handleEvent(int x, int y) {
        zx = x;  zy = y;
        ImageIcon imgicon = new ImageIcon(Board.getInstance().renderPart(zx, zy));
        this.setIcon(imgicon);
    }
    
    @Override
    public void handleEvent(){
        ImageIcon imgicon = new ImageIcon(Board.getInstance().renderPart(zx, zy));
        this.setIcon(imgicon);
    }

    public ZoomPanel() {
    }
}
