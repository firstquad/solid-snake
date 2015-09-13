package ru.firstquad.snake.model;

import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 13.09.2015.
 */
public abstract class SnakeBody {
    protected Rectangle head = new Rectangle();
    protected List<Rectangle> body = new ArrayList<Rectangle>();
    protected Direction direction = Direction.RIGHT;
    protected double step = 22;

    public Rectangle getHead() {
        return head;
    }

    public void setHead(Rectangle head) {
        this.head = head;
    }

    public List<Rectangle> getBody() {
        return body;
    }

    public void setBody(List<Rectangle> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
