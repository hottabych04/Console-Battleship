package com.hottabych04.app.model;

public class GameBoard {

    private final static short MAX_LINE_LENGTH = 10;

    private char[][] board;

    private int shipsCount;

    public GameBoard(){
        this.board = new char[MAX_LINE_LENGTH][MAX_LINE_LENGTH];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < MAX_LINE_LENGTH; i++) {
            for (int j = 0; j < MAX_LINE_LENGTH; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    public boolean canPlaceShip(int row, int column, int size, Direction direction){

        if ( !((0 <= row && row < MAX_LINE_LENGTH) && (0 <= column && column < MAX_LINE_LENGTH)) ) return false;

        return switch (direction) {
            case UP -> {
                if (row - size < 0) yield false;
                for (int i = 0; i < size; i++){
                    if (this.board[column][row+i] != '~') yield false;
                }
                yield true;
            }
            case LEFT -> {
                if (column - size < 0) yield false;
                for (int i = 0; i < size; i++){
                    if (this.board[column-i][row] != '~') yield false;
                }
                yield true;
            }
            case RIGHT -> {
                if (column + size >= MAX_LINE_LENGTH) yield false;
                for (int i = 0; i < size; i++){
                    if (this.board[column+i][row] != '~') yield false;
                }
                yield true;
            }
            case DOWN -> {
                if (row + size >= MAX_LINE_LENGTH) yield false;
                for (int i = 0; i < size; i++){
                    if (this.board[column][row-i] != '~') yield false;
                }
                yield true;
            }
        };
    }

    private boolean checkPlace(int row, int column, int size){
        // TODO: 30.09
        return true;
    }

    public void placeShip(int row, int column, int size, Direction direction){
        switch (direction) {
            case UP -> {
                for (int i = 0; i < size; i++){
                    this.board[column][row+i] = '#';
                }
            }
            case LEFT -> {
                for (int i = 0; i < size; i++){
                    this.board[column-i][row] = '#';
                }
            }
            case RIGHT -> {
                for (int i = 0; i < size; i++){
                    this.board[column+i][row] = '#';
                }
            }
            case DOWN -> {
                for (int i = 0; i < size; i++){
                    this.board[column][row-i] = '#';
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBoard = new StringBuilder();

        stringBoard.append(' ');

        for (int i = 0; i < 10; i++) {
            stringBoard.append(" ").append(i).append(" ");
        }

        stringBoard.append('\n');

        for (int row = 0; row < MAX_LINE_LENGTH; row++) {

            stringBoard.append((char) (65+row));
            for (int column = 0; column < MAX_LINE_LENGTH; column++) {
                 stringBoard.append(" ").append(this.board[column][row]).append(" ");
            }
            stringBoard.append("\n");
        }

        return stringBoard.toString();
    }
}
