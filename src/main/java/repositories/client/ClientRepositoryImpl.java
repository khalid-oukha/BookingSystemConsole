package repositories.client;

import Entities.Client;
import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepositoryImpl implements ClientRepository {

    private Connection connection;

    public ClientRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public void addClient(Client client) {
        String sql = "INSERT INTO clients (cin, full_name) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getCin());
            statement.setString(2, client.getFullName());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    @Override
    public Client findByCin(String cin) {
        String sql = "SELECT * FROM clients WHERE cin = ?";
        Client client = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String clientCin = resultSet.getString("cin");
                String fullName = resultSet.getString("full_name");

                client = new Client(clientCin, fullName);
            }
        } catch (SQLException e) {
            System.out.println("Error finding client by CIN: " + e.getMessage());
        }

        return client;
    }
}
