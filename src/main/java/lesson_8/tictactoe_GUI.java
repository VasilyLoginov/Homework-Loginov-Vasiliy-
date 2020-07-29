package lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

import java.util.Random;

public class tictactoe_GUI extends JFrame {

    public static final int SIZE = 3;
    //public static final char DOT_EMPTY = 8226; //9679 //8226
    public static final String DOT_HUMAN = "X";
    public static final String DOT_AI = "0";
    public static final String EMPTY = "";
    public static final String FIRST_EMPTY_CHAR = "  ";

    public static final String FRAME_NAME = "Крестики-нолики";
    public static String resultString = "";
    public static Random random = new Random();
    public static int index = 0;
    public static int countPlayerMove = 0;
    public static final int maxCountPlayerMove = ((SIZE * SIZE) / 2 )+ 1;

    public static boolean isCorrectMove = false;
    public static boolean isEndGame = false;

    public static int rowMoveNumber = 1;
    public static int colMoveNumber = 1;
    public static int[] targetRow = new int[SIZE];
    public static int[] targetCol = new int[SIZE];
    public static int targetDia = 1;
    public static int targetInvDia = 1;

    public static char[][] map = new char[SIZE][SIZE];

    JButton[] jbs = new JButton[SIZE * SIZE];
    JFrame exitWindow = new JFrame();

    public tictactoe_GUI() {
        createGUI();

    }

    public void createGUI(){
        System.out.println(maxCountPlayerMove);
        initFrame();
        initButtoms();
        //firstComputerMove();
        initListener();
    }

    protected void initFrame() {
        setTitle(FRAME_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 300);
    }

    protected void initButtoms() {
        setLayout(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton(EMPTY);
            jbs[i].setName(String.valueOf(i));
            add(jbs[i]);
        }
        setVisible(true);
    }

    protected void firstComputerMove() {
        int randomCell = random.nextInt(SIZE * SIZE);
        System.out.println(randomCell);
        jbs[randomCell].setText(DOT_AI);
        System.out.println("random");
    }

    protected void initListener() {
        ActionListener actionListener = new TestActionListener();
        int i = 0;
        while (i < SIZE * SIZE) {
            jbs[i].addActionListener(actionListener);
            i++;
        }
    }

    public class TestActionListener implements ActionListener {

        protected String[][] map = new String[SIZE][SIZE];
        protected String[][] maskMap = new String[SIZE][SIZE];
        protected boolean isInitMap = false;
        protected boolean isInitMaskMap = false;

        public void actionPerformed(ActionEvent e) {
            boolean isMovePlayer = false;
            int i = 0;

            if(!isInitMap) {
                initMap();
            }
            printMap(map);
            if(!isInitMaskMap) initMaskMap();
            printMap(maskMap);
            JButton btn = (JButton) e.getSource();
            isMovePlayer = playerMove(isMovePlayer, btn);
            printMap(map);
            drawCheck();
            victoryCheck();
            computerMove(isMovePlayer);
            drawCheck();
            victoryCheck();
        }

