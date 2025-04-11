package org.example;

import java.util.Objects;

public class Trainer {
    int age;
    String name;
    public int id ;

    public Trainer(){
    }
    public Trainer(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo(){
        return String.format("%s %d", name, age);
    }
    public int getId(){return id;}
    public void SetId(int id){this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer trainer)) return false;
        return Objects.equals(id, trainer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

