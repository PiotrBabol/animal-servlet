package foo.bar.dbConnetion;

import foo.bar.animal.model.Animal;
import foo.bar.animal.service.AnimalService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ConnectingToSqlite {

    public static void connect() {
        AnimalService animalService;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db.Animals")) {
            animalService = new AnimalService();
            connection.createStatement().executeUpdate("DROP TABLE if EXISTS Animals");
            System.out.print("Droping table ...");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Animals (id VARCHAR , `name` VARCHAR , kingdom VARCHAR, details VARCHAR ,editable INT)");
            System.out.println("Creating table ...");
            connection.createStatement().executeUpdate("INSERT INTO Animals (id , name, kingdom, details  ,editable ) VALUES " + AnimalService.animalList.stream().map(Animal::sql).collect(Collectors.joining(",")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
