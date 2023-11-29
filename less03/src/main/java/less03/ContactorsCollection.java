package less03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ContactorsCollection {
    /**
     * создаем объекты класса Worker и Freelancer и добавляем их в Коллекцию
     *
     */
    public void createContractorsCollections(){
        // создаем объекты класса Worker и добавляем их в Коллекцию
        for (int i = 0; i < workersAmount; i++){
            contractorsList.add(Worker.create(getRandomValueFromFieldsList(names),
                    getRandomValueFromFieldsList(specialitys),
                    getRandomValueFromFieldsList(dates),
                    random.nextDouble() * MAX_SALARY));
        }

        // создаем объекты класса Freelancer и добавляем их в Коллекцию
        for (int i = 0; i < freelancerAmount; i++){
            contractorsList.add(Freelancer.create(getRandomValueFromFieldsList(names),
                    getRandomValueFromFieldsList(specialitys),
                    getRandomValueFromFieldsList(dates),
                    random.nextDouble() * MAX_HRATE));
        }
    }

    public void printContactorsCollection(){
        int count = 1;
        for (Contractor contractors : contractorsList){
            System.out.println(count + "." + contractors);
            count++;
        }
    }

    public void sortContractorsBySalary(){
        Contractor[] array = contractorsList.toArray(new Contractor[0]);
        for (int i = 0; i < array.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[minIndex].compareTo(array[j] ) == 1){
                    minIndex = j;
                }
            }
            Contractor temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        contractorsList = new ArrayList<Contractor>(Arrays.asList(array));
    }


    //region  Вспомогательные методы
    /**
     * генерируем случайное значение поля для классов Worker и Freelancer
     * @param fieldList - поле класса
     * @return - возвращает случайное значение из указанного поля класса
     */
    private String getRandomValueFromFieldsList(String[] fieldList){
        int index = random.nextInt(fieldList.length);
        return fieldList[index];
    }
    //endregion

    //region Поля
    private ArrayList<Contractor> contractorsList = new ArrayList<>();
    private int workersAmount;
    private int freelancerAmount;


    //endregion

    //region Константы (final var)
    private static String[] names = {"Иван Иванович", "Сергей Сергеевич",
            "Петр Петрович", "Василий Петрович",
            "Алексей Сергеевич", "Тимофей Тимофеевич",
            "Валентин Валентинович", "Роман Васильевич"};
    private static String[] specialitys = {"штукатур", "маляр", "водитель", "программист",
            "дизайнер", "копирайтер", "разнорабочий", "фрилансер"};
    private static String[] dates = {"15.12.1985", "01.01.1989", "23.04.1991", "20.09.1978",
            "24.11.1969", "28.02.1979", "17.03.1996", "11.11.2000"};
    private static final double MAX_SALARY = 150_000.00;
    private static final double MAX_HRATE = 500.00;

    private static final Random random = new Random();
    //endregion

    //region Свойства
    /**
     * из общего количества исполнителей формируем количествоWorker и Freelancer
     * @param amount - общее количество исполнителей
     */
    public void setAmountContractors(int amount){
        if (amount < 0){
            throw new RuntimeException("Некорректное количество исполнителей.");
        }
        // определяем количество объектов каждого класса: Worker и Freelancer
        workersAmount = amount / 2;
        freelancerAmount = amount / 2;
        if (amount % 2 != 0) {
            freelancerAmount = amount - workersAmount;
        }
    }

    public ArrayList<Contractor> getContractorsList(){
        return contractorsList;
    }

    public int getWorkersAmount(){
        return workersAmount;
    }

    public int getFreelancerAmount(){
        return freelancerAmount;
    }
    //endregion

}
