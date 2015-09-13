package ru.firstquad.snake.model;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ru.firstquad.snake.engine.Snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 14.09.2015.
 */
public class Field {
    private List<Rectangle> food = new ArrayList<>();
    private Group group;
    private Integer score = 0;
    private Text scoreInfo = new Text();

    public Field(Snake snake, Group group, Scene scene) {
        this.group = group;
        this.group.getChildren().addAll(snake.getHead());
        this.group.getChildren().addAll(snake.getBody());
        scene.setRoot(group);
        snake.setField(this);
        addScore(group);
    }

    public void addScore(Group group) {
        scoreInfo.setX(900);
        scoreInfo.setY(50);
        scoreInfo.setText("Score: ");
        scoreInfo.setFill(Color.BLUE);
        scoreInfo.setFont(Font.font(null, FontWeight.NORMAL, 30));
        group.getChildren().addAll(scoreInfo);
    }

    public void scoreUpdate(List<Rectangle> snake) {
        scoreInfo.setText("Score: " + snake.size());
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
