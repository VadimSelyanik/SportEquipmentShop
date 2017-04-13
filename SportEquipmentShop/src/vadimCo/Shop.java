package vadimCo;

import java.io.*;
import java.util.*;

/**
 * Created by Вадим on 12.04.2017.
 */
public class Shop {
    private Map<SportEquipment, Integer> goods;
    private ArrayList<Person> clients;
    private Map<SportEquipment, Integer> availableEquipment;
    private final String shopGoodsInfo = "ShopGoodsInfo";
    private final String shopClientsInfo = "ShopClientsInfo";

    public ArrayList<Person> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Person> clients) {
        this.clients = clients;
    }

    private Person currentPerson;

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public void loadShopInfo() {

        Map<SportEquipment, Integer> goods = new HashMap<>();
        try {
            Scanner cinFile = new Scanner(new InputStreamReader(new FileInputStream(shopGoodsInfo)));
            while (cinFile.hasNext()) {
                SportEquipment equipment = new SportEquipment();
                equipment.setParametersFromString(cinFile.nextLine());
                goods.put(equipment, Integer.parseInt(cinFile.nextLine()));
            }
            cinFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.goods = goods;

        ArrayList<Person> clients = new ArrayList<>();
        try {
            Scanner cinFile = new Scanner(new InputStreamReader(new FileInputStream(shopClientsInfo)));
            while (cinFile.hasNext()) {
                Person person = new Person();
                person.setParametersFromString(cinFile.nextLine());
                clients.add(person);
            }
            cinFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.clients = clients;

        checkAvailableEquipment();
    }

    public Map<SportEquipment, Integer> getAvailableEquipment() {
        checkAvailableEquipment();
        return availableEquipment;
    }

    public Map<SportEquipment, Integer> getRentedEquipment() {
        Map<SportEquipment, Integer> rentedEquipment = new HashMap<>();
        for (Person p : clients) {
            if (!p.getRentUnit().isEmpty()) {
                SportEquipment[] spEq = p.getRentUnit().getUnits();
                for (int i = 0; i < spEq.length; i++) {
                    if (spEq[i] != null) {
                        if (rentedEquipment.containsKey(spEq[i])) {
                            int n = rentedEquipment.get(spEq[i]) + 1;
                            rentedEquipment.put(spEq[i], n);
                        } else rentedEquipment.put(spEq[i], 1);
                    }
                }
            }
        }
        return rentedEquipment;
    }

    private void checkAvailableEquipment() {
        Map<SportEquipment, Integer> rentedEquipment = new HashMap<>();
        for (Person p : clients) {
            if (!p.getRentUnit().isEmpty()) {
                SportEquipment[] spEq = p.getRentUnit().getUnits();
                for (int i = 0; i < spEq.length; i++) {
                    if (spEq[i] != null) {
                        if (rentedEquipment.containsKey(spEq[i])) {
                            int n = rentedEquipment.get(spEq[i]) + 1;
                            rentedEquipment.put(spEq[i], n);
                        } else rentedEquipment.put(spEq[i], 1);
                    }
                }
            }
        }
        availableEquipment = new HashMap<>();
        for (Map.Entry<SportEquipment, Integer> pair : goods.entrySet()) {
            if (rentedEquipment.containsKey(pair.getKey())) {
                int n = pair.getValue() - rentedEquipment.get(pair.getKey());
                if (n != 0)
                    availableEquipment.put(pair.getKey(), n);
            } else availableEquipment.put(pair.getKey(), pair.getValue());
        }
    }

    public ArrayList<SportEquipment> getAvailableEquipmentList() {
        ArrayList<SportEquipment> equipment = new ArrayList<>();
        for (SportEquipment s : getAvailableEquipment().keySet()) {
            equipment.add(s);
        }
        equipment.sort(new Comparator<SportEquipment>() {
            @Override
            public int compare(SportEquipment o1, SportEquipment o2) {
                return o1.compareTo(o2);
            }
        });
        return equipment;
    }

    public void addNewPerson(Person person) {
        clients.add(person);
    }

    public void saveShopInfo() {
        checkAvailableEquipment();
        try {
            FileWriter fw = new FileWriter(shopClientsInfo);
            for (Person person : clients) {
                fw.write(person.getStringOfParameters() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
