package com.kodilla.tictactoe;

import javafx.scene.image.Image;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TicTacToeApplication {

    private Image imageback = new Image("file:resources/back.png");

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }

}
