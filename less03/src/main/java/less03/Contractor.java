package less03;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Contractor implements Comparable<Contractor> {
    public int getAge() {
        Date now = new Date();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDateD;
        try {
            birthDateD = formatter.parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int age = now.getYear() - birthDateD.getYear();
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, возраст(лет): %d, доход в мес (руб): %.2f (%s)", name, speciality, getAge(), getSalary(), this.getClass().getSimpleName());
    }


    public abstract double getSalary();


    //compare SALARY
    @Override
    public int compareTo(Contractor contractor2) {
        if (this.getSalary() > contractor2.getSalary()){
            return 1;
        } else if (this.getSalary() < contractor2.getSalary()){
            return -1;
        }
        return 0;
    }

//    //compare AGE
//    @Override
//    public int compareTo(Contractor contractor2) {
//        if (this.getAge() > contractor2.getAge()){
//            return 1;
//        } else if (this.getAge() < contractor2.getAge()){
//            return -1;
//        }
//        return 0;
//    }

    //region Поля
    protected String name;
    protected String speciality;
    protected String birthDate;
    //endregion

    //region Конструкторы
    protected Contractor(String name, String speciality, String birthDate) {
        this.name = name;
        this.speciality = speciality;
        this.birthDate = birthDate;
    }
    //endregion

    //region Свойства
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 3) {
            throw new RuntimeException("Некорректное имя человека.");
        }
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        if (speciality == null || speciality.length() < 3) {
            throw new RuntimeException("Некорректное наименование специальности.");
        }
        this.speciality = speciality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
//endregion
}
