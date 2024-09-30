package com.hottabych04.app.view;

public class GameView {

    public void displayEnemyBoard(char[][] board) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < board.length; i++) {
            System.out.print((char) (i + 'A'));
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] == '#') {
                    System.out.print(" ~");
                } else {
                    System.out.print(" " + board[j][i]);
                }
            }
            System.out.println();
        }
    }

    public void displayUnionBoard(char[][] board) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < board.length; i++) {
            System.out.print((char) (i + 'A'));
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[j][i]);
            }
            System.out.println();
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

}
