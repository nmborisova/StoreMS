package bg.smg.service;

import bg.smg.model.Product;
import bg.smg.util.DBManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductServiceI {

    private DataSource dataSource;

    public ProductService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        // expect a SQLNonTransientConnectionException (too many connections)
        // see README.md
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM product")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    products.add(product);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllFromCategory(int id) {
        return null;
    }
}
