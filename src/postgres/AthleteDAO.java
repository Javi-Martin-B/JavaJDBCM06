package postgres;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AthleteDAO implements DAO<Athlete> {
    private static final String URL = "jdbc:postgresql://localhost:5432/sports";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123qwe";

    @Override
    public void add(Athlete athlete) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, athlete.getName());
            preparedStatement.setInt(2, athlete.getSportId());
            preparedStatement.executeUpdate();
            System.out.println("Atleta agregado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByName(Scanner scanner) {
        System.out.println("Ingrese el nombre o parte del nombre del atleta:");
        String name = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT a.nombre, a.cod, s.nombre AS sport_name FROM deportistas a " +
                         "JOIN deportes s ON a.cod_deporte = s.cod WHERE LOWER(a.nombre) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Atleta: " + resultSet.getString("nombre") +
                                   ", ID: " + resultSet.getInt("cod") +
                                   ", Deporte: " + resultSet.getString("sport_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listBySport(Scanner scanner, SportDAO sportDAO) {
        System.out.println("Seleccione el ID del deporte para listar los atletas:");
        List<Sport> sports = sportDAO.getAll();
        sports.forEach(sport -> System.out.println(sport.getId() + ". " + sport.getName()));

        int sportId = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT a.nombre, a.cod FROM deportistas a WHERE a.cod_deporte = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sportId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Atleta: " + resultSet.getString("nombre") +
                                   ", ID: " + resultSet.getInt("cod"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Athlete> getAll() {
        return new ArrayList<>();
    }
}