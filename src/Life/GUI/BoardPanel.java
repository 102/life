/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Life.GUI;

import Life.Board;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author q
 */
public class BoardPanel extends JLabel {

    public void handleEvent() {
        ImageIcon imgicon = new ImageIcon(Board.getInstance().render());
        this.setIcon(imgicon);
    }

    public BoardPanel() {
        this.setPreferredSize(new Dimension(602,602));
    }

}
