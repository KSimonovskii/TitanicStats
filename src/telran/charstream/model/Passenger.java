package telran.charstream.model;

import telran.charstream.model.enums.Category;
import telran.charstream.model.enums.Port;
import telran.charstream.model.enums.Sex;

import java.util.IllegalFormatException;

public class Passenger {

    private int id;
    private boolean survived;
    private int pClass;
    private String name;
    private Sex sex;
    private double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private Port embarked;
    private Category category;

    public Passenger(int id, boolean survived,
                     int pClass, String name,
                     Sex sex, double age,
                     int sibSp, int parch,
                     String ticket, double fare,
                     String cabin, Port embarked) {
        this.id = id;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
        this.category = getCategoryOfPassenger();
    }

    public Passenger(String[] data) {

            this.id = Integer.parseInt(data[0]);
            this.survived = Integer.parseInt(data[1]) == 1;
            this.pClass = Integer.parseInt(data[2]);
            this.name = (data[3] + " " + data[4]).replaceAll("\"", "");
            this.sex = Sex.valueOf(data[5].toUpperCase());
            if (!data[6].isEmpty()){
                this.age = Double.parseDouble(data[6]);
            }
            this.sibSp = Integer.parseInt(data[7]);
            this.parch = Integer.parseInt(data[8]);
            this.ticket = data[9];
            this.fare = Double.parseDouble(data[10]);
            this.cabin = data[11];
            if (data.length > 12) {
                this.embarked = Port.valueOf(data[12].toUpperCase());
            }
            this.category = getCategoryOfPassenger();
    }

    public int getId() {
        return id;
    }

    public boolean isSurvived() {
        return survived;
    }

    public int getpClass() {
        return pClass;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public double getAge() {
        return age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public double getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public Port getEmbarked() {
        return embarked;
    }

    public Category getCategory() {
        return category;
    }

    private Category getCategoryOfPassenger(){
        if (getAge() < 18) {
            return Category.CHILD;
        } else if (getSex() == Sex.MALE) {
            return Category.MALE;
        } else if (getSex() == Sex.FEMALE) {
            return Category.FEMALE;
        } else return null;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", survived=" + survived +
                ", pClass=" + pClass +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", sibSp=" + sibSp +
                ", parch=" + parch +
                ", ticket='" + ticket + '\'' +
                ", fare=" + fare +
                ", cabin='" + cabin + '\'' +
                ", embarked=" + embarked +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Passenger passenger = (Passenger) object;

        return getId() == passenger.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}


