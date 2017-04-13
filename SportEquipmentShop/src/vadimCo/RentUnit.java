package vadimCo;

import java.util.Arrays;

/**
 * Created by Вадим on 12.04.2017.
 */
public class RentUnit {
    private SportEquipment[] units;
    private final int MAX_ELEMENTS_NUMBER=3;
    private int currentNumberOfUnits;

    public SportEquipment[] getUnits() {
        return units;
    }

    public void setUnits(SportEquipment[] units) {
        this.units = units;
    }

    public RentUnit(){
        units = new SportEquipment[3];
        currentNumberOfUnits = 0;
    }

    public void addUnit(SportEquipment equipment){
        for (int i =0; i< MAX_ELEMENTS_NUMBER; i++){
            if(units[i]==null){
                units[i] = equipment;
                currentNumberOfUnits++;
                return;
            }
        }
    }

    public boolean isFull(){
        if (currentNumberOfUnits==MAX_ELEMENTS_NUMBER){
            return true;
        }else return false;
    }

    public boolean isEmpty(){
        if (currentNumberOfUnits==0)
            return true;
        else return false;
    }

    public void deleteUnit(int number){
        currentNumberOfUnits--;
        units[number] = null;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i =0; i< MAX_ELEMENTS_NUMBER; i++){
            if (units[i]!=null){
                str+=units[i].toString()+',';
            }
        }
        if (str.length()!=0)
            return str.substring(0,str.length()-1);
        else
            return "no equipment rented";
    }
}
