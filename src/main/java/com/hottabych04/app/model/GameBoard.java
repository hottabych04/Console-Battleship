package com.hottabych04.app.model;

public class GameBoard {

    private final static short MAX_LINE_LENGTH = 10;

    private final char[][] board;

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
                if (row - size + 1 < 0) yield false;
                for (int i = -1; i <= size; i++){
                    for (int j = column - 1; j <= (column + 1); j++){
                        try {
                            if (this.board[j][row-i] != '~') yield false;
                        } catch (IndexOutOfBoundsException ignored) {}
                    }
                }
                yield true;
            }
            case DOWN -> {
                if (row + size > MAX_LINE_LENGTH) yield false;
                for (int i = -1; i <= size; i++){
                    for (int j = column - 1; j <= (column + 1); j++){
                        try {
                            if (this.board[j][row+i] != '~') yield false;
                        } catch (IndexOutOfBoundsException ignored) {}
                    }
                }
                yield true;
            }
            case LEFT -> {
                if (column - size + 1 < 0) yield false;
                for (int i = -1; i <= size; i++){
                    for (int j = row - 1; j <= (row + 1); j++){
                        try {
                            if (this.board[column-i][j] != '~') yield false;
                        } catch (IndexOutOfBoundsException ignored) {}
                    }
                }
                yield true;
            }
            case RIGHT -> {
                if (column + size > MAX_LINE_LENGTH) yield false;
                for (int i = -1; i <= size; i++){
                    for (int j = row - 1; j <= (row + 1); j++){
                        try {
                            if (this.board[column+i][j] != '~') yield false;
                        } catch (IndexOutOfBoundsException ignored) {}
                    }
                }
                yield true;
            }
        };
    }

    public void placeShip(int row, int column, int size, Direction direction){
        switch (direction) {
            case UP -> {
                for (int i = 0; i < size; i++){
                    this.board[column][row-i] = '#';
                }
            }
            case DOWN -> {
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
        }
    }

    public boolean checkHit(int row, int col) {
        if (board[col][row] == '#') {
            board[col][row] = 'X';
            return true;
        } else if (board[col][row] == '~') {
            board[col][row] = 'O';
            return false;
        }
        return false;
    }

    public boolean areAllShipsDestroy() {
        for (int i = 0; i < MAX_LINE_LENGTH; i++) {
            for (int j = 0; j < MAX_LINE_LENGTH; j++) {
                if (board[i][j] == '#') return false;
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return MAX_LINE_LENGTH;
    }
}
