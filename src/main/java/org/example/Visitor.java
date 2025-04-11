package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Visitor {
    public int age;
    public String name;
    public Subscription sub = new Subscription(true);
    public int id;
    public Visitor(){
    }
    public Visitor(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void goToGym(Gym gym, boolean trainingWithTrainer, Trainer trainer){
        try (FileWriter writer = new FileWriter("visit_log.txt",true)) {
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if(trainingWithTrainer){
                writer.write(String.format("%d id %s (%d) trained with %s at the gym at %s%n", id, name, age,trainer.name, time));
            }
            else{
                writer.write(String.format("%d id %s (%d) visited the gym at%s%n", id, name, age, time));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void goToGym(Gym gym, boolean trainingWithTrainer){
        try (FileWriter writer = new FileWriter("visit_log.txt",true)) {
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("%d id %s (%d) visited the gym at%s%n", id, name, age, time));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getInfo(){
        return String.format("%s %d", name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor visitor)) return false;
        return Objects.equals(id, visitor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

