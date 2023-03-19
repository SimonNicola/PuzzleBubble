/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Dimension2D;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Simon Roca
 */
public class Nivel {

    private int fila = 0;
    private int columnaX;
    private int columnaY;
    public BallGrid grid;
    //para poder acceder a los tipos de burbujas
    public BubbleType type;
    //grid de burbjuas para los niveles
    public Bubble nivelGrid[][];
    //matriz de typos

    BubbleType[][][] nivelUno = {
        //matriz de burbujas
        //nivel uno
        {
            {type.BLUE, type.BLUE, type.GREEN, type.BLUE, type.BLUE, type.BLUE, type.GRAY, type.BLUE},
            {type.GREEN, type.ORANGE, type.BLUE, type.GRAY, type.ORANGE, type.GRAY, type.BLUE}
        },
        { //nivel dos
            {type.BLUE, type.GRAY, type.GREEN, type.GRAY, type.GRAY, type.PURPLE, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.GRAY, type.GRAY, type.GRAY, type.PURPLE},
            {type.GREEN, type.GRAY, type.ORANGE, type.GRAY, type.GRAY, type.GRAY, type.GRAY, type.ORANGE}

        },
        { //nivel tres
            {type.GREEN, type.WRITE, type.ORANGE, type.WRITE, type.BLUE, type.RED, type.YELLOW, type.WRITE},
            {type.RED, type.RED, type.BLUE, type.GREEN, type.RED, type.GREEN, type.YELLOW},
            {type.ORANGE, type.GREEN, type.WRITE, type.PURPLE, type.GRAY, type.GREEN, type.YELLOW, type.ORANGE},
            {type.PURPLE, type.RED, type.GREEN, type.WRITE, type.RED, type.BLUE, type.PURPLE}

        },
        //nivel cuatro
        {
            {type.GREEN, type.WRITE, type.ORANGE, type.WRITE, type.BLUE, type.RED, type.YELLOW, type.WRITE},
            {type.RED, type.RED, type.BLUE, type.GREEN, type.RED, type.GREEN, type.YELLOW},
            {type.ORANGE, type.GRAY, type.RED, type.PURPLE, type.GRAY, type.ORANGE, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.PURPLE, type.GRAY, type.GRAY, type.RED, type.RED}
        },
        //nivel cinco
        {
            {type.GREEN, type.WRITE, type.ORANGE, type.WRITE, type.BLUE, type.RED, type.YELLOW, type.WRITE},
            {type.GREEN, type.RED, type.WRITE, type.PURPLE, type.GRAY, type.GRAY, type.GREEN},
            {type.YELLOW, type.ORANGE, type.PURPLE, type.GRAY, type.BLUE, type.GREEN, type.GRAY, type.ORANGE},
            {type.YELLOW, type.ORANGE, type.WRITE, type.GRAY, type.BLUE, type.GRAY, type.RED}
        },
        //nivel seis
        {
            {type.WRITE, type.WRITE, type.YELLOW, type.RED, type.PURPLE, type.BLUE, type.BLUE, type.YELLOW},
            {type.BLUE, type.GREEN, type.PURPLE, type.GRAY, type.GREEN, type.ORANGE, type.GREEN},
            {type.BLUE, type.RED, type.RED, type.BLUE, type.GREEN, type.GRAY, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.YELLOW, type.BLUE, type.PURPLE, type.RED},
            {type.GRAY, type.ORANGE, type.RED, type.YELLOW, type.GRAY, type.RED, type.RED, type.GRAY}
        },
        //nivel siete
        {
            {type.BLUE, type.GRAY, type.WRITE, type.RED, type.GRAY, type.PURPLE, type.GREEN, type.RED},
            {type.GREEN, type.GREEN, type.WRITE, type.PURPLE, type.RED, type.PURPLE, type.ORANGE},
            {type.PURPLE, type.GRAY, type.RED, type.RED, type.ORANGE, type.GRAY, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.YELLOW, type.GRAY, type.RED, type.RED},
            {type.PURPLE, type.ORANGE, type.YELLOW, type.GRAY, type.GREEN, type.GRAY, type.RED, type.RED}
        },
        //nivel ocho
        {
            {type.BLUE, type.GRAY, type.WRITE, type.RED, type.GRAY, type.GREEN, type.GRAY, type.PURPLE},
            {type.RED, type.YELLOW, type.GREEN, type.WRITE, type.PURPLE, type.RED, type.PURPLE},
            {type.ORANGE, type.GRAY, type.GREEN, type.WRITE, type.GRAY, type.ORANGE, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.GRAY, type.YELLOW, type.GRAY, type.RED},
            {type.GRAY, type.ORANGE, type.YELLOW, type.BLUE, type.GRAY, type.BLUE, type.RED, type.GRAY},
            {type.YELLOW, type.ORANGE, type.GRAY, type.GRAY, type.ORANGE, type.GRAY, type.RED}
        },
        //nivel nueve
        {
            {type.BLUE, type.GRAY, type.WRITE, type.RED, type.GRAY, type.PURPLE, type.GREEN, type.RED},
            {type.GREEN, type.GREEN, type.WRITE, type.PURPLE, type.RED, type.PURPLE, type.ORANGE},
            {type.PURPLE, type.GRAY, type.RED, type.RED, type.ORANGE, type.GRAY, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.YELLOW, type.GRAY, type.RED, type.RED},
            {type.PURPLE, type.ORANGE, type.YELLOW, type.GRAY, type.GREEN, type.GRAY, type.RED, type.RED},
            {type.GRAY, type.ORANGE, type.GRAY, type.YELLOW, type.GRAY, type.RED, type.RED}
        },
        //nivel dies
        {
            {type.BLUE, type.GRAY, type.WRITE, type.RED, type.GRAY, type.GREEN, type.GRAY, type.PURPLE},
            {type.RED, type.YELLOW, type.GREEN, type.WRITE, type.PURPLE, type.RED, type.PURPLE},
            {type.ORANGE, type.GRAY, type.GREEN, type.WRITE, type.GRAY, type.ORANGE, type.GRAY, type.ORANGE},
            {type.GRAY, type.ORANGE, type.GRAY, type.GRAY, type.YELLOW, type.GRAY, type.RED},
            {type.GRAY, type.ORANGE, type.YELLOW, type.BLUE, type.GRAY, type.BLUE, type.RED, type.GRAY},
            {type.YELLOW, type.ORANGE, type.GRAY, type.GRAY, type.ORANGE, type.GRAY, type.RED}
        }

    };

    public int[][] primerNivel = {
        //matriz de coordenadas de niveles
        {16, 17},
        {344, 17},
        {672, 17},
        {17, 280},
        {344, 280},
        {672, 280},
        {17, 544},
        {344, 544},
        {672, 544},
        //ultimo nivel se tendria que mover hacia abajo
        {1000, 16}
    };

    public Nivel(int x, int y, int fila) {
        columnaX = x;
        columnaY = y;
        this.fila = fila;
        //this.grid.fillGrid(bubbleNivel);

    }

    public Nivel() {
        columnaX = -1;
        columnaY = -1;
        this.fila = -1;
        //this.grid.fillGrid(bubbleNivel);

    }

    public void aumentoFila() {
        if (this.fila < 9) {
            this.fila++;
        }
    }

    public void decrementoFila() {
        if (this.fila > 0) {
            this.fila--;
        }
    }

  
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumnaX() {
        return columnaX;
    }

    public void setColumnaX(int columnaX) {
        this.columnaX = columnaX;
    }

    public int getColumnaY() {
        return columnaY;
    }

    public void setColumnaY(int columnaY) {
        this.columnaY = columnaY;
    }

}
