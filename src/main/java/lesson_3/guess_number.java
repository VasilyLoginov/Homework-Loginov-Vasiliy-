package lesson_3;
// 1. Написать программу, которая загадывает случайное число от 0 до 9,
// и пользователю дается 3 попытки угадать это число. При каждой попытке
// компьютер должен сообщить больше ли указанное пользователем число чем
// загаданное, или меньше. После победы или проигрыша выводится
// запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

import java.util.Scanner;

public class guess_number {

    private static int score = 0;
    private static int guessNumber = 0;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int userNumber = 0;
        guessNumber = randomNumber(9);
        userNumber = inputIntNumber();
        checkNumber(userNumber, guessNumber);

        while (true)
            if(score == 4){
                System.out.println("Можете продолжить или выйти.");
                doExit();
            } else if (score == 3){
                System.out.println("Вы использовали все попытки и не угадали число. Правильный ответ: " + guessNumber + ". Можете продолжить или выйти.");
                doExit();
            } else if(score < 3){
                userNumber = inputIntNumber();
                checkNumber(userNumber, guessNumber);
        }
    }

    public static int randomNumber(int range) {
        int number = (int) (0 + (Math.random() * range));
        return number;
    }

    public static int inputIntNumber() {
        int number = 0;
        boolean isCorrectNumber = false;
        while (!isCorrectNumber) {
            System.out.println((score + 1) + "-я попытка угадайть число от 0 до 9.");
            isCorrectNumber = true;
            String next = sc.next();
            if ("0".equals(next)) {
                number = 0;
            } else if ("1".equals(next)) {
                number = 1;
            } else if ("2".equals(next)) {
                number = 2;
            } else if ("3".equals(next)) {
                number = 3;
            } else if ("4".equals(next)) {
                number = 4;
            } else if ("5".equals(next)) {
                number = 5;
            } else if ("6".equals(next)) {
                number = 6;
            } else if ("7".equals(next)) {
                number = 7;
            } else if ("8".equals(next)) {
                number = 8;
            } else if ("9".equals(next)) {
                number = 9;
            } else {
                isCorrectNumber = false;
                System.out.println("\u001B[31m" + "Некорректный ввод числа!" + "\u001B[37m");
            }
        }
        return number;
    }

    public static void checkNumber(int userNum, int guessNum) {
        if(userNum == guessNum){
            System.out.println("Вы угадали!!!");
            score = 4;
        } else if(userNum < guessNum) {
            System.out.println("Ваше число меньше загаданного!");
            score ++;
        } else if (userNum > guessNum) {
            System.out.println("Ваше число больше загаданного!");
            score ++;
        }
    }

    public static void doExit() {
        System.out.println("Выйти? (y) - выход/(n) - продолжить");
        if(sc.next().equals("y")){
            System.exit(0);
        }
        guessNumber = randomNumber(9);
        score = 0;
    }

}
