package View;

import View.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;

public class PlayerView {
    Image directionArrow;
    Sprite sprite;
    ImageView PlayerDirectionArrow;
    GraphicsContext graphicsContext;

    /**
     *  Affichage de la flèche du joueur
     */
    void display(double x, double y, double angle)
    {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotateArrow(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
        graphicsContext.drawImage(directionArrow, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    private void rotateArrow(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Constructeur de l'affichage du joueur
     *
     * @param gc ContextGraphic dans lequel on va afficher le joueur
     * @param y position joueur
     * @param x position joueur
     */
    PlayerView(GraphicsContext gc, int x, int y, String _side) {
        // Tous les joueurs commencent au centre du canvas,
        graphicsContext = gc;

        // On charge la representation du joueur
        if(_side=="top") {
            directionArrow = new Image("assets/PlayerArrowDown.png");
        }
        else {
            directionArrow = new Image("assets/PlayerArrowUp.png");
        }

        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);

        Image tilesheetImage = new Image("assets/orc.png");
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), _side);
        sprite.setX(x);
        sprite.setY(y);
        //directionArrow = sprite.getClip().;

    }


    void spriteAnimate(double x, double y){
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(x);
        sprite.setY(y);
    }

}