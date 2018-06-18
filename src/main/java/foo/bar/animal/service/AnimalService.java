package foo.bar.animal.service;

import foo.bar.animal.model.Animal;
import foo.bar.data.AnimalDAO;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.util.List;

public class AnimalService {

    private Logger log = Logger.getLogger(AnimalService.class);
    private AnimalDAO dao;

    public AnimalService() {
        try {
            dao = new AnimalDAO();
        } catch (NamingException e) {
            log.error("Naming exception problem", e);
        }
    }


    public Animal findOne(String Id) {
        try {
            return dao.findOne(Id);
        } catch (NamingException e) {
            log.error("Naming exception problem", e);
            return null;
        }

    }

    public List<Animal> findAll() {
        try {
            return dao.findAll();
        } catch (NamingException e) {
            log.error("Naming exception problem", e);
            return null;
        }
    }

    public void add(Animal animal) {
        animal.setDetails(animal.getDetails().replaceAll("'", ""));
        try {
            dao.add(animal);
        } catch (NamingException e) {
            log.error("Naming exception problem", e);
        }
    }

    public void remove(String idToRemove) {
        try {
            dao.remove(idToRemove);
        } catch (NamingException e) {
            log.error("Naming exception problem", e);
        }
    }


}
