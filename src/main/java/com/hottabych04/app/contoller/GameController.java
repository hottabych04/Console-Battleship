package com.hottabych04.app.contoller;

import com.hottabych04.app.model.Direction;
import com.hottabych04.app.model.GameBoard;
import com.hottabych04.app.view.GameView;

import java.util.Scanner;

public class GameController {

    private final GameView view;
    private final GameBoard board1;
    private final GameBoard board2;
    private Scanner scanner;

    public GameController(GameView view, GameBoard board1, GameBoard board2) {
        this.view = view;
        this.board1 = board1;
        this.board2 = board2;
        scanner = new Scanner(System.in);
    }

    public void startGame(){
        view.displayMessage("Добро пожаловать в морской бой!\n");
        view.displayMessage("Для начала игрок 1 должен расположить корабли на своей доске: ");
        placeShips(board1);

        view.displayMessage("Теперь игрок под номером 2 должен расположить корабли на своей доске: ");
        placeShips(board2);

        for (int i = 0; !board1.areAllShipsDestroy() && !board2.areAllShipsDestroy(); i++) {
            if (i%2 == 0) {
                playerMove("игрок 1", board2);
            } else {
                playerMove("игрок 2", board1);
            }
        }
        
        if (board1.areAllShipsDestroy()) {
            view.displayMessage("игрок 2 все корабли потоплены! Вы выиграли!");
            view.displayUnionBoard(board1.getBoard());
        } else {
            view.displayMessage("игрок 1 все корабли потоплены! Вы выиграли!");
            view.displayUnionBoard(board2.getBoard());
        }


    }

    private void playerMove(String name, GameBoard board) {

        boolean hit = true;

        while (!board.areAllShipsDestroy() && hit) {
            view.displayMessage(name + " выполняет ход.");
            view.displayEnemyBoard(board.getBoard());
            int[] coordinates = getUserInput(board);
            hit = board.checkHit(coordinates[0], coordinates[1]);
            if (hit) {
                view.displayMessage("Попадание!");
            } else {
                view.displayMessage("Мимо!");
            }
        }

    }


    private void placeShips(GameBoard board) {
        int[] shipLimits = {4, 3, 2, 1};
        for (int size = 1; size <= 4; size++) {
            int shipsPlaced = 0;
            while (shipsPlaced < shipLimits[size - 1]) {
                view.displayMessage("Разместите корабль размером " + size + ": ");
                view.displayUnionBoard(board.getBoard());

                int[] coordinates = getUserInput(board);
                Direction direction;
                if (size != 1){
                    direction = getOrientation();
                } else {
                    direction = Direction.UP;
                }

                if (board.canPlaceShip(coordinates[0], coordinates[1], size, direction)) {
                    board.placeShip(coordinates[0], coordinates[1], size, direction);
                    shipsPlaced++;
                } else {
                    view.displayMessage("Невозможно разместить корабль в этой позиции. Попробуйте снова:");
                }
            }
        }
    }

    private int[] getUserInput(GameBoard board) {
        int row, col;
        while (true) {
            view.displayMessage("Введите координаты для корабля (строка и столбец через пробел): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    row = parts[0].toUpperCase().chars().findFirst().getAsInt() - 65;
                    col = Integer.parseInt(parts[1]);
                    if (row >= 0 && row < board.getBoardSize() && col >= 0 && col < board.getBoardSize()) {
                        return new int[]{row, col};
                    }
                } catch (NumberFormatException e) {
                    view.displayMessage("Неверный ввод. Введите символ и число: ");
                }
            }
            view.displayMessage("Неверные координаты. Попробуйте снова: ");
        }
    }

    private Direction getOrientation() {
        view.displayMessage("Введите направление в котором хотиеть расположить корабль (Up, Left, Right, Down): ");

        Direction direction = null;
        while (direction == null){
            try {
                direction = Direction.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                view.displayMessage("Неверно введенное направление. Попробуйте еще раз (Up, Left, Right, Down): ");
            }
        }
        return direction;
    }
}
