package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Gym gym = new Gym();
    public static void main(String[] args) {
        System.out.println("\n----- Menu -----");
        System.out.println("1. Register a visitor");
        System.out.println("2. View all visitors");
        System.out.println("3. Register a trainer");
        System.out.println("4. View all trainers");
        System.out.println("5. Go to Gym");
        System.out.println("6. Training with a trainer");
        System.out.println("7. View visitor information");
        System.out.println("8. Export visitors to JSON");
        System.out.println("9. Import visitors from JSON");
        System.out.println("0. Get history");
        System.out.println("`. Exit");

        while(true){

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> registerVisitor();
                case "2" -> viewVisitors();
                case "3" -> registerTrainer();
                case "4" -> viewTrainers();
                case "5" -> goToGym();
                case "6" -> trainingWithTrainer();
                case "7" -> showVisitorInfo();
                case "8" -> exportVisitors();
                case "9" -> importVisitors();
                case "0" -> getHistory();
                case "`" -> {
                    return;
                }
            }

        }

    }

    static void registerVisitor(){
        System.out.print("Visitor name: ");
        String name = scanner.nextLine();
        System.out.print("Visitor age: ");
        int age = scanner.nextInt();
        System.out.print("Visitor id: ");
        int id = scanner.nextInt();
        if(gym.addVisitor(new Visitor(name,age,id))){
            System.out.println("Visitor registered");
            return;
        }
        System.out.println("Visitor already registered");
    }

    static void viewVisitors(){
        for(Visitor visitor1 : gym.visitorList){
            System.out.printf("ID: %d, Name: %s, Age: %d\n", visitor1.id, visitor1.name, visitor1.age);
        }
    }

    static void registerTrainer(){
        System.out.print("Trainer name: ");
        String name = scanner.nextLine();
        System.out.print("Trainer age: ");
        int age = scanner.nextInt();
        System.out.print("Trainer id: ");
        int id = scanner.nextInt();
        if(gym.addTrainer(new Trainer(name,age,id))){
            System.out.println("Trainer registered");
            return;
        }
        System.out.println("Trainer already registered");
        }
    static void viewTrainers(){
        for(Trainer trainer : gym.trainerList){
            System.out.printf("ID: %d, Name: %s, Age: %d\n", trainer.id, trainer.name, trainer.age);
        }
    }
    static void goToGym(){
        System.out.print("Visitor ID: ");
        int id = scanner.nextInt();
        Visitor visitor = findVisitorById(id);
        visitor.goToGym(gym,false, null);
        System.out.println("The visitor went to the gym");
    }
    static Visitor findVisitorById(int targetId) {
        for (Visitor visitor : gym.visitorList) {
            if (visitor.id == targetId) {
                return visitor;
            }
        }
        return null;
    }
    static void trainingWithTrainer(){
        System.out.print("Visitor ID: ");
        int id1 = scanner.nextInt();
        System.out.print("Trainer ID: ");
        int id2 = scanner.nextInt();
        Visitor visitor = gym.getVisitor(id1);
        Trainer trainer = gym.getTrainer(id2);
        gym.trainingWithTrainer(visitor,trainer);
        System.out.println("The visitor went to the gym");
    }

    static void showVisitorInfo() {
        System.out.print("Visitor ID: ");
        int id = scanner.nextInt();
        Visitor visitor = findVisitorById(id);
        System.out.println(visitor.getInfo());
    }
    static void exportVisitors(){
        System.out.print("File path: ");
        String filePath = scanner.nextLine();
        try {
            gym.exportVisitorsToJson(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Export completed");
    }

    static void importVisitors(){
        System.out.print("File path: ");
        String filePath = scanner.nextLine();
        try {
            gym.importVisitorsFromJson(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void getHistory(){
        List<String> visitHistory = gym.getHistory();
        visitHistory.forEach(System.out::println);
    }
}


