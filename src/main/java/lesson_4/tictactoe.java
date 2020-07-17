package lesson_4;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import  java.util.Scanner;

public class tictactoe {
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 3;

    public static final char DOT_EMPTY = 8226; //9679 //8226
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = '0';

    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";

    public static boolean isCorrectMove = false;
    public static boolean isEndGame = false;

    public static int rowMoveNumber = 1;
    public static int colMoveNumber = 1;
    public static int[] targetRow = new int[SIZE];
    public static int[] targetCol = new int[SIZE];
    public static int targetDia = 1;
    public static int targetInvDia = 1;

    public static char[][] map = new char[SIZE][SIZE];

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {

        initMap();
        printMap();

        while (!isEndGame) {
            playerMove();
            victoryCheck();
            printMap();
            if(isEndGame){
                return;
            }
            computerMove();
            victoryCheck();
            printMap();
            if(isEndGame) {
                return;
            }
        }
    }

    private static void initMap() {
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        int rowNumber = 0;
        int colNumber = 0;

        System.out.print(FIRST_EMPTY_CHAR + EMPTY);

        for (char[] chars : map) {
            colNumber++;
            System.out.printf(colNumber + FIRST_EMPTY_CHAR);
        }

        System.out.println();
        for (char[] ints : map) {
            rowNumber++;
            System.out.printf("%2d",rowNumber);
            System.out.print(EMPTY);
            for (char anInt : ints) {
                System.out.print(anInt + FIRST_EMPTY_CHAR);
            }
            System.out.println();
        }
    }

    public static void playerMove() {
        isCorrectMove = false;

        System.out.println("Выполните ход.");

        while (!isCorrectMove) {
            rowMoveNumber = inputInt("Задайте номер строки: ");
            colMoveNumber = inputInt("Задайте номер столбца: ");
            checkMove();
            if(!isCorrectMove){
                System.out.println("Вы сделали не верный ход. Повторите ход ...");
                printMap();
            } else System.out.println("Ход принят.");
        }

        map[rowMoveNumber-1][colMoveNumber-1] = DOT_HUMAN;
    }

    public static int inputInt(String string) {
        int number = 0;
        System.out.println(string);
        while(true) {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Повторите ввод.");
                scanner.nextLine();
                continue;
            }
            break;
        }
        return number;
    }

    public static void checkMove() {
        isCorrectMove = ((rowMoveNumber > SIZE)||(colMoveNumber > SIZE) || (map[rowMoveNumber - 1][colMoveNumber - 1] != DOT_EMPTY))?false:true;
    }

    public static void computerMove() {
        isCorrectMove = false;

        System.out.println("Компьютер ходит.");

        while (!isCorrectMove) {

            computerChooseMove();

            checkMove();

        }

        map[rowMoveNumber-1][colMoveNumber-1] = DOT_AI;
    }

    public static void computerChooseMove() {

        char controlChar;

        controlChar = scanMapForChar(DOT_AI, SIZE - 1);
        if(controlChar == DOT_AI){
            findLastMove();
            return;
        }

        controlChar = scanMapForChar(DOT_HUMAN, SIZE - 1);

        if(controlChar == DOT_HUMAN){
            findLastMove();
            return;
        }
        randomRowCol();
    }

    public static void randomRowCol() {
        rowMoveNumber = random.nextInt(SIZE) + 1;
        colMoveNumber = random.nextInt(SIZE) + 1;
    }

    public static void victoryCheck() {

        char victoryChar = scanMapForChar(DOT_HUMAN, SIZE);

        if(victoryChar == DOT_HUMAN){
            System.out.println("Вы выиграли!!!");
            endGame();
            return;
        }

        victoryChar = scanMapForChar(DOT_AI, SIZE);
        if(victoryChar == DOT_AI){
            System.out.println("Вы проиграли!!!");
            endGame();
            return;
        }
        drawCheck();
    }

    public static char scanMapForChar(char symbol, int numberOfChars) {
        int  numberOfCharRow, numberOfCharCol, numberOfCharDia, numberOfCharInvDia;
        char victorySymbol = DOT_EMPTY;
        numberOfCharDia = 0;
        numberOfCharInvDia = 0;
        resetTarget();
        for (int i = 0; i < SIZE;i++) {
           numberOfCharRow = 0;
           numberOfCharCol = 0;

            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symbol) {
                    numberOfCharRow++;
                }
                if (map[j][i] == symbol) {
                    numberOfCharCol++;
                }
            }

            if (map[i][i] == symbol) {
                numberOfCharDia++;
            }

            if (map[i][SIZE - i - 1] == symbol) {
                numberOfCharInvDia++;
            }

            targetRow[i] = numberOfCharRow;
            targetCol[i] = numberOfCharCol;
            targetDia = numberOfCharDia;
            targetInvDia = numberOfCharInvDia;

            if (numberOfCharRow == numberOfChars || numberOfCharCol == numberOfChars || numberOfCharDia == numberOfChars || numberOfCharInvDia == numberOfChars) {
                victorySymbol = symbol;
            }
        }
        return victorySymbol;
    }

    public static void resetTarget() {
        for(int i = 0; i < SIZE; i++){
            targetRow[i] = 0;
            targetCol[i] = 0;
        }
        targetDia = 0;
        targetInvDia = 0;
    }

    public static void findLastMove() {

        for(int i = 0; i <= SIZE-1; i++) {
            if(targetRow[i] == SIZE-1){
                rowMoveNumber = i + 1;
                for (int j = 0; j <= SIZE - 1; j++){
                    if(map[i][j] == DOT_EMPTY){
                        colMoveNumber = j + 1;
                        return;
                    }
                }
            }
            if(targetCol[i] == SIZE-1){
                colMoveNumber = i + 1;
                for (int j = 0; j < SIZE; j++){
                    if(map[j][i] == DOT_EMPTY){
                        rowMoveNumber = j + 1;
                        return;
                    }
                }
            }
        }
        if(targetDia == SIZE - 1){
            for (int i = 0; i < SIZE; i++){
                if(map[i][i] == DOT_EMPTY){
                    rowMoveNumber = i + 1;
                    colMoveNumber = i + 1;
                    return;
                }
            }
        }

        if(targetInvDia == SIZE - 1){
            for (int i = 0; i < SIZE; i++){
                if(map[i][SIZE - 1 - i] == DOT_EMPTY){
                    rowMoveNumber = i + 1;
                    colMoveNumber = SIZE - i;
                    return;
                }
            }
        }
        randomRowCol();
    }

    public static void drawCheck() {
        boolean isDraw = true;
        for (char[] chars : map) {
            for (char aChar : chars) {
                if(aChar == DOT_EMPTY){
                    isDraw = false;
                }
            }
        }
        if(isDraw){
            System.out.println("Ходов больше нет! Ничья !");
            endGame();
            return;
        }
    }

    public static void endGame() {
        System.out.println("Игра окончена.");
        isEndGame = true;
        return;
    }
}

