package com.hottabych04.app;

import com.hottabych04.app.contoller.GameController;
import com.hottabych04.app.model.GameBoard;
import com.hottabych04.app.view.GameView;

public class Main {
    public static void main(String[] args) {

        GameBoard gameBoard1 = new GameBoard();
        GameBoard gameBoard2 = new GameBoard();
        GameView gameView = new GameView();

        GameController controller = new GameController(gameView, gameBoard1, gameBoard2);

        controller.startGame();

    }
}