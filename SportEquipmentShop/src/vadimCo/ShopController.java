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

    public String getAvailableEquipment() {
        Map<SportEquipment, Integer> avEq = shop.getAvailableEquipment();
        String listOfAvailableEquipment = "";
        int i = 1;
        for (Map.Entry<SportEquipment, Integer> pair : avEq.entrySet()) {
            listOfAvailableEquipment += i + ". " + pair.getKey().toString() + ". Free " + pair.getValue() + " units\n";
            i++;
        }
        return listOfAvailableEquipment;
    }

    public String getAvailableEquipmentList() {
        ArrayList<SportEquipment> avEq = shop.getAvailableEquipmentList();
        Map<SportEquipment, Integer> mapEquipment = shop.getAvailableEquipment();
        String listOfAvailableEquipment = "";
        int i = 1;
        for (SportEquipment equipment : avEq) {
            listOfAvailableEquipment += i + ". " + equipment.toString() + ". Free " + mapEquipment.get(equipment) + " units\n";
            i++;
        }
        return listOfAvailableEquipment;
    }

    public String getAllEquipment() {
        Map<SportEquipment, Integer> equipment = shop.getGoods();
        String listOfAvailableEquipment = "";
        int i = 1;
        for (Map.Entry<SportEquipment, Integer> pair : equipment.entrySet()) {
            listOfAvailableEquipment += i + ". " + pair.getKey().toString() + ". At all " + pair.getValue() + " units\n";
            i++;
        }
        return listOfAvailableEquipment;
    }

    public String getRentedEquipment() {
        Map<SportEquipment, Integer> equipment = shop.getRentedEquipment();
        String listOfAvailableEquipment = "";
        int i = 1;
        for (Map.Entry<SportEquipment, Integer> pair : equipment.entrySet()) {
            listOfAvailableEquipment += i + ". " + pair.getKey().toString() + ". Rented " + pair.getValue() + " units\n";
            i++;
        }
        return listOfAvailableEquipment;
    }

    public String getClients() {
        String listOfClients = "";
        int i = 1;
        for (Person person : shop.getClients()) {
            listOfClients += i + ". " + person.toString() + "\n";
            i++;
        }
        return listOfClients;
    }


    public void addNewPerson(Scanner scanner) {
        Person person = new Person();
        System.out.print("Enter the the surname: ");
        person.setSurname(scanner.nextLine());
        System.out.print("Enter the name: ");
        person.setName(scanner.nextLine());
        shop.addNewPerson(person);
    }

    public boolean choosePerson(int i) {
        Person person = shop.getClients().get(i);
        if (person.getRentUnit().isFull())
            return false;
        else {
            shop.setCurrentPerson(person);
            return true;
        }
    }

    public void rentUnit(int i) {
        shop.getCurrentPerson().getRentUnit().addUnit(shop.getAvailableEquipmentList().get(i));
    }

    public int getClientsNumber() {
        return shop.getClients().size();
    }

    public int getEquipmentNumber() {
        return shop.getAvailableEquipmentList().size();
    }

}
