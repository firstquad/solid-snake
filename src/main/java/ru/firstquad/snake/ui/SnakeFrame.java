package ru.firstquad.snake.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.firstquad.snake.engine.Snake;
import ru.firstquad.snake.model.Field;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;


/**
 * Created by Dima on 05.09.2015.
 */
public class SnakeFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SolidSnake");
        Group group = new Group();
        Scene scene = new Scene(group, 1200, 800);
        scene.setRoot(group);

        Snake snake = new Snake(7, scene);
        snake.setSpeed(10);
        Field field = new Field(snake, group, scene);
        field.addFood(10);
        primaryStage.setScene(scene);
        primaryStage.show();
        doAnimation(snake);
    }

    private void doAnimation(Snake snake) {
        Timer timer = new Timer(snake.getSpeed(), snake);
        timer.addActionListener(new DefaultEditorKit.DefaultKeyTypedAction());
        timer.start();
    }

}
