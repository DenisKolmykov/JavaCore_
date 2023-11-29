package less03;

public class Worker extends Contractor{
    private double monthSalary;

    public static Worker create(String name, String speciality, String birthDate, double monthSalary){
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя человека.");
        }
        if (speciality == null || speciality.length() < 3){
            throw new RuntimeException("Некорректное наименование специальности.");
        }
        if (monthSalary <= 0){
            throw new RuntimeException("Некорректный размер зарплаты.");
        }
        return new Worker(name, speciality, birthDate, monthSalary);
    }

    private Worker(String name, String speciality, String birthDate, double monthSalary){
        super(name, speciality, birthDate);
        this.monthSalary = monthSalary;
    }

    @Override
    public double getSalary() {
        return monthSalary;
    }

}
