package lesson_3;

import java.util.Scanner;

public class guess_word {
        /*
        2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot",
        "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
        "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано,
        компьютер показывает буквы которые стоят на своих местах.
        apple – загаданное
        apricot - ответ игрока
        ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
        Для сравнения двух слов посимвольно, можно пользоваться:
        String str = "apple";
        str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
        Играем до тех пор, пока игрок не отгадает слово
        Используем только маленькие буквы
        */

    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                        "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String[] maskWord = {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"};
        boolean isRigth = false;

        String guessWord = guessingWord(words);

        System.out.println("Попробуйте угадать, загаданное компьютером слово (название фрукта или овоща): ");

        while (!isRigth) {
            String userWord = inputString();
            int lengthWord = chooseLength(guessWord, userWord);
            String maskedWord = checkWord(maskWord, userWord, guessWord, lengthWord);
            System.out.println(maskedWord);
            isRigth = guessResult(guessWord, maskedWord);
        }
    }

    public static String inputString() {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        return string;
    }

    public static String guessingWord(String[] word) {
        int length = word.length;
        int randomIndex = (int) (0 + Math.random() * (length - 1));
        String randomWord = word [randomIndex];
        return randomWord;
    }

    public static int chooseLength(String wordOne, String wordTwo) {
        int lengthOne = wordOne.length();
        int lengthTwo = wordTwo.length();
        return (lengthOne < lengthTwo)?lengthOne:lengthTwo;
    }

    public static String checkWord(String[] mask, String wordOne, String wordTwo, int minLength) {
        String maskedAnswer = "";
        for (int i = 0; i < minLength; i++){
            char charOne = wordOne.charAt(i);
            char charTwo = wordTwo.charAt(i);
            if(charOne == charTwo){
                mask[i] = String.valueOf(charOne);
            }
        }
        for (String s : mask) {
            maskedAnswer += s;
        }
        return maskedAnswer;
    }

    public static boolean guessResult(String guessWord, String maskedWord) {
        boolean isRigth = false;
        int length = guessWord.length();
        String answerWord = maskedWord.substring(0,length);
        if(guessWord.equals(answerWord)){
            System.out.println("Слово отгадано!");
            isRigth = true;
        } else {
            System.out.println("Вы не угадали загаданное слово. Пробуйте ещё!");
        }
        return isRigth;
    }



}
