/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Life.GUI;

import Life.Board;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class LifeMouseListener implements MMObservable, java.awt.event.MouseListener, java.awt.event.MouseMotionListener, java.awt.event.MouseWheelListener{

    private ArrayList<MMObserver> observers = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isControlDown()) Board.getInstance().addCell(e.getX()/3, e.getY()/3);
        else Board.getInstance().removeCell(e.getX()/3, e.getY()/3);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!e.isControlDown()) Board.getInstance().addCell(e.getX()/3, e.getY()/3);
        else Board.getInstance().removeCell(e.getX()/3, e.getY()/3);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!e.isControlDown()) Board.getInstance().addCell(e.getX()/3, e.getY()/3);
        else Board.getInstance().removeCell(e.getX()/3, e.getY()/3);
        notifyObservers(e.getX()/3 - 8, e.getY()/3 - 8);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        notifyObservers(e.getX()/3 - 8, e.getY()/3 - 8);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) Board.incrementBrushSize();
        else Board.decrementBrushSize();        
    }

    @Override
    public void addObserver(MMObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(int x, int y) {
        for (MMObserver observer: observers)
            observer.handleEvent(x, y);
    }
    
}
