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
   
    
     public BubbleType[][] nivelUno;
    /* public BubbleType[][] nivelUno = {
        //matriz de burbujas
        {type.BLUE, type.GRAY, type.GREEN, type.PURPLE, type.ORANGE, type.PURPLE, type.GRAY, type.ORANGE},
        {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE},
        {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE,type.ORANGE}
    };
   
    
    public BubbleType[][] nivelTres = {
        //matriz de burbujas
        {type.BLUE, type.GRAY, type.GREEN, type.PURPLE, type.ORANGE, type.PURPLE, type.GRAY, type.ORANGE},
        {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE}
    };
    
    public BubbleType[][] nivelCuatro = {
        //matriz de burbujas
        {type.BLUE, type.GRAY, type.GREEN, type.PURPLE, type.ORANGE, type.PURPLE, type.GRAY, type.ORANGE},
        {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE}
    };
    
    public BubbleType[][] nivelCinco = {
        //matriz de burbujas
        {type.BLUE, type.GRAY, type.GREEN, type.PURPLE, type.ORANGE, type.PURPLE, type.GRAY, type.ORANGE},
        {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE}
    }; */
    
    

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

    public Nivel(int x, int y) {
        columnaX = x;
        columnaY = y;
        //this.grid.fillGrid(bubbleNivel);
        nivelUno = new BubbleType[][] {
            //matriz de burbujas
            {type.BLUE, type.GRAY, type.GREEN, type.PURPLE, type.ORANGE, type.PURPLE, type.GRAY, type.ORANGE},
            {type.GREEN, type.ORANGE, type.ORANGE, type.GRAY, type.ORANGE, type.GRAY, type.PURPLE, type.ORANGE}
        };

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
