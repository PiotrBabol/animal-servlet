package foo.bar.animal.model;


import foo.bar.animal.service.AnimalService;

import java.io.Serializable;
import java.util.UUID;

public class Animal implements Serializable {

    private String id;
    private String name;
    private AnimalKingdom kingdom;
    private String details;
    private boolean editeble;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isEditeble() {
        return editeble;
    }

    public void setEditeble(boolean editeble) {
        this.editeble = editeble;
    }

    public Animal() {
    }

    public Animal(Boolean unknown) {
        this.id = String.valueOf(-1);
        this.name = "unknown animal";
    }

    public Animal(String name, AnimalKingdom kingdom) {
        this.id = String.valueOf(AnimalService.animalList.size());
        this.name = name.toLowerCase();
        this.kingdom = kingdom;
    }

    public Animal(String name, AnimalKingdom kingdom, String details) {
        this.id = String.valueOf(AnimalService.animalList.size());
        this.name = name;
        this.kingdom = kingdom;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", kingdom=" + kingdom ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalKingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(AnimalKingdom kingdom) {
        this.kingdom = kingdom;
    }
}
