/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Life.GUI;

import Life.Board;
import Life.BoardIO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author q
 */
public class ButtonPanel extends javax.swing.JPanel {
    private static boolean toricSw = false;

    public ButtonPanel() {
        this.setPreferredSize(new Dimension(200,300));
        this.setLayout(new GridLayout(0,1));
        final JButton pause = new JButton("Pause");//
        final JButton reset = new JButton("Reset");//
        final JButton saveBoard = new JButton("Save board"); //
        final JButton loadBoard = new JButton("Load board"); //
        final JButton saveAsImage = new JButton("Save as image"); //
        final JButton toric = new JButton("Enable torical field"); //
        final JButton step = new JButton("Step");//
        final JButton random = new JButton("Fill randomly");//
        
        toric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board.getInstance().switchToric();
                if (toricSw) toric.setText("Enable torical field");
                else toric.setText("Disable torical field");
                toricSw = !toricSw;
            }
        });
        saveBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().saveBoard();}}
        );
        loadBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().loadBoard();}}
        );
        saveAsImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().saveImage();}}
        );
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Main.switchPaused();}}
        );
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().reset();}}
        );
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().fillRandom();}}
        );
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Board.getInstance().reinit();}}
        );
 
        this.add(pause);
        this.add(reset);
        this.add(step);
        this.add(toric);
        this.add(saveBoard);
        this.add(loadBoard);
        this.add(saveAsImage);
        this.add(random);
    }
}