        protected void initMap(){
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    map[i][j] = EMPTY;
                }
            }
            isInitMap = true;
        }

        protected void initMaskMap(){
            int count = 0;
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    maskMap[i][j] = String.valueOf(count);
                    count++;
                }
            }
        }

        public void printMap(String[][] map) {
            int rowNumber = 0;
            int colNumber = 0;

            System.out.print(FIRST_EMPTY_CHAR + EMPTY);

            for (String[] strings : map) {
                colNumber++;
                System.out.printf(colNumber + FIRST_EMPTY_CHAR);
            }

            System.out.println();
            for (String[] ints : map) {
                rowNumber++;
                System.out.printf("%2d",rowNumber);
                System.out.print(EMPTY);
                for (String anInt : ints) {
                    System.out.print(anInt + FIRST_EMPTY_CHAR);
                }
                System.out.println();
            }
        }

        protected boolean playerMove(boolean isMovePlayer, JButton btn) {
            if (changeSymbol(btn)) {
                isMovePlayer = true;
                countPlayerMove++;
            }
            return isMovePlayer;
        }

        protected boolean changeSymbol(JButton btn) {
            if (btn.getText() .equalsIgnoreCase(String.valueOf(EMPTY))) {
                btn.setText(DOT_HUMAN);
                String indexOfMove = btn.getName();
                sendSymbolToMap(indexOfMove, DOT_HUMAN);

                return true;
            }
            return false;
        }

        protected void sendSymbolToMap(String ind, String symbol){
            for(int i = 0; i < SIZE; i++){
                for (int j = 0; j < SIZE; j++){

                    if(Integer.parseInt(maskMap[i][j]) == Integer.parseInt(ind)){
                        map[i][j] = symbol;

                    }
                }
            }
        }

        protected void computerMove(boolean isMovePlayer) {
            String indexOfMove;
            if(isMovePlayer && countPlayerMove < maxCountPlayerMove){
                computerChooseMove();
                isMovePlayer = false;
                ActionEvent e1 = new ActionEvent(jbs[index], Event.MOUSE_DOWN, "");
                JButton btn2 = (JButton) e1.getSource();
                btn2.setText(DOT_AI);
                indexOfMove = btn2.getName();
                sendSymbolToMap(indexOfMove, DOT_AI);
                return;
            }
        }

        protected void randomIndex() {
            boolean isNotSelected = true;
            while (isNotSelected) {
                isNotSelected = true;
                index = random.nextInt(SIZE * SIZE);
                for(int i = 0; i < SIZE; i++){
                    for (int j = 0; j < SIZE; j++){
                        if(Integer.parseInt(maskMap[i][j]) == index){
                            if (map[i][j] == "") {
                                isNotSelected = false;
                                break;
                            }
                        }
                    }
                }
            }
        }

        protected void computerChooseMove() {

            String controlChar;

            controlChar = scanMapForChar(DOT_AI, SIZE - 1);
            if(controlChar == DOT_AI){
                findLastMove();
                mapToIndex();
                return;
            }

            controlChar = scanMapForChar(DOT_HUMAN, SIZE - 1);

            if(controlChar == DOT_HUMAN){
                findLastMove();
                mapToIndex();
                return;
            }
            randomIndex();
        }
        protected void mapToIndex(){
            index = Integer.parseInt(maskMap[rowMoveNumber - 1][colMoveNumber - 1]);
        }

        protected void victoryCheck() {

            String victoryChar = scanMapForChar(DOT_HUMAN, SIZE);

            if(victoryChar == DOT_HUMAN){
                if(!isEndGame) {
                    resultString = "Вы выиграли!!!";
                }
                endGame();
                return;
            }

            victoryChar = scanMapForChar(DOT_AI, SIZE);
            if(victoryChar == DOT_AI){
                resultString = "Вы проиграли!!!";
                endGame();
                return;
            }
            drawCheck();
        }

        protected String scanMapForChar(String symbol, int numberOfChars) {
            int  numberOfCharRow, numberOfCharCol, numberOfCharDia, numberOfCharInvDia;
            String victorySymbol = EMPTY;
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

        protected void resetTarget() {
            for(int i = 0; i < SIZE; i++){
                targetRow[i] = 0;
                targetCol[i] = 0;
            }
            targetDia = 0;
            targetInvDia = 0;
        }

        protected void findLastMove() {

            for(int i = 0; i <= SIZE-1; i++) {
                if(targetRow[i] == SIZE-1){
                    rowMoveNumber = i + 1;
                    for (int j = 0; j <= SIZE - 1; j++){
                        if(map[i][j] == EMPTY){
                            colMoveNumber = j + 1;
                            return;
                        }
                    }
                }
                if(targetCol[i] == SIZE-1){
                    colMoveNumber = i + 1;
                    for (int j = 0; j < SIZE; j++){
                        if(map[j][i] == EMPTY){
                            rowMoveNumber = j + 1;
                            return;
                        }
                    }
                }
            }
            if(targetDia == SIZE - 1){
                for (int i = 0; i < SIZE; i++){
                    if(map[i][i] == EMPTY){
                        rowMoveNumber = i + 1;
                        colMoveNumber = i + 1;
                        return;
                    }
                }
            }

            if(targetInvDia == SIZE - 1){
                for (int i = 0; i < SIZE; i++){
                    if(map[i][SIZE - 1 - i] == EMPTY){
                        rowMoveNumber = i + 1;
                        colMoveNumber = SIZE - i;
                        return;
                    }
                }
            }
            randomIndex();
        }

        protected void drawCheck() {
            if(!isEndGame) {
                boolean isDraw = true;
                for (String[] strings : map) {
                    for (String aChar : strings) {
                        if (aChar == EMPTY) {
                            isDraw = false;
                        }
                    }
                }
                if (isDraw) {
                    resultString = "Ходов больше нет! Ничья !";
                    endGame();
                    return;
                }
            }
        }

        protected void endGame() {
            if(!isEndGame) {
                isEndGame = true;
                exitWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                exitWindow.addWindowListener (new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // Потверждение закрытия окна JFrame

                        int res = JOptionPane.showConfirmDialog(exitWindow,
                                resultString, "Game over!",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (res == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                });

                JOptionPane.showMessageDialog(null,
                        resultString,
                        "Game over!",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);




            }
            return;
        }
    }

    public static void main(String[] args) {
        new tictactoe_GUI();
    }
}
