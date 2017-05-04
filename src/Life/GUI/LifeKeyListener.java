/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Life.GUI;

import Life.Board;
import java.awt.event.KeyEvent;

/**
 *
 * @author q
 */
public class LifeKeyListener implements java.awt.event.KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') Main.switchPaused();
        if (e.getKeyChar() == 's') Board.getInstance().reinit();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
