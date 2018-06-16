package foo.bar.animal.dbConnetion;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import foo.bar.animal.service.AnimalService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectingToSqlite {
    private static List<Animal> animalList = AnimalService.animalList;

    public ConnectingToSqlite() throws NamingException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
        try (Connection connection = ds.getConnection()) {

            connection.createStatement().executeUpdate("DROP TABLE if EXISTS Animals");
            System.out.print("Droping table ...");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Animals (id VARCHAR , `name` VARCHAR , kingdom VARCHAR, details VARCHAR ,editable INT)");
            System.out.println("Creating table ...");
            connection.createStatement().executeUpdate("INSERT INTO Animals (id , name, kingdom, details  ,editable ) VALUES " + animalList.stream().map(Animal::sql).collect(Collectors.joining(",")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Animal findOne(String id1) throws NamingException {

        Animal tempAnimal = new Animal();
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
        try (Connection connection = ds.getConnection()) {
            id1 = "\"" + id1 + "\"";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Animals  WHERE  `id` = " + id1);
            ResultSet resultSet = preparedStatement.executeQuery();
            tempAnimal.setId(resultSet.getString("id"));
            tempAnimal.setName(resultSet.getString("name"));
            tempAnimal.setKingdom(AnimalKingdom.valueOf(resultSet.getString("kingdom")));
            tempAnimal.setDetails(resultSet.getString("details"));
            tempAnimal.setEditeble(Boolean.valueOf(resultSet.getString("editable")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempAnimal;
    }

    public List<Animal> findAll() throws NamingException {
        ArrayList<Animal> tempList = new ArrayList<>();

        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
        try (Connection connection = ds.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Animals ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Animal tempAnimal = new Animal();
                tempAnimal.setId(resultSet.getString("id"));
                tempAnimal.setName(resultSet.getString("name"));
                tempAnimal.setKingdom(AnimalKingdom.valueOf(resultSet.getString("kingdom")));
                tempAnimal.setDetails(resultSet.getString("details"));
                tempAnimal.setEditeble(Boolean.valueOf(resultSet.getString("editable")));
                tempList.add(tempAnimal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public void add(Animal animal) throws NamingException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
        try (Connection connection = ds.getConnection()) {
            animal.setDetails(animal.getDetails().replaceAll("'", ""));
            connection.createStatement().executeUpdate("INSERT INTO Animals (id , name, kingdom, details  ,editable ) VALUES " + animal.sql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String idToRemove) throws NamingException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
        try (Connection connection = ds.getConnection()) {
            idToRemove = "\'" + idToRemove + "\'";
            connection.createStatement().executeUpdate("DELETE FROM Animals WHERE  `id` =" + idToRemove);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
