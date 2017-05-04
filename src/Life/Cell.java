/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Life;

import java.io.Serializable;

/**
 *
 * @author q
 */
public class Cell implements Serializable {
    private boolean alive;
    private static int amount;

    public Cell() {
        this.alive = false;
            Cell.amount = 0;
    }

    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive() {
        if (!this.isAlive()){
        this.alive = true;
        amount++;
        }
    }
    
    public void setDead() {
        if(this.isAlive()){
        this.alive = false;
        if(amount > 0) amount--;
        }
    }
    
    public static int getAmount() {
        return amount;
    }
}
