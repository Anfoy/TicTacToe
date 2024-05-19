package me.antcode;

import java.util.Scanner;

public class Game {

    private final Tile[][] grid;

    private final int NUM_ROWS_COL;

   private int turn;


    public Game() {
        //initialize variables
        turn = 1;
        NUM_ROWS_COL = 3;
        int spot = 1;

        //Initializes the grid
        grid = new Tile[NUM_ROWS_COL][NUM_ROWS_COL];
        for (int row = 0; row < NUM_ROWS_COL; row ++){
            for (int col = 0; col < NUM_ROWS_COL; col++) {
                grid[row][col] = new Tile("-", row, col, spot);
                spot++;
            }
        }


        printBoard();
        Scanner scanner = new Scanner(System.in);
        //main game loop
    while (hasOpenSpace() && checkWinner() == null) {
        System.out.println("Enter a number (1-9) ('0' to quit):");
        int move = scanner.nextInt();
        if (move == 0) return;

        //loop if player's entry wasn't valid
        while (!executeMove(move)){
            if (move == 0) return;
            System.out.println("Enter a number (1-9) ('0' to quit):");
            move = scanner.nextInt();
      }
            }
    //Executes if a winner was found, or there was not any space left.
    if (checkWinner() != null){
        String winner = checkWinner();
        if (winner != null) {
            switch (winner){
                case "X":
                System.out.println("Player One Wins!");
                break;
                case "O":
                System.out.println("Player Two Wins!");
                break;
            }
        }
    }else{
      System.out.println("Game is a tie!");
    }

    }

    /**
     * Prints the current board
     */
    private void printBoard(){
        for (int row = 0; row < NUM_ROWS_COL; row ++){
            for (int col = 0; col < NUM_ROWS_COL; col++) {
                if (col == NUM_ROWS_COL - 1) {
                    System.out.println(grid[row][col].getValue());
                } else {
                    System.out.print(grid[row][col].getValue() + " | ");
                }
            }
        }
    }

    /**
     * Place the appropriate player's piece on the board depending on the player in turn. Prints the board whether action
     * was successful or not.
     * @param position Position of where to attempt to place the piece
     * @return true if there was a successful placing; false if otherwise.
     */
    private boolean executeMove(int position){
        String playerMove = turn == 1 ? "X" : "O";
        for (Tile[] row : grid){
            for (Tile tile : row){
                if (tile.getSpot() == position){
                    if (tile.getValue().equals("-")){
                        tile.setValue(playerMove);
                        turn = turn == 1 ? 2 : 1;
                        printBoard();
                        return true;
                    }else{
            System.out.println("that spot is taken! pick again.");
            printBoard();
            return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winning combination on the board.
     * @return returns the winner's piece if they have a winning combination; null if no winning combinations are found.
     */
    private String checkWinner() {
        for (int i = 0; i < NUM_ROWS_COL; i++) {
            if (isWinningCombination(grid[i][0], grid[i][1], grid[i][2])) return grid[i][0].getValue();
            if (isWinningCombination(grid[0][i], grid[1][i], grid[2][i])) return grid[0][i].getValue();
        }
        if (isWinningCombination(grid[0][0], grid[1][1], grid[2][2])) return grid[0][0].getValue();
        if (isWinningCombination(grid[0][2], grid[1][1], grid[2][0])) return grid[0][2].getValue();
        return null;
    }

    /**
     * Checks of each of the tiles have the same value
     * @param t1 Tile one to check.
     * @param t2 Tile two to check.
     * @param t3 Tile three to check.
     * @return true if all values match and are not "-"; false otherwise.
     */
    private boolean isWinningCombination(Tile t1, Tile t2, Tile t3) {
        return !t1.getValue().equals("-") && t1.getValue().equals(t2.getValue()) && t1.getValue().equals(t3.getValue());
    }


    /**
     * Checks if there is an open space on the board.
     * @return true if there is an open space; false otherwise.
     */
    private boolean hasOpenSpace(){
        for (Tile[] row : grid){
            for (Tile tile : row){
                if (tile.getValue().equals("-")) return true;
            }
        }
        return false;
    }



}
