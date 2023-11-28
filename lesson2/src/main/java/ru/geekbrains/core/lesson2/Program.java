package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 3;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static  char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    /**
     * указатели на координаты последней установленной dot
     */
    private static int pointerX;
    private static int pointerY;


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    static void initialize(){
        fieldSizeY = 3;
        fieldSizeX = 3;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    private static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++){
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int y = 0; y < fieldSizeY; y++){
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++){
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;

        do {
            System.out.print("Введите координаты хода X и Y (от 1 до 3)\nчерез пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        pointerX = x; // фиксируем точку хода
        pointerY = y;
        field[y][x] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn() {
        int x = 0;
        int y = 0;
        boolean flag = false; // выход из цикла если находим win комбинацию из WIN_COUNT при подстановке

        int countWinCombinationMax = 0; //считаем win комбинации
        int xMax = 0; // сохраняем координаты наиболее опасной точки
        int yMax = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {

                if (field[i][j] == DOT_EMPTY) {
                    field[i][j] = DOT_HUMAN; // подставляем первую точку
                    if (checkWin(DOT_HUMAN, WIN_COUNT)){ //проверяем на win по WIN_COUNT
                        xMax = j;
                        yMax = i;
                        flag = true; // если да - выходим из цикла, сохраняем координаты опасной точки
                        break;
                    }

                    int countWinCombination = 0;
//                    if (check1(j, i, DOT_HUMAN, WIN_COUNT - 1)) countWinCombination++;
//                    System.out.println("from aiTurn ch1 countWinCombination = " + countWinCombination);
//                    if (check2(j, i, DOT_HUMAN, WIN_COUNT - 1)) countWinCombination++;
//                    System.out.println("from aiTurn ch2 countWinCombination = " + countWinCombination);
//
//                    if (check3(j, i, DOT_HUMAN, WIN_COUNT - 1)) countWinCombination++;
//                    System.out.println("from aiTurn ch3 countWinCombination = " + countWinCombination);
//
//                    if (check4(j, i, DOT_HUMAN, WIN_COUNT - 1)) countWinCombination++;
//                    System.out.println("from aiTurn ch4 countWinCombination = " + countWinCombination);
                    /**
                     * считаем опасные комбинации - уходим в aiTurn2(), где подставляем еще одну точку
                     */

                    countWinCombination = countWinCombination + aiTurn2();

                    // перезапись координат наиболее опасной точки
                    if (countWinCombinationMax < countWinCombination) {
                        countWinCombinationMax = countWinCombination;
                        xMax = j;
                        yMax = i;
                    }
                    field[i][j] = DOT_EMPTY; // возвращаем пустую точку в место подстановки
                }
            }
            if (flag) break;
        }
        if (flag || countWinCombinationMax > 0){
            x = xMax; // фиксируем координаты опасной точки
            y = yMax;

        } else { // если нет опасной точки - то ставим рандом
            do {
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            } while (!isCellEmpty(x, y));
        }

        pointerX = x; // фиксируем точку хода
        pointerY = y;
        field[y][x] = DOT_AI; // ставим ход компа в опасную точку

    }

    /**
     * "подметод" для подстановки второй точки для хода компа
     * @return - подсчет win комбинаций
     */
    static int aiTurn2() {
        int countWinCombination = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == DOT_EMPTY) { // первое погружение (комбинация WIN_COUNT-1)
                    field[i][j] = DOT_HUMAN;
//                    countWinCombination = countWinCombination + check11(j, i, DOT_HUMAN, WIN_COUNT-1);
//                    System.out.println("from aiTurn2 ch11 y = " + i + " x = " + j + ", " + countWinCombination);
//                    countWinCombination = countWinCombination + check21(j, i, DOT_HUMAN, WIN_COUNT-1);
//                    System.out.println("from aiTurn2 ch21 y = " + i + " x = " + j + ", " + countWinCombination);
//                    countWinCombination = countWinCombination + check31(j, i, DOT_HUMAN, WIN_COUNT-1);
//                    System.out.println("from aiTurn2 ch31 y = " + i + " x = " + j + ", "+ countWinCombination);
//                    countWinCombination = countWinCombination + check41(j, i, DOT_HUMAN, WIN_COUNT-1);
//                    System.out.println("from aiTurn2 ch41 y = " + i + " x = " + j + ", " + countWinCombination);

                    for (int k = 0; k < fieldSizeY; k++){
                        for (int l = 0; l < fieldSizeX; l++){
                            if (field[k][l] == DOT_EMPTY) { // второе погружение (комбинация WIN_COUNT)
                                field[k][l] = DOT_HUMAN;
                                countWinCombination = countWinCombination + check11(l, k, DOT_HUMAN, WIN_COUNT) + check21(l, k, DOT_HUMAN, WIN_COUNT) + check31(l, k, DOT_HUMAN, WIN_COUNT) + check41(l, k, DOT_HUMAN, WIN_COUNT);
                                field[k][l] = DOT_EMPTY;
                            }
                        }
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return countWinCombination;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y){
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игрового поля
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x< fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Метод проверки состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return результат проверки состояния игры
     */
    static boolean checkGameState(char dot, String s){
        if (checkWin(dot, WIN_COUNT)){
            System.out.println(s);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка на ничью
     * @return
     */
    static boolean checkDraw(){
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
               if (isCellEmpty(x, y))
                   return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы игрока
     * @param dot фишка игрока
     * @return признак победы
     */
    static boolean checkWin(char dot, int winCount){
        // Проверка по трем горизонталям
//        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
//        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
//        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
//
//        // Проверка по трем вертикалям
//        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
//        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
//        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
//
//        // Проверка по диагоналям
//        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
//        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;
        if (check1 (pointerX, pointerY, dot, winCount)) return true;
        if (check2 (pointerX, pointerY, dot, winCount)) return true;
        if (check3 (pointerX, pointerY, dot, winCount)) return true;
        if (check4 (pointerX, pointerY, dot, winCount)) return true;

        return false;
    }

    /**
     * проверка победы по горизонтали вправо
     * @param x
     * @param y
     * @param dot
     * @param winCount
     * @return
     */
    static boolean check1(int x, int y, char dot, int winCount){
        int startX = x + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        if (startX < 0){
            startX = 0;
        }
        while (startX <= x) {
            int count = 0; // счетчик подряд идущих dot
            for (int i = 0; i < winCount; i++) {
                if (isCellValid(startX + i, y)) {
                    if (field[y][startX + i] == dot) {
                        count++;
                    }//можно было бы еще проверку,если наткнулся на чужой dot - прерывать цикл (но ввиду "небольших размеров" - нет необходимости)
                } else {break;}
            }
            if (count == winCount) return true;
            startX++;
        }
        return false;
    }
    static int check11(int x, int y, char dot, int winCount){
        int startX = x + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        if (startX < 0){
            startX = 0;
        }
        while (startX <= x) {
            int count = 0; // счетчик подряд идущих dot
            for (int i = 0; i < winCount; i++) {
                if (isCellValid(startX + i, y)) {
                    if (field[y][startX + i] == dot) {
                        count++;
                    }//можно было бы еще проверку,если наткнулся на чужой dot - прерывать цикл (но ввиду "небольших размеров" - нет необходимости)
                } else {break;}
            }
            if (count == winCount) return 1;
            startX++;
        }
        return 0;
    }

    /**
     * проверка победы по вертикали вниз
     * @param x
     * @param y
     * @param dot
     * @param winCount
     * @return
     */
    static boolean check2(int x, int y, char dot, int winCount){
        int startY = y + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        if (startY < 0){
            startY = 0;
        }
        while (startY <= y) {
            int count = 0;
            for (int i = 0; i < winCount; i++) {
                if (isCellValid(x, startY + i)) {
                    if (field[startY + i][x] == dot) {
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return true;
            startY++;
        }
        return false;
    }
    static int check21(int x, int y, char dot, int winCount){
        int startY = y + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        if (startY < 0){
            startY = 0;
        }
        while (startY <= y) {
            int count = 0;
            for (int i = 0; i < winCount; i++) {
                if (isCellValid(x, startY + i)) {
                    if (field[startY + i][x] == dot) {
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return 1;
            startY++;
        }
        return 0;
    }

    /**
     * проверка победы по диагонали вниз
     * @param x
     * @param y
     * @param dot
     * @param winCount
     * @return
     */
    static boolean check3(int x, int y, char dot, int winCount){
        int startY = y + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        int startX = x + 1 - winCount;
        if (startY < 0){
            startY = 0;
            startX = x - y;
        }
        if (startX < 0){
            startX = 0;
            startY = y - x;
        }

        while (startX <= x ){
            int count = 0;
            for (int i = 0; i < winCount; i++){
                if (isCellValid(startX + i, startY + i)){
                    if (field[startY + i][startX + i] == dot){
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return true;
            startX++;
            startY++;
        }

        return false;
    }
    static int check31(int x, int y, char dot, int winCount){
        int startY = y + 1 - winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        int startX = x + 1 - winCount;
        if (startY < 0){
            startY = 0;
            startX = x - y;
        }
        if (startX < 0){
            startX = 0;
            startY = y - x;
        }

        while (startX <= x ){
            int count = 0;
            for (int i = 0; i < winCount; i++){
                if (isCellValid(startX + i, startY + i)){
                    if (field[startY + i][startX + i] == dot){
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return 1;
            startX++;
            startY++;
        }

        return 0;
    }

    /**
     * проверка победы по диагонали вверх
     * @param x
     * @param y
     * @param dot
     * @param winCount
     * @return
     */
    static boolean check4(int x, int y, char dot, int winCount){
        int startY = y - 1 + winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        int startX = x + 1 - winCount;
        if (startY >= fieldSizeY){
            startY = fieldSizeY - 1;
            startX = x - (startY - y);
        }
        if (startX < 0){
            startX = 0;
            startY = y + x;
        }
        while (startX <= x) {
            int count = 0;
            for (int i = 0; i < winCount; i++){
                if (isCellValid(startX + i, startY - i)){
                    if (field[startY - i][startX + i] == dot){
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return true;
            startX++;
            startY--;
        }
        return false;
    }
    static int check41(int x, int y, char dot, int winCount){
        int startY = y - 1 + winCount; // точка от которой начинаем пробегать проверку выигрышной комбинации
        int startX = x + 1 - winCount;
        if (startY >= fieldSizeY){
            startY = fieldSizeY - 1;
            startX = x - (startY - y);
        }
        if (startX < 0){
            startX = 0;
            startY = y + x;
        }
        while (startX <= x) {
            int count = 0;
            for (int i = 0; i < winCount; i++){
                if (isCellValid(startX + i, startY - i)){
                    if (field[startY - i][startX + i] == dot){
                        count++;
                    }
                } else {break;}
            }
            if (count == winCount) return 1;
            startX++;
            startY--;
        }
        return 0;
    }

}
