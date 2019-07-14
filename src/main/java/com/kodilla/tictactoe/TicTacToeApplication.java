package com.kodilla.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class TicTacToeApplication {

    private Image imageback = new Image("file:resources/back.png");

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }

}
