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
    private Connection connection;
    private CategoryServiceI categoryService;

    public ProductService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
        categoryService = new CategoryService();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        try {
            List<Product> products = new ArrayList<>();
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM product")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    long category_id = resultSet.getLong("category_id");
                    System.out.println(category_id);
                    product.setCategory(categoryService.getCategoryById(category_id));
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
        return null;
    }

    @Override
    public Product getById(int id) throws SQLException {
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM product WHERE id=?")) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.first();
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                return product;
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
        return null;
    }

    @Override
    public List<Product> getAllFromCategory(int id) {
        return null;
    }
}
