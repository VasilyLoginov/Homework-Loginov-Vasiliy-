package lesson_3;

import java.util.Scanner;

public class spiral {
    //1** ) При помощи метода drawSpiral(int height, int weight) и двумерного массива нарисовать
    // спиральную матрицу:
    //
    //01 12 11 10
    //02 13 16 09
    //03 14 15 08
    //04 05 06 07
    //
    //Принцип заполнения: вниз -> вправо -> вверх -> влево
    //Учтите, что столбцов и строк может быть минимум 1. А если числа разных разрядов, то еще нужно
    //подумать о форматировании (подсказка: printf() поможет)
    //Размер спрашивается у пользователя, выводить по порядку. Может быть по размеру 7х1, может 2х11

    public static void main(String[] args) {
        System.out.println("Задайте количество строк матрицы: ");
        int height = inputInt();
        System.out.println("Задайте количество столбцов матрицы: ");
        int weight = inputInt();
        drawSpiral(height, weight);
    }

    public static int inputInt() {
        Scanner input = new Scanner(System.in);
        int intNumber = input.nextInt();
        return intNumber;
    }

    public static void drawSpiral(int height, int weight) {
        int[][] matrix = new int[height][weight];
        int cellValue = 1; //начальное значение элемента массива

        int startRowDown = 0;
        int endRowDown = height;
        int currentRow = 0;
        int startRowUp = height -1;
        int endRowUp = 0;

        int startColumnRight = 0;
        int endColumnRight = weight;
        int currentColumn = 0;
        int startColumnLeft = weight - 1;
        int endColumnLeft = 0;

        while (cellValue <= (height * weight)) {

            for (int i = startRowDown; i < endRowDown; i++) { //шагаем вниз
                matrix[i][currentColumn] = cellValue;
                currentRow = i;
                cellValue++;
            }

            startColumnRight++;

            for (int j = startColumnRight; j < endColumnRight; j++) { //шагаем вправо
                matrix[currentRow][j] = cellValue;
                currentColumn = j;
                cellValue++;
            }

            if(cellValue > (height * weight)){
                break;
            }

            startRowUp = currentRow - 1;
            endColumnRight--;

            for (int i = startRowUp; i >= endRowUp; i--) { //шагаем вверх
                matrix[i][currentColumn] = cellValue;
                currentRow = i;
                cellValue++;
            }

            endRowUp++;

            startColumnLeft = currentColumn - 1;
            endColumnLeft++;

            for (int j = startColumnLeft; j >= endColumnLeft; j--) { //шагаем влево
                matrix[currentRow][j] = cellValue;
                currentColumn = j;
                cellValue++;
            }

            startRowDown = currentRow + 1;
            endRowDown--;
        }
// Вывод матрицы на эркан
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%4d",anInt);
            }
            System.out.println();
            System.out.println();
        }
    }
}
