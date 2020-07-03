package lesson_3;

import java.math.BigInteger;
import java.util.Scanner;

public class fibonacci_number {
    //2) На КАРТИНКЕ представлена визуализация чисел Фибоначчи. Необходимо подсчитать
    // сумму периметров квадратов для n+1 квадратов
    //Для примера выше n=5,
    //1*4 + 1*4 + 2*4 + 3*4 + 5*4 + 8*4 = 80
    //Для n = 7 -> 216
    //3 ****) Квадратов может быть вплоть до 10000. Понадобится import java.math.BigInteger;

    public static void main(String[] args) {

        int numberOfSquares = 0;

        System.out.println("Для вычисления суммы периметров квадратов для n+1 квадратов. Задайте число n: ");
        numberOfSquares = inputInt();
        BigInteger result = sumFibonacci(numberOfSquares);
        System.out.println("Значение суммы периметров квадратов для n+1 квадратов равно: " + result);

    }

    public static int inputInt() {
        Scanner inputNumber = new Scanner(System.in);
        int intNumber = inputNumber.nextInt();
        return intNumber;
    }

    public static BigInteger sumFibonacci(int n) {
        BigInteger sumFibonacciNumbers = BigInteger.valueOf(0);
        BigInteger fibonacciNumber = BigInteger.valueOf(1);
        BigInteger lastNumber = BigInteger.valueOf(0);

        for(int i = 1; i <= (n); i++){
            BigInteger tempNumber = lastNumber.add(fibonacciNumber);
            lastNumber = fibonacciNumber;
            fibonacciNumber = tempNumber;
            System.out.println(fibonacciNumber);
            sumFibonacciNumbers = sumFibonacciNumbers.add(fibonacciNumber);
        }

        return (sumFibonacciNumbers.add(BigInteger.valueOf(1))).multiply(BigInteger.valueOf(4));

    }

}
