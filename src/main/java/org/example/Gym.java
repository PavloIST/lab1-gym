package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Gym {
    String name;
    ArrayList<Visitor> visitorList;
    ArrayList<Trainer> trainerList;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Gym() {
        visitorList = new ArrayList<Visitor>();
        trainerList = new ArrayList<Trainer>();
    }

    public void trainingWithTrainer(Visitor visitor, Trainer trainer){
        visitor.goToGym(this,true, trainer);
    }
    public boolean addVisitor(Visitor visitor){
        if(visitorList.contains(visitor)){

            return false;
        }
        visitorList.add(visitor);
        return true;
    }

    public void updateVisitor(int id, String newName, int newAge){
        Visitor visitor = getVisitor(id);
        if(visitor == null){
            throw new IllegalArgumentException("No visitor found with id: " + id);
        }
        visitor.name = newName;
        visitor.age = newAge;
    }

    public boolean deleteVisitor(Visitor visitor){
        if(!visitorList.contains(visitor)){
            return false;
        }
        visitorList.remove(visitor);
        return true;
    }

    public boolean addTrainer(Trainer trainer){
        if(trainerList.contains(trainer)){
            return false;
        }
        trainerList.add(trainer);
        return true;
    }

    public void importVisitorsFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Visitor[] visitors = mapper.readValue(new File(filePath), Visitor[].class);

        for (Visitor v : visitors) {
            System.out.println("Imported: " + v.getName() + ", age: " + v.getAge());
            addVisitor(v);
        }
    }

    public void exportVisitorsToJson(String filePath) throws IOException {
        visitorList.sort(Comparator.comparingInt(Visitor::getId));

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(new File(filePath), visitorList);
    }

    public Visitor getVisitor(int id) {
        for (Visitor v : visitorList) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }
    public Trainer getTrainer(int id) {
        for (Trainer trainer : trainerList) {
            if (trainer.getId() == id) {
                return trainer;
            }
        }
        return null;
    }


    public List<String> getHistory() {
        String filePath = "visit_log.txt";
        List<String> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return history;
    }


}

