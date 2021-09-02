
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    ArrayList<Item> loadItems(String filePath) throws FileNotFoundException {
        ArrayList<Item> payload = new ArrayList<>();
        try {
            File manifest = new File(filePath);
            Scanner manifestScanner = new Scanner(manifest);
            String[] payloadItem;
            while (manifestScanner.hasNextLine()) {
                Item item = new Item();
                payloadItem = manifestScanner.nextLine().split("=");
                item.setName(payloadItem[0]);
                item.setWeight(Integer.parseInt(payloadItem[1]));
                payload.add(item);
            }
            manifestScanner.close();
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter weights in numeric format!");
        } catch (IndexOutOfBoundsException iob) {
            System.out.println("Please separate payload item name and weight with '='");
        }
        return payload;
    }

    ArrayList<Rocket> loadU1(ArrayList<Item> payload) {
        ArrayList<Rocket> u1Rockets = new ArrayList<>();
        Rocket u1Rocket = new U1();
        for (Item item : payload) {
            if (u1Rocket.canCarry(item)) {
                u1Rocket.carry(item);
            } else {
                u1Rockets.add(u1Rocket);
                u1Rocket = new U1();
                u1Rocket.carry(item);
            }
        }
        if (payload.size() != 0) {
            u1Rockets.add(u1Rocket);
        }
        return u1Rockets;
    }


    ArrayList<Rocket> loadU2(ArrayList<Item> payload) {
        ArrayList<Rocket> u2Rockets = new ArrayList<>();
        Rocket u2Rocket = new U2();
        for (Item item : payload) {
            if (u2Rocket.canCarry(item)) {
                u2Rocket.carry(item);
            } else {
                u2Rockets.add(u2Rocket);
                u2Rocket = new U2();
                u2Rocket.carry(item);
            }
        }
        if (payload.size() != 0) {
            u2Rockets.add(u2Rocket);
        }
        return u2Rockets;
    }


    int runSimulation(ArrayList<Rocket> rockets) {
        int totalBudget = 0;
        for (Rocket rocket : rockets) {
            do {
                totalBudget += rocket.getCost();
            } while (!(rocket.launch() && rocket.land()));
        }
        return totalBudget;
    }
}
