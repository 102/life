/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Life;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author student
 */
public class BoardIO {

    public void loadBoard() {
        try {
            FileInputStream fileIn = new FileInputStream("saved.brd");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Board.getInstance().setCells((Cell[][]) in.readObject());
        } catch (IOException | ClassNotFoundException ex) {
        }

    }

    public void saveBoard() {
        File file = new File("saved.brd");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Board.getInstance().getCells());
        } catch (IOException ex) {
        }
    }

    public void saveImage() {

        int i = 1;
        File file = new File("capture" + Integer.toString(i) + ".png");

        while (file.exists()) {
            i++;
            file = new File("capture" + Integer.toString(i) + ".png");
        }

        try {
            ImageIO.write(Board.getInstance().render(), "png", file);
        } catch (IOException ex) {
        }
    }
}
