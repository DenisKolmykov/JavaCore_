package lesson01.regular;
/**
 * Декоратор - фомирует читабельную строку для вывода результата
 */
public class Decorator {
    /**
     *
     * @param mathOperation название математической операции в родительном падеже
     * @param a  результат математической операции
     * @return отформатированная строка для вывода результата
     */

    public static String decorate (String mathOperation, int a){
        return String.format("Результат %s = %d", mathOperation, a);
    }
}
