package ru.firstquad.snake.model;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import ru.firstquad.snake.engine.Snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 14.09.2015.
 */
public class Field {
    private List<Rectangle> food = new ArrayList<>();
    private Group group;

    public Field(Snake snake, Group group, Scene scene) {
        this.group = group;
        this.group.getChildren().addAll(snake.getHead());
        this.group.getChildren().addAll(snake.getBody());
        scene.setRoot(group);
        snake.setField(this);
    }

    public List<Rectangle> getFood() {
        return food;
    }

    public void addFood(int count) {
        for (int i = 0; i < count; i++) {
            food.add(Food.create(group));
        }
    }

    public void setFood(List<Rectangle> food) {
        this.food = food;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
