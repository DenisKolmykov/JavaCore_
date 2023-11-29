package lesson01.sample;

import lesson01.regular.Decorator;
import lesson01.regular.MathOperations;

public class Program {
    /**
     * Точка входа в программу
     *
     * @param args стандартные аргументы
     */
    public static void main(String[] args) {
        /**
         * задаются два числа a и b для математиматических операций
         */
        int a = 5;
        int b = 4;

        System.out.printf("Математические операции с числами %d и %d:\n", a, b);

        int res = MathOperations.summa(a, b);
        System.out.println(Decorator.decorate("сложения", res));

        if (b != 0){
            res = MathOperations.div(a, b);
            System.out.println(Decorator.decorate("деления", res));
        } else {
            System.out.println("Вы пытаетесь делить на ноль!");
        }

        res = MathOperations.sub(a, b);
        System.out.println(Decorator.decorate("вычитания", res));

        res = MathOperations.multi(a, b);
        System.out.println(Decorator.decorate("умножения", res));

    }
}