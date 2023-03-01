/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Simon Roca
 */
public class Shuttle {

    private float angle;
    private Bubble bubble;
    private Bubble nextBubble;
    private static float angelInc = 180.0f / 126.0f;
    private Point2D center;
    private static final float MIN_ANGLE = 0.0f;
    private static final float MAX_ANGLE = 180.0f;
    private boolean debug;
    private int row = 3;
    private int column = 975;
    private int change = 65;

    public Shuttle(Point2D center) {
        this.center = center;
        //cambiar el bubbletype al aleatorio, BubbleType.values() podemos introducir un valor
        this.bubble = new Bubble((double) this.center.getX(), this.center.getY(), BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        this.nextBubble = new Bubble((double) this.center.getX() - 20, this.center.getY() + 10, BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        this.angle = 90.0f;
    }

    //bubble tiene dos constructores uno por defecto y uno sobrecargado
    /* public Bubble bubblesCreate(){
        //creando un bubble
        Bubble bubble; 
        //Instanciando bubble,usando el constructor por defecto
        bubble = new Bubble(); 
        //Dando un tipo a la bala
        this.bubble.setBalltype(BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        //Devuelvo la bala
        return  bubble;
    } */
    //instanciar un bubble, Bubble(double x, double y, BubbleType balltype)
    public Bubble shoot() {
        //asingnamos el bubble a la variable
        Bubble tempo = this.bubble;
        //asignamos la nextBubble a la actual
        this.bubble = this.nextBubble;
        //pocicion de la actual al centro del shuttle
        this.bubble.setPosicion(center);
        //añadir un angulo a la bala
        tempo.addAngulo(360 - angle);
        //instanciamos una nueva bubble con una pocicion, y un bubbleType aleatorio
        this.nextBubble = new Bubble((double) this.center.getX() - 20, this.center.getY() + 10, BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        //devuelvo la bubble
        return tempo;
    }

    public void moveRight() {
        //incrementamos el angulo
        this.angle -= angelInc;
        this.column += this.column + 65;
        //condicion de si es menor que el angulo minimo se iguala al angulo minimo
        if (this.angle < Shuttle.MIN_ANGLE) {
            this.angle = Shuttle.MIN_ANGLE;
        }
    }

    public void moveLeft() {
        this.angle += angelInc;
        this.column -= this.column - 65;
        if (this.angle > Shuttle.MAX_ANGLE) {
            this.angle = Shuttle.MAX_ANGLE;
        }

    }

    public void paint(GraphicsContext gc) {
        Resources r = Resources.getInstance();
        gc.drawImage(r.getImage("spriters"),
                //inicio de la posicion
                3,
                1805,
                60,
                40,
                //dibujar en el lienzo
                (this.center.getX() - 60 / 2) * Game.SCALE,
                (this.center.getY() - 40 / 2) * Game.SCALE,
                61 * Game.SCALE,
                40 * Game.SCALE);
        if (this.bubble != null) {
            this.bubble.paint(gc);
        }
        if (this.nextBubble != null) {
            this.nextBubble.paint(gc);
        }
        //si se esta depurando
        if (this.debug) {
            gc.setStroke(Color.GREEN);
            gc.strokeText(this.angle + "º x:" + this.center.getX() + "y:" + this.center.getY(), (this.center.getX()) * Game.SCALE, (this.center.getY()) * Game.SCALE);
        }
    }

    //pintando el arrow
    public void paintArrow(GraphicsContext gc) {
        Resources r = Resources.getInstance();
        gc.drawImage(r.getImage("arrow"),
                //inicio de la posicion
                this.column,
                this.row,
                60,
                64,
                (this.center.getX() - 60 / 2) * Game.SCALE,
                (this.center.getY() - 64 / 2) * Game.SCALE,
                60 * Game.SCALE,
                64 * Game.SCALE);
        //si se esta depurando
        if (this.debug) {
            gc.setStroke(Color.GREEN);
            gc.strokeText(this.angle + "º x:" + this.center.getX() + "y:" + this.center.getY(), (this.center.getX()) * Game.SCALE, (this.center.getY()) * Game.SCALE);
        }
    }

    public float getAngle() {
        return angle;
    }

}
