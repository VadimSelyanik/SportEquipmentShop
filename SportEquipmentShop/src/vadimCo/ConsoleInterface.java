package vadimCo;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * Created by Âàäèì on 12.04.2017.
 */
public class ConsoleInterface {
    private static final String MENU_TITLE = "" +
            "1 - Rent new unit\n" +
            "2 - Show all equipment\n" +
            "3 - Show rented equipment" + "\n" +
            "4 - Show available equipment" + "\n" +
            "5 - Show clients info" + "\n" +
            "0 - Exit";
    private static final String SUB_MENU_TITLE = "1 - Ñğàæåíèå\n2 - Èíâåíòàğü\n3 - Ñîõğàíèòü è âûéòè\n4 - Âûéòè";
    private static final String WRONG_INPUT = "Wrong input parameters. Please enter again.";
    private static final String EXIT = "0 - Exit.";
    private static final String CHOICE = "You choice: ";
    private static Scanner cin;
    private static ShopController shopController;

    public static void main(String[] args) {
        cin = new Scanner(System.in);
        shopController = new ShopController();
        int answer = -1;
        while (answer != 0) {
            System.out.println(MENU_TITLE);
            try {
                answer = Integer.parseInt(cin.nextLine());
                switch (answer) {
                    case 0:
                        return;
                    case 1:
                        rentNewUnit();
                        break;
                    case 2:
                        allEquipment();
                        break;
                    case 3:
                        rentedEquipment();
                        break;
                    case 4:
                        availableEquipment();
                        break;
                    case 5:
                        clientsInfo();
                        break;
                    default: {
                        System.out.println(WRONG_INPUT);
                        //cin.nextLine();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT);
                //cin.nextLine();
            }
        }
    }

    private static void commonMenu() {
        while (true) {
            try {
                int answer = Integer.parseInt(cin.nextLine());
                if (answer == 0)
                    return;
                else System.out.println(WRONG_INPUT);
            }catch (NumberFormatException e){
                System.out.println(WRONG_INPUT);
            }
        }
    }

    private static void availableEquipment() {
        System.out.println("All available equipment: ");
        System.out.print(shopController.getAvailableEquipment());
        System.out.println(EXIT);
        commonMenu();
//        while (true) {
//            int answer = Integer.parseInt(cin.nextLine());
//            if (answer == 0)
//                return;
//            else System.out.println(WRONG_INPUT);
//        }
    }

    private static void clientsInfo() {
        System.out.println("All clients: ");
        System.out.print(shopController.getClients());
        System.out.println(EXIT);
        commonMenu();
//        while (true) {
//            int answer = Integer.parseInt(cin.nextLine());
//            if (answer == 0)
//                return;
//            else System.out.println(WRONG_INPUT);
//        }
    }

    private static void rentedEquipment() {
        System.out.println("All rented equipment: ");
        System.out.print(shopController.getRentedEquipment());
        System.out.println(EXIT);
        commonMenu();
//        while (true) {
//            try {
//                int answer = Integer.parseInt(cin.nextLine());
//                if (answer == 0)
//                    return;
//                else System.out.println(WRONG_INPUT);
//            } catch ()
//        }
    }

    private static void allEquipment() {
        System.out.println("All equipment: ");
        System.out.print(shopController.getAllEquipment());
        System.out.println(EXIT);
        commonMenu();
//        while (true) {
//            int answer = Integer.parseInt(cin.nextLine());
//            if (answer == 0)
//                return;
//            else System.out.println(WRONG_INPUT);
//        }
    }

    private static void rentNewUnit() {
        while (true) {
            try {
                System.out.println("Please choose a person to rent an equipment: ");
                System.out.print(shopController.getClients());
                int amount = shopController.getClientsNumber();
                System.out.println((amount + 1) + " - Add new person");
                System.out.println(EXIT);
                System.out.println(CHOICE);
                int number = Integer.parseInt(cin.nextLine());
                if (number >= 1 && number <= amount) {
                    if (shopController.choosePerson(number - 1))
                        chooseEquipment();
                    else
                        System.out.println("Current person can't rent new equipment. Ask to give back some equipment.");
                } else if (number == 0) return;
                else if (number == amount + 1)
                    shopController.addNewPerson(cin);
                else System.out.println(WRONG_INPUT);
            }catch (NumberFormatException e){
                System.out.println(WRONG_INPUT);
            }
        }
    }

    private static void chooseEquipment() {
        while (true) {
            try {
                System.out.println("Please choose equipment: ");
                System.out.print(shopController.getAvailableEquipmentList());
                int amount = shopController.getEquipmentNumber();
                System.out.println(EXIT);
                System.out.println(CHOICE);
                int number = Integer.parseInt(cin.nextLine());
                if (number >= 1 && number <= amount) {
                    shopController.rentUnit(number - 1);
                    return;
                } else if (number == 0) return;
                else System.out.println(WRONG_INPUT);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT);
            }
        }
    }
}
