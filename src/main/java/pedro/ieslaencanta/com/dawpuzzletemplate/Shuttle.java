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
    private static float angelInc = 180.0f/126.0f;
    private Point2D center;
    private static final  float  MIN_ANGLE = 0.0f;
    private static final float MAX_ANGLE = 180.0f;
    private boolean debug;

    
    
    public Shuttle(Point2D center) {
        this.center = center;
        this.bubble = bubble; 
        this.nextBubble = nextBubble;
        this.angle = 0.0f;
    }

    public Bubble bubblesP(){
        //creando un bubble
        Bubble bubble; 
        //Instanciando bubble
        bubble = new Bubble(); 
        //Dando un tipo a la bala
        this.bubble.setBalltype(BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        //Devuelvo la bala
        return  bubble;
    }
    
    public Bubble shoot(){
        Bubble bubble = this.bubble;
        this.nextBubble = bubblesP();
        
    }
    
    
   public void moveRight(){
       this.angle -= angelInc;
       if(this.angle < Shuttle.MIN_ANGLE){
           this.angle=Shuttle.MIN_ANGLE;
       }
   }
   
    public void moveLeft(){
       this.angle += angelInc;
       if(this.angle > Shuttle.MAX_ANGLE){
           this.angle= Shuttle.MAX_ANGLE;
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
        //si se esta depurando
        if (this.debug) {
      
            gc.setStroke(Color.GREEN);
            gc.strokeText(this.angle + "º x:" + this.center.getX() + "y:" + this.center.getY(), (this.center.getX()) * Game.SCALE, (this.center.getY()) * Game.SCALE);
        }
    }

    
}
