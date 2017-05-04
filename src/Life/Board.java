/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author q
 */
public class Board implements Serializable, Cloneable {
    
    private static Board instance;
    private static boolean toric = false;
    
    private static BoardIO boardIO;
    
    public void saveBoard(){
        boardIO.saveBoard();
    }
    public void loadBoard(){
        boardIO.loadBoard();
    }
    public void saveImage(){
        boardIO.saveImage();
    }
    public int getLivingCellsAmount(){
        return Cell.getAmount();
    }
    
    private Board() {}
    
    private static long generation;
    private static Cell[][] cells;    
    private static final int edge = 200;
    private static int brushSize = 1;

    public void switchToric() {
        toric = !toric;
    }

    public static void incrementBrushSize() {
        if (brushSize <= 31) brushSize += 2;
    }
    
    public static void decrementBrushSize() {
        if (brushSize >= 3) brushSize -= 2;
    }
    
    
    
    

    public void setInstance(Board board){
        instance = board;
    }

    public void setCells(Cell[][] cells) {
        Board.cells = cells;
    }

    public int getEdge() {
        return edge;
    }

    public long getGeneration() {
        return generation;
    }
    
    public void resetGeneration() {
        generation = 0;
    }
    
    public static Board getInstance() {
        if (instance == null)
        {
            instance = new Board();
        }
        return instance;
    }
    
    public void reset(){
        if (Board.getInstance() != null) {
            for (int i = 0; i < Board.getInstance().getEdge(); i++)
                        for (int j = 0; j < Board.getInstance().getEdge(); j++)
                            Board.getInstance().getCells()[i][j].setDead();            
        }
        resetGeneration();
    }
    
    public void fillRandom(){
        if (Board.getInstance() != null) {
            for (int i = 0; i < Board.getInstance().getEdge(); i++)
                        for (int j = 0; j < Board.getInstance().getEdge(); j++)
                            if (Math.random() > 0.4)
                                Board.getInstance().getCells()[i][j].setAlive();
        }
    }
    
    public void init(int[] coordsX, int[] coordsY) {
        boardIO  = new BoardIO();
        generation = 0;
        Board.cells = new Cell[edge][edge];
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < coordsX.length; i++) {
            cells[coordsX[i]][coordsY[i]].setAlive();
        }
    }
    
    private int[][] aliveNeighbours(Cell[][] abc) {
        int[][] table = new int[edge][edge];
        int count = 0;
        
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                if (i > 0 && j > 0 &&               abc[i-1][j-1].isAlive()) count++;
                if (i > 0 &&                        abc[i-1][j].isAlive()) count++;
                if (i > 0 && (j < edge-1) &&        abc[i-1][j+1].isAlive()) count++;
                if (j > 0 &&                        abc[i][j-1].isAlive()) count++;
                if ((j < edge-1) &&                 abc[i][j+1].isAlive()) count++;
                if ((i < edge-1) && j > 0 &&        abc[i+1][j-1].isAlive()) count++;
                if ((i < edge-1) &&                 abc[i+1][j].isAlive()) count++;
                if ((i < edge-1) && (j < edge-1) && abc[i+1][j+1].isAlive()) count++;
                
                if(toric){                    
                    //ПРАВЫЙ КРАЙ
                    if (i == edge-1 && j != 0 && j != edge-1) {
                        if (abc[0][j-1].isAlive()) count++;
                        if (abc[0][j].isAlive()) count++;
                        if (abc[0][j+1].isAlive()) count++;
                    }

                    //ЛЕВЫЙ КРАЙ
                    if (i == 0 && j != 0 && j != edge-1) {
                        if (abc[edge-1][j-1].isAlive()) count++;
                        if (abc[edge-1][j].isAlive()) count++;
                        if (abc[edge-1][j+1].isAlive()) count++;
                    }
                    
                    //ВЕРХНИЙ КРАЙ
                    if (j == 0 && i != 0 && i != edge-1){
                        if (abc[i][edge-1].isAlive()) count++;
                        if (abc[i-1][edge-1].isAlive()) count++;
                        if (abc[i+1][edge-1].isAlive()) count++;
                    }
                    
                    //НИЖНИЙ КРАЙ
                    if (j == edge-1 && i != 0 && i != edge-1){
                        if (abc[i][0].isAlive()) count++;
                        if (abc[i-1][0].isAlive()) count++;
                        if (abc[i+1][0].isAlive()) count++;
                    }
                    
                }
                table[i][j] = count;
                count = 0;
            }
   
        }
        return table;
    }
    
    public BufferedImage render(){
        BufferedImage img;
        img = new BufferedImage(601,601,1);
        
        Graphics g = img.getGraphics();
        
        g.setColor(Color.white);
        g.fillRect(0, 0, 601, 601);
        g.setColor(Color.red);
        
        int x = 3;
        for (int i = 0; i < Board.getInstance().getEdge(); i++) 
            for (int j = 0; j < Board.getInstance().getEdge(); j++) 
                if (Board.getInstance().getCells()[i][j].isAlive()) 
                    g.fillRect(i * x, j * x, x, x);
        
        g.setColor(Color.black);
        g.drawRect(0,0,600,600);

        return img;
    }
    
    
    
    public BufferedImage renderPart(int x1, int y1){
        BufferedImage img;
        int xx = 0, yy = 0;
        img = new BufferedImage(200,200,1);
        
        Graphics g = img.getGraphics();
        
        g.setColor(Color.white);
        g.fillRect(0, 0, 200, 200);
        
        int x = 10;
        for (int i = x1 - 1; i < x1 + 20; i++) {
            for (int j = y1 - 1; j < y1 + 20; j++) {
                if (i < 200 && j < 200 && i > 0 && j > 0) {
                    if (Board.getInstance().getCells()[i][j].isAlive()) {
                        g.setColor(Color.red);
                        g.fillRect(xx * x, yy * x, x, x);
                    }
                }
                else
                {
                    g.setColor(Color.gray);
                    g.fillRect(xx * x, yy * x, x, x);
                }
                yy++;
            }
            xx++; yy=0;
        }
        g.setColor(Color.black);
        g.fillRect(100-brushSize*x/2-5, 100-brushSize*x/2-5, brushSize*x, brushSize*x);
        g.drawRect(0,0,199,199);
        return img;
    }
    
    public void reinit() {
        generation++;
        int[][] table = this.aliveNeighbours(cells);
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                if (cells[i][j].isAlive() && !(table[i][j] == 2 || table[i][j] == 3))
                        cells[i][j].setDead();
                if (!cells[i][j].isAlive() && (table[i][j] == 3))
                        cells[i][j].setAlive();
            } 
        }
    }
    
    public void addCell(int x, int y){
            for (int i = x - brushSize / 2; i <= x + brushSize / 2; i++)
                for (int j = y - brushSize / 2; j <= y + brushSize / 2; j++)
                    if (i < Board.getInstance().getEdge() && i > -1 && j < Board.getInstance().getEdge() && j > -1)
                        cells[i][j].setAlive();          
    }
    
    public void removeCell(int x, int y){
        for (int i = x - brushSize / 2; i <= x + brushSize / 2; i++)
                for (int j = y - brushSize / 2; j <= y + brushSize / 2; j++)
                    if (i < Board.getInstance().getEdge() && i > -1 && j < Board.getInstance().getEdge() && j > -1)
                        cells[i][j].setDead();  
    }
    
    public Cell[][] getCells(){
        return cells;
    }
}
