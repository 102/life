/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Life.GUI;

/**
 *
 * @author q
 */
public interface MMObservable {
    public void addObserver(MMObserver observer);
    public void notifyObservers(int x, int y); 
}
