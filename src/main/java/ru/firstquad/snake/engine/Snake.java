package ru.firstquad.snake.engine;

import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.firstquad.snake.model.Field;
import ru.firstquad.snake.model.SnakeBody;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 13.09.2015.
 */
public class Snake extends SnakeBody implements ActionListener {
    private Field field;
    private static int speed;

    public Snake(int lenght, Scene scene) {
        processHead();
        processBody();
        for (int i = 0; i < lenght; i++)
            addBodyElement();
        addKeyEvent(scene);
    }

    private void processHead() {
        head.setX(300);
        head.setY(300);
        head.setWidth(20);
        head.setHeight(20);
        head.setArcWidth(10);
        head.setArcHeight(10);
        head.setFill(Color.GREEN);
        snake.add(head);
    }

    private void processBody() {
        Rectangle b = new Rectangle();
        b.setWidth(20);
        b.setHeight(20);
        b.setX(head.getX() - b.getWidth() - 2);
        b.setY(head.getY());
        b.setArcWidth(10);
        b.setArcHeight(10);
        b.setFill(Color.GREEN);
        body.add(b);
        snake.addAll(body);
    }

    public void actionPerformed(ActionEvent e) {
        resolveDirection();
        feed();
    }

    private void feed() {
        field.getFood().forEach(food -> {
            if (head.getX() == food.getX() && head.getY() == food.getY()) {
                addBodyElement(food);
                field.scoreUpdate(snake);
            }
        });
    }

    private void resolveDirection() {
        double tmpX = head.getX();
        double tmpY = head.getY();
        if (Direction.LEFT.equals(direction)) {
            head.setX(head.getX() - step);
        } else if (Direction.RIGHT.equals(direction)) {
            head.setX(head.getX() + step);
        } else if (Direction.UP.equals(direction)) {
            head.setY(head.getY() - step);
        } else if (Direction.DOWN.equals(direction)) {
            head.setY(head.getY() + step);
        }
        for (int i = body.size() - 1; i > 0; i--) {
            Rectangle h = body.get(i - 1);
            Rectangle b = body.get(i);
            b.setY(h.getY());
            b.setX(h.getX());
        }
        body.get(0).setX(tmpX);
        body.get(0).setY(tmpY);
    }

    public void addKeyEvent(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.DOWN.equals(event.getCode())) {
                direction = Direction.DOWN;
            } else if (KeyCode.UP.equals(event.getCode())) {
                direction = Direction.UP;
            } else if (KeyCode.LEFT.equals(event.getCode())) {
                if (KeyCode.DOWN.equals(event.getCode()))
                    head.setY(head.getY() + 10);
                direction = Direction.LEFT;
            } else if (KeyCode.RIGHT.equals(event.getCode())) {
                direction = Direction.RIGHT;
            }
        });
    }

    public void addBodyElement() {
        Rectangle b = body.get(body.size() - 1);
        Rectangle bNew = new Rectangle();
        bNew.setWidth(20);
        bNew.setHeight(20);
        bNew.setX(b.getX() - b.getWidth() - 2);
        bNew.setY(b.getY());
        bNew.setArcWidth(10);
        bNew.setArcHeight(10);
        bNew.setFill(Color.GREEN);
        body.add(bNew);
    }

    public void addBodyElement(Rectangle b) {
        b.setFill(Color.DARKGREEN);
        snake.add(b);
        body.add(b);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        Snake.speed = 1000 / speed;
    }
}
