package vadimCo;

import java.util.StringTokenizer;

/**
 * Created by ����� on 12.04.2017.
 */
public class Person implements InitializedFromString {
    private String surname;
    private String name;
    private RentUnit rentUnit;

    public Person() {
        rentUnit = new RentUnit();
    }

    public RentUnit getRentUnit() {
        return rentUnit;
    }

    public void setRentUnit(RentUnit rentUnit) {
        this.rentUnit = rentUnit;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return surname + ' ' + name + ": " +
                rentUnit.toString();
    }

    @Override
    public void setParametersFromString(String str) {
        StringTokenizer strTok = new StringTokenizer(str, "/");
        setSurname(strTok.nextToken());
        setName(strTok.nextToken());
        while (strTok.hasMoreTokens()) {
            SportEquipment equipment = new SportEquipment();
            equipment.setParametersFromString(strTok.nextToken());
            rentUnit.addUnit(equipment);
        }
    }

    @Override
    public String getStringOfParameters() {
        String str = surname + '/' + name + '/';
        SportEquipment[] units = getRentUnit().getUnits();
        for (int i = 0; i < units.length; i++) {
            if (units[i] != null) {
                str += units[i].getStringOfParameters() + '/';
            }
        }
        return str;
    }
}
