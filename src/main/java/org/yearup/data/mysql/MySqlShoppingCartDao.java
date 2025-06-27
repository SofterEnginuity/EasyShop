package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    private ProductDao productDao;

    @Autowired

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();

        String sql = "SELECT p.*, sc.quantity " +
                "FROM shopping_cart sc " +
                "JOIN products p ON sc.product_id = p.product_id " +
                "WHERE sc.user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = MySqlProductDao.mapRow(rs);
                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(product);
                item.setQuantity(rs.getInt("quantity"));

                shoppingCart.add(item);
            }

        } catch (SQLException sqlException) {
            System.out.println("Error retrieving cart: " + sqlException.getLocalizedMessage());
        }

        return shoppingCart;
    }


    @Override
    public ShoppingCart addProduct(int userId, int productId) {

        return addProduct(userId, productId, 1);
    }

    @Override
    public ShoppingCart addProduct(int userId, int productId, int quantity) {
        String selectSql = "SELECT quantity FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        String updateSql = "UPDATE shopping_cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
        String insertSql = "INSERT INTO shopping_cart(user_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {

            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            selectStmt.setInt(1, userId);
            selectStmt.setInt(2, productId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, userId);
                updateStmt.setInt(3, productId);
                updateStmt.executeUpdate();
                System.out.println("Quantity updated.");
            } else {
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, productId);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
                System.out.println("Added to cart :)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getByUserId(userId);
    }

    @Override
    public ShoppingCart getQuantity(int userId, int productId, int quantity) {
        return null;
    }

    @Override
    public ShoppingCart updateQuantity(int userId, int productId, int quantity) {
        String sql = "UPDATE shopping_cart" +
                " SET quantity = ? ";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, 1);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getByUserId(userId);
    }

    @Override
    public ShoppingCart removeProduct(int userId, int productId) {
        String sql = "DELETE FROM shopping_cart" +
                " WHERE product_id = ? ";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, 1);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getByUserId(userId);
    }


    @Override
    public ShoppingCart clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart " +
                " WHERE user_id = ?;";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getByUserId(userId);
    }

}
