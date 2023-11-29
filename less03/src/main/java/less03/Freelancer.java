package less03;

public class Freelancer extends Contractor{

    private double hRate;

    public static Freelancer create(String name, String speciality, String birthDate, double hRate){
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя человека.");
        }
        if (speciality == null || speciality.length() < 3){
            throw new RuntimeException("Некорректное наименование специальности.");
        }
        if (hRate <= 0){
            throw new RuntimeException("Некорректный размер зарплаты.");
        }
        return new Freelancer(name, speciality, birthDate, hRate);
    }

    private Freelancer(String name, String speciality, String birthDate, double hRate){
        super(name, speciality, birthDate);
        this.hRate = hRate;
    }

    @Override
    public double getSalary() {
        return 20.8 * 8 * hRate;
    }

}
