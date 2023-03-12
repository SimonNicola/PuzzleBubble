/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import static java.lang.System.gc;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Simon Roca
 */
public class BallGrid {

    private int startx;
    private int starty;
    private Bubble bubble;
    private Bubble grid[][];
    //12 filas de burbujas
    private static int ROWS = 12;
    //8 columnas de burbujas
    private static int COLS = 8;
    //minimo de numero de pelotas que van a explotar
    private static int MIN_BALLS = 3;
    boolean debug;
    //matriz bubbleTypes
    private BubbleType bubbleTypes[][];
    //define la fila de las burbujas
    private int lineBubble = 3;

    //constructor sobrecargado
    public BallGrid(int startx, int stary) {
        this.startx = startx;
        this.starty = stary;
        this.grid = new Bubble[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            if (i % 2 == 0) {
                //creamos la fila con 9 columnas
                this.grid[i] = new Bubble[9];
            } else if (i % 2 != 0) {
                //creamos la fila con 8 columnas
                this.grid[i] = new Bubble[8];
            }
        }
    }

    public BallGrid(int startx, int stary, BubbleType bubbleTypes[][]) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < BallGrid.COLS; j++) {
                this.grid[i][j] = new Bubble(this.startx, this.starty, bubbleTypes[i][j]);
            }
        }
    }

    public boolean collision(Bubble b) {
         boolean collision= false;
       
           for(int j = 0; !collision;j++){
                   if(grid[5][j].getPosicion() == b.getPosicion()){
                       b.stop();
                       b.setPosicion(grid[5][j].getPosicion());
                       collision=true;
                   }
                }
             return collision;
        
        }

        /* boolean collision = false;
        for (int i = grid.length-1; i >= 0 && !collision; i--) {
            for (int j = this.grid[i].length-1; j >=0  && !collision; j--) {
                if(this.grid[11][j] == null){
                    
                }
            }
        }
        return collision;
    */
       
    
    
    public void paint(GraphicsContext gc){
        for(int i=0;i<this.grid.length;i++){
            for(int j=0;j<this.grid[i].length;j++){
                this.grid[i][j].paint(gc);
            }
        }
    }
}
