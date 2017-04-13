package vadimCo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Вадим on 12.04.2017.
 */
public class ShopController {
    private Shop shop;

    public ShopController() {
        shop = new Shop();
        shop.loadShopInfo();
    }

    public String getAvailableEquipment(){
        Map<SportEquipment, Integer> avEq = shop.getAvailableEquipment();
        String listOfAvailableEquipment = "";
        for (Map.Entry<SportEquipment, Integer> pair : avEq.entrySet()){
            listOfAvailableEquipment += pair.getKey().toString()+"\n";
        }
        return listOfAvailableEquipment;
    }

    public String getAvailableEquipmentList(){
        ArrayList<SportEquipment> avEq = shop.getAvailableEquipmentList();
        String listOfAvailableEquipment = "";
        for (SportEquipment equipment : avEq){
            listOfAvailableEquipment += equipment.toString()+"\n";
        }
        return listOfAvailableEquipment;
    }
    public String getAllEquipment(){
        Map<SportEquipment, Integer> equipment = shop.getGoods();
        String listOfAvailableEquipment = "";
        for (Map.Entry<SportEquipment, Integer> pair : equipment.entrySet()){
            listOfAvailableEquipment += pair.getKey().toString()+"\n";
        }
        return listOfAvailableEquipment;
    }

    public String getRentedEquipment(){
        Map<SportEquipment, Integer> equipment = shop.getRentedEquipment();
        String listOfAvailableEquipment = "";
        for (Map.Entry<SportEquipment, Integer> pair : equipment.entrySet()){
            listOfAvailableEquipment += pair.getKey().toString()+"\n";
        }
        return listOfAvailableEquipment;
    }

    public String getClients(){
        String listOfClients = "";
        int i =1;
        for (Person person : shop.getClients()){
            listOfClients += i + person.toString()+"\n";
            i++;
        }
        return listOfClients;
    }


    public void addNewPerson(Scanner scanner){
        Person person = new Person();
        System.out.print("Enter the the surname: ");
        person.setSurname(scanner.nextLine());
        System.out.print("\nEnter the name: ");
        person.setName(scanner.nextLine());
        System.out.print("\nEnter the name: ");
        shop.addNewPerson(person);
    }

    public void choosePerson(int i){
        shop.setCurrentPerson(shop.getClients().get(i));
    }

    public void rentUnit(int i){
        shop.getCurrentPerson().getRentUnit().addUnit(shop.getAvailableEquipmentList().get(i));
    }

    public int getClientsNumber(){
        return shop.getClients().size();
    }

    public int getEquipmentNumber(){
        return shop.getAvailableEquipmentList().size();
    }

}
