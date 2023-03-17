/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import static java.lang.System.gc;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Simon Roca
 */
public class BallGrid {

    //sumatorio para despalazar las burbujas
    int sumatorioX = 8;
    int sumatorioY = 8;
    //esquina top izquierda
    private int startx;
    //esquina top derecha   
    private int starty;
    //creamos un bubble de tipo Bubble
    private Bubble bubble;
    //creamos una matriz de Bubbles
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
    //centro de la burbuja
    private int mh = Bubble.HEIGHT / 2;
    private int mw = Bubble.WIDTH / 2;
    
    //constructor defecto
    public BallGrid() {
        this.startx = -1;
        this.starty = -1;
    }

    //constructor sobrecargado
    public BallGrid(int startx, int stary, BubbleType[][] b) {
        //instanciamos

        this.startx = startx;
        this.starty = stary;
        //instancimamos el grid con 12 filas
        this.grid = new Bubble[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            //si es par
            if (i % 2 == 0) {
                //creamos la fila con 9 columnas
                this.grid[i] = new Bubble[8];
                //si es impar
            } else if (i % 2 != 0) {
                //creamos la fila 8 
                this.grid[i] = new Bubble[7];
            }
        }
        //instanciar burbujas, solo hara la primera instancia, luego se llamara a un metodo
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {

                this.grid[i][j] = new Bubble(startx + sumatorioX, stary + sumatorioY, b[i][j]);
                sumatorioX += 16;
            }
            if (i % 2 == 0) {
                //si i es par tomara el valor de 16
                sumatorioX = 16;
            } else {
                //si es impar tomara el valor de 8
                sumatorioX = 8;
            }
            //añadimos a Y para bajar al siguiente nivel
            sumatorioY += 16;
        }
    }
    
   //casi igual que el constructor, pero recibe un parametro más, además de que añadimos un [] más al bubbletype, que correspondera con la fila  
    public void pintarBurbuja(int startx, int stary, BubbleType[][][] b, int fila) {
        int sumatorioX = 8; //sumatorios
        int sumatorioY = 8; //
        //defimos primero la fila de la que queremos pintar, la sacamos de un getter de nivel
        for (int i = 0; i < b[fila].length; i++) {
            for (int j = 0; j < b[fila][i].length; j++) {
                //la pintamos en el comienzo más la mitad de la burbuja en la pocicion x, y en la pocicion y, e por final del bubbletype[viene de nivel][vamos corriendo][contiene los typos]
                this.grid[i][j] = new Bubble(startx + sumatorioX, stary + sumatorioY, b[fila][i][j]);
                //después de que se haya añadido una burbuja se suma 16, para ir a la siguiente pocicion
                sumatorioX += 16;
            }
            //una vez listo con esa columna, comprobamos si i es par o impar, ya que las columnas van de 8 a 7, tambien es un reseteo
            if (i % 2 == 0) {
                //si i es par tomara el valor de 16
                sumatorioX = 16;
            } else {
                //si es impar tomara el valor de 8
                sumatorioX = 8;
            }
            //añadimos a Y para bajar al siguiente nivel
            sumatorioY += 16;
        }
    }

    public boolean collision(Bubble b) {
        boolean collision = false;
        /* comprueba si la parte de abajo del bubble, es menor o igual, a la parte del final del grid */
        int fila, columna;
        //pocicion y del centro de b restamos de comienzo del grid, y lo dividimos entre el hight para obtener la fila
        fila = (int) (b.getPosicion().getY() - this.starty) / Bubble.HEIGHT;
        //lo mismo pero obtenemos la columna
        columna = (int) (b.getPosicion().getX() - this.startx) / Bubble.WIDTH;
        double distancia;
        //colision con la cima
        if (b.getPosicion().getY() - mh <= this.starty) {
            collision = true;
            //paramos la bola
            b.stop();

            //le damos una nueva pocicion al bubble
            b.setPosicion(
                    //creamos una nueva pocicion para b
                    new Point2D(
                            //pocicion cominezo de x, mas la columna actual, multiplicado por el width y añadimos la mitad del bubble para centrar                                              
                            this.startx + columna * Bubble.WIDTH + mw,
                            this.starty + fila * Bubble.HEIGHT + mh));
            //asignamos b a la fila y columna antes calculada
            this.grid[fila][columna] = b;

        } else {
            for (int i = this.grid.length - 1; i >= 0; i--) {
                for (int j = this.grid[i].length - 1; j >= 0; j--) {
                    //ha reccorido el grid, y ha encontrado una pocicion con un bubble
                    if (this.grid[i][j] != null) {
                        //obtenemos la distancia de ese bubble a b
                        distancia = this.grid[i][j].getPosicion().distance(b.getPosicion());
                        //si la distancia es menor que 16, que el el area centro del bubble, ha occurido una collision
                        if (distancia <= 16) {
                            //colison cierto
                            collision = true;
                            b.stop();

                            //si la fila actual es par
                            if (fila % 2 == 0) {
                                b.setPosicion(
                                        new Point2D(
                                                //pocicion cominezo de x, mas la columna actual, multiplicado por el width y añadimos la mitad del bubble para centrar
                                                this.startx + columna * Bubble.WIDTH + mw,
                                                this.starty + fila * Bubble.HEIGHT + mh));
                            } else {
                                //si la fila actual es impar
                                b.setPosicion(
                                        new Point2D(
                                                /*pocicion cominezo de x, mas la columna actual, multiplicado por el width y añadimos la mitad del bubble para centrar
                                                entender mejor lo que hace*/
                                                this.startx + columna * Bubble.WIDTH + mw + mw, //tuve que sumarlo dos veces para que encajara bien
                                                this.starty + fila * Bubble.HEIGHT + mh));

                            }
                            //guradamos b 
                            this.grid[fila][columna] = b;
                        }
                    }
                }
            }
        }
        //devulvemos que ha occurido una collision, board ya se encarga de quitar b
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
    //metodo pintar 
    public void paint(GraphicsContext gc) {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                //si el grid es differente de null, no tiene sentido pintar los nullos, ya que son nullos
                if (this.grid[i][j] != null) {
                    //pintamos el bubbble si la hay
                    this.grid[i][j].paint(gc);
                }
            }
        }
    }

    public void vaciar() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                //si el grid es differente de null, no tiene sentido pintar los nullos, ya que son nullos
                this.grid[i][j] = null;
            }

        }
    }

    //metodo utlizado en el nivel
    public void fillGrid(BubbleType[][] bubble) {
        for (int i = 0; i < bubble.length; i++) {
            for (int j = 0; j < bubble[i].length; j++) {
                if (this.grid[i][j] != null) {
                    this.grid[i][j] = new Bubble(120, 30, bubble[i][j]);
                }
            }
        }
    }

    //debugs balls inside the grid
    /*public String debugTest() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (this.grid[i][j] != null) {
                    return "This is the pocicion of y and x of " + this.grid[i][j] + this.grid[i][j].getPosicion().getY() + "this is the pocicion of X" +this.grid[i][j].getPosicion().getX() + 
                            "this is the BallType of " + this.grid[i][j].getBalltype();
                }
            }
        }
    }*/
    public void debugTest(Bubble b) {
        if (b != null) {
            System.out.println("this b has this pocicion on the gird " + "ball X pocicion " + b.getPosicion().getX() + " ball Y pocicion " + b.getPosicion().getY());
        }
    }
    
    //getters y setters
    
    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }
}
