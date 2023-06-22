package amex.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnightSolution {
    private static final int[] KNIGHTS_POSSIBLE_ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] KNIGHTS_POSSIBLE_COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> path = new ArrayList<String>();
        boolean hasValidInput = false;

        System.out.print("Please enter the size of the chessboard: ");
        int sizeOfTheBoard = 0;
        while (!hasValidInput) {
            try {
                sizeOfTheBoard = scanner.nextInt();

                if (sizeOfTheBoard <= 0) {
                    System.out.print("You entered an invalid chessboard size. Please enter a valid size of the chessboard: ");
                    continue;
                }
                hasValidInput = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("Please enter a number for the size of the chessboard: ");
            }
        }


        int[][] chessBoard = new int[sizeOfTheBoard][sizeOfTheBoard];
        for (int i = 0; i < sizeOfTheBoard; i++) {
            Arrays.fill(chessBoard[i], -1);
        }

        hasValidInput = false;
        int startKnightX = 0;
        System.out.print("Please enter the starting X position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
        while (!hasValidInput) {
            try {
                startKnightX = scanner.nextInt();
                if (!isUserInputValid(startKnightX, sizeOfTheBoard)) {
                    System.out.print("You entered an invalid starting X position. Please enter the starting X position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
                    continue;
                }
                hasValidInput = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("Please enter a number for the starting X position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
            }
        }

        hasValidInput = false;
        int startKnightY = 0;
        System.out.print("Please enter the starting Y position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
        while (!hasValidInput) {
            try {
                startKnightY = scanner.nextInt();
                if (!isUserInputValid(startKnightY, sizeOfTheBoard)) {
                    System.out.print("You entered an invalid starting Y position. Please enter the starting Y position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
                    continue;
                }
                hasValidInput = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("Please enter a number for the starting Y position of the knight on the board (1 to " + sizeOfTheBoard + "): ");
            }
        }

        System.out.println("Printing all possible paths for the knight on chessboard of size " + sizeOfTheBoard + " with starting position at (" + startKnightX + "," + startKnightY + ").");
        boolean hasPossiblePath = traverseKnightsPossiblePaths(chessBoard, startKnightX - 1, startKnightY - 1, 1, sizeOfTheBoard, path);

        if (!hasPossiblePath) {
            System.out.println("There does not seem to be any possible path such that knight can visit all squares of the chessboard.");
        }
        scanner.close();
    }

    private static boolean isUserInputValid(int userInputValue, int sizeOfTheBoard) {
        return ((userInputValue >= 1) && (userInputValue <= sizeOfTheBoard));
    }

    public static boolean isValidMove(int[][] board, int row, int col, int boardSize) {
        return (row >= 0 && col >= 0 && row < boardSize && col < boardSize && board[row][col] == -1);
    }

    private static boolean traverseKnightsPossiblePaths(int[][] chessBoard, int row, int col, int moveCount, int sizeOfTheBoard, List<String> path) {
        chessBoard[row][col] = moveCount;
        path.add("(" + (row + 1) + "," + (col + 1) + ")");

        if (moveCount == sizeOfTheBoard * sizeOfTheBoard) {
            System.out.print("Possible Path: ");
            System.out.print(String.join(" => ", path));
            System.out.println("");
            chessBoard[row][col] = -1;
            path.remove(path.size() - 1);
            return true;
        }

        boolean hasPossiblePath = false;

        for (int i = 0; i < 8; i++) {
            int nextRow = row + KNIGHTS_POSSIBLE_ROW_MOVES[i];
            int nextCol = col + KNIGHTS_POSSIBLE_COL_MOVES[i];
            if (isValidMove(chessBoard, nextRow, nextCol, sizeOfTheBoard)) {
                if (traverseKnightsPossiblePaths(chessBoard, nextRow, nextCol, moveCount + 1, sizeOfTheBoard, path))
                    hasPossiblePath = true;
            }
        }

        chessBoard[row][col] = -1;
        path.remove(path.size() - 1);
        return hasPossiblePath;
    }
}