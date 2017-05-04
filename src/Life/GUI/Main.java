package Life.GUI;

import Life.Board;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static boolean running;
    public static String NAME = "Life";
    private static boolean paused = false;
    private static ZoomPanel zoomPanel = new ZoomPanel();
    private static BoardPanel boardPanel = new BoardPanel();

    public static void switchPaused() {
        paused = !paused;
    }

    @Override
    public void run() {
        paused = false;
        Board board = Board.getInstance();

        int[] a = {
            0, 0, 1, 1, 10, 10, 10, 11, 11, 12, 13, 12, 13, 14, 15, 15, 16, 16, 16, 17, 20, 20, 20, 21, 21, 21, 22, 22, 24, 24, 24, 24, 34, 34, 35, 35,
            11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19,
            53 + 7, 53 + 7, 54 + 7, 54 + 7, 56 + 7, 57 + 7, 57 + 7, 58 + 7, 58 + 7, 59 + 7, 59 + 7, 61 + 7, 61 + 7, 62 + 7, 62 + 7,
            92, 92, 92, 93, 93, 94, 95, 95, 96, 96, 97, 98, 98,
            92, 92, 92, 93, 93, 94, 95, 95, 96, 96, 97, 98, 98,
            60, 61, 60, 61,
            49, 41, 40, 41, 42, 43, 43,
            100, 101, 101, 101, 102,
            100, 101, 101, 103, 104, 105, 106
        };
        int[] b = {
            4, 5, 4, 6, 4, 5, 6, 3, 7, 2, 2, 8, 8, 5, 3, 7, 4, 5, 6, 5, 2, 3, 4, 2, 3, 4, 1, 5, 0, 1, 5, 6, 2, 3, 2, 3,
            50, 51, 52, 53, 54, 55, 57, 58, 50, 51, 52, 53, 54, 55, 57, 58, 57, 58, 50, 51, 57, 58, 50, 51, 57, 58, 50, 51, 57, 58, 50, 51, 50, 51, 53, 54, 55, 56, 57, 58, 50, 51, 53, 54, 55, 56, 57, 58,
            54, 55, 54, 55, 55, 54, 56, 53, 56, 52, 55, 52, 55, 54, 55,
            32, 33, 34, 31, 34, 34, 30, 34, 30, 34, 34, 31, 33,
            72, 73, 74, 71, 74, 74, 70, 74, 70, 74, 74, 71, 73,
            40, 40, 41, 41,
            23, 23, 24, 25, 25, 25, 26,
            101, 100, 101, 102, 100,
            102, 102, 100, 101, 102, 102, 102
        };
        board.init(a, b);
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                if (!paused) {
                    Board.getInstance().reinit();
                }
                boardPanel.handleEvent();
                zoomPanel.handleEvent();
            }
        };
        new java.util.Timer().scheduleAtFixedRate(task, 0, 85);
    }


    public void start() {
        running = true;
        new Thread(this).start();
    }

    public static void main(String[] args) {
        Main game = new Main();        
        
        LifeMouseListener lml = new LifeMouseListener();
        
        boardPanel.addMouseMotionListener(lml);
        boardPanel.addMouseListener(lml);
        boardPanel.addMouseWheelListener(lml);
        
        

        game.setPreferredSize(new Dimension(602, 602));

        JFrame frame = new JFrame(Main.NAME);
        JPanel butPanel = new ButtonPanel();
        
        frame.addKeyListener(new LifeKeyListener());
        lml.addObserver(zoomPanel);

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setAlwaysOnTop(true);
        frame.add(game);
        frame.add(butPanel);
        frame.add(zoomPanel);
        frame.add(boardPanel);
      
        Dimension size = boardPanel.getPreferredSize();
        Dimension butPanelSize = butPanel.getPreferredSize();
        boardPanel.setBounds(5,5,size.width,size.height);
        butPanel.setBounds(613,3,butPanelSize.width,butPanelSize.height);
        zoomPanel.setBounds(613,407,200,200);
        boardPanel.setVisible(true);
        
        butPanel.setFocusable(false);

        frame.setSize(824, 637);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        
        game.start();
    }
}
