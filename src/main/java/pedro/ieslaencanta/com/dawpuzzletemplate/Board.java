/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.E;
import javafx.scene.paint.Color;

/**
 * Tablero del juego, posee un fondo (imagen estática, solo se cambia cuando se
 * cambia el nivel), Una bola, un vector de niveles, un disparador y una matriz
 * de bolas gestiona la pulsación de tecla derecha e izquierda
 *
 * @author Pedro
 * @see Bubble Level Shttle BallGrid
 */
public class Board implements IKeyListener {

    private Rectangle2D game_zone;

    private GraphicsContext gc;
    private GraphicsContext bggc;
    private Dimension2D original_size;
    private Bubble ball;
    private Shuttle shuttle;

    private boolean debug;
    private boolean left_press, right_press;

    /**
     * constructor
     *
     * @param original
     */
    public Board(Dimension2D original) {
        this.gc = null;
        this.game_zone = new Rectangle2D(95, 23, 128, 200);
        this.original_size = original;
        this.right_press = false;
        this.left_press = false;
        this.shuttle = new Shuttle(new Point2D(
                        (this.game_zone.getMaxX() - this.game_zone.getWidth() / 2),
                        (this.game_zone.getMaxY() - 20)
                ));
        this.debug = false;

    }

    /**
     * muestra el grid
     */
    private void Debug() {
        this.bggc.setStroke(Color.RED);
        for (int i = 0; i < 12; i++) {
            //se pinta las lineas horizontales

            this.bggc.strokeLine(this.game_zone.getMinX() * Game.SCALE,
                    (this.game_zone.getMinY() + i * 16) * Game.SCALE,
                    (this.game_zone.getMaxX()) * Game.SCALE,
                    (this.game_zone.getMinY() + i * 16) * Game.SCALE);

            //se pintan las líneas verticales desplazando en las impares
            for (int j = 0; j < 8; j++) {
                this.bggc.strokeLine(
                        //inicio x va cambiando entre pares e impares
                        (this.game_zone.getMinX() + j * 16 + ((i % 2 != 0) ? 8 : 0)) * Game.SCALE,
                        //inicio de y, siempre el mismo
                        (this.game_zone.getMinY() + i * 16) * Game.SCALE,
                        //fin de x va cambiando entre pares e impares
                        (this.game_zone.getMinX() + j * 16 + ((i % 2 != 0) ? 8 : 0)) * Game.SCALE,
                        //fin de y es igual en todas
                        (this.game_zone.getMinY() + i * 16 + 16) * Game.SCALE);
            }
        }
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;

    }

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;

    }

    public void setBackGroundGraphicsContext(GraphicsContext gc) {
        this.bggc = gc;
        this.paintBackground();
    }

    /**
     * cuando se produce un evento
     */
    public synchronized void TicTac() {
        this.clear();
        this.render();
        this.process_input();
        //actualizar
        this.update();
        
    }

    private void update() {
        //actualizar el juego
        if (this.ball != null && this.ball.getBalltype() != null) {
            this.ball.move(this.game_zone);
        }

    }

    private void render() {
        if (this.ball != null && this.ball.getBalltype() != null) {
            this.ball.paint(gc);
        }
        if(this.shuttle != null ){
            this.shuttle.paint(gc);
        }

    }

    private void process_input() {
        if (this.left_press) {
            this.shuttle.moveLeft();
        } else if (this.right_press) {
            this.shuttle.moveRight();
        } else {

        }
    }

    /**
     * limpiar la pantalla
     */
    private void clear() {
        this.gc.restore();
        this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }

    /**
     * pintar el fonodo
     */
    public void paintBackground() {
            Resources r = Resources.getInstance();
        Image fondos = r.getImage("fondos");
        //se dibujar el fondo
        this.bggc.drawImage(fondos,
                //inicio de la posicion
                16, //incio en x de la imagen original //16 Segunda mapa   //16                //344
                17, //inicio en y de la imagen origina //280             //Tercer mapa 543     //17 primer mapa Segundo nivel
                         this.original_size.getWidth(), //ancho de la imagen original
                this.original_size.getHeight(), //alto de la imagen original
                //dibujar en el liendozo
                0, //pociión x en el lienzo
                0, //posición y en el lienzo
                this.original_size.getWidth() * Game.SCALE, //ancho en el lienzo
                this.original_size.getHeight() * Game.SCALE); //alto en el lienzo
        if (this.debug) {
            this.Debug();
        }

    }
   /* public void paintShuttle() {
        Resources r = Resources.getInstance();
        Image todos = r.getImage("todos");
        //se dibujar el fondo
        this.bggc.drawImage(todos,
                //inicio de la posicion
                325, //incio en x de la imagen original
                326, //inicio en y de la imagen origina
                65, //ancho de la imagen original 
                40, //alto de la imagen original                                
                //dibujar en el liendozo
                0, //pociión x en el lienzo
                0, //posición y en el lienzo
                65 * Game.SCALE, //ancho en el lienzo
                40 * Game.SCALE); //alto en el lienzo
        if (this.debug) {
            this.Debug();
        }

    }*/

    /**
     * gestión de pulsación
     *
     * @param code
     */
    @Override
    public void onKeyPressed(KeyCode code
    ) {
        switch (code) {
            case LEFT:
                this.left_press = true;
                break;
            case RIGHT:
                this.right_press = true;
                break;
        }
    }
    @Override
    public void onKeyReleased(KeyCode code
    ) {
        switch (code) {
            case LEFT:
                this.left_press = false;
                break;
            case RIGHT:
                this.right_press = false;
                break;
            case ENTER:
                break;
            case SPACE:
                this.shuttle.shoot();
                break;
            case P:

                break;
            case E:
                break;
            case D:
                this.setDebug(!this.debug);
                this.paintBackground();

        }
    }

}
