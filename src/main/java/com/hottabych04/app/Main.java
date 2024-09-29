package com.hottabych04.app;

import com.hottabych04.app.model.Direction;
import com.hottabych04.app.model.GameBoard;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GameBoard gameBoard = new GameBoard();

        if (gameBoard.canPlaceShip(3, 4, 2, Direction.DOWN)){
            gameBoard.placeShip(3, 4, 2, Direction.DOWN);
        }

        if (gameBoard.canPlaceShip(8, 8, 4, Direction.LEFT)){
            gameBoard.placeShip(8, 8, 4, Direction.LEFT);
        }

        System.out.println(gameBoard);
    }
}