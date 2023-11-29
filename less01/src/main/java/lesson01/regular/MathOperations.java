package lesson01.regular;
/**
* класс для осуществления математических операций
 */
public class MathOperations {
    /**
     * Функция сложения двух чисел
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return сумма
     */
    public static int summa (int a, int b){
        return a + b;
    }

    /**
     * Функция умножения двух чисел
     * @param a первый множитель
     * @param b второй множитель
     * @return произведение
     */
    public static int multi (int a, int b){
        return a * b;
    }

    /**
     * Функция вычитания двух чисел
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return разность
     */
    public static int sub (int a, int b){
        return a - b;
    }

    /**
     * Функция деления двух чисел (результат в int)
     * @param a делимое
     * @param b делитель
     * @return частное
     */
    public static int div (int a, int b){
        return a / b;
    }
}
