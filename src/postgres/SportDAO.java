package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SportDAO implements DAO<Sport> {
    private static final String URL = "jdbc:postgresql://localhost:5432/sports";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123qwe";

    @Override
    public void add(Sport sport) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO deportes (nombre) VALUES ('" + sport.getName() + "')";
            statement.executeUpdate(sql);
            System.out.println("Deporte agregado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sport> getAll() {
        // Implementación para listar todos los deportes
        return new ArrayList<>();
    }
}