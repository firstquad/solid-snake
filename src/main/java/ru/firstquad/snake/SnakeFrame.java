package ru.firstquad.snake;

import com.sun.javafx.scene.traversal.Direction;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dima on 05.09.2015.
 */
public class SnakeFrame extends Application implements ActionListener {
    Rectangle head = new Rectangle();
    List<Rectangle> body = new ArrayList<Rectangle>();
    Direction direction = Direction.RIGHT;
    double step = 22;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SolidSnake");


        head.setX(500);
        head.setY(300);
        head.setWidth(20);
        head.setHeight(20);
        head.setArcWidth(10);
        head.setArcHeight(10);
        head.setFill(Color.GREEN);

        Rectangle b = new Rectangle();
        b.setWidth(20);
        b.setHeight(20);
        b.setX(head.getX() - b.getWidth() - 2);
        b.setY(head.getY());
        b.setArcWidth(10);
        b.setArcHeight(10);
        b.setFill(Color.GREEN);

        body.add(b);
        addBodyElement();
        addBodyElement();
        addBodyElement();
        addBodyElement();

        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800, Color.WHITE);
        root.getChildren().addAll(head);
        body.forEach(root.getChildren()::add);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
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
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        Timer timer = new Timer(500, this);
        timer.addActionListener(new DefaultEditorKit.DefaultKeyTypedAction());
        timer.start();
    }

    private void addBodyElement() {
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

    public void actionPerformed(ActionEvent e) {
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
}
