package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//should return shopping cart instead of whatever
//remove by id


@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    //private ShoppingCart shoppingCart;
 //   private ShoppingCartItem shoppingCartItem;
    private ProductDao productDao;

   //  private Product product;
   // private User user;
@Autowired
//private MySqlProductDao mySqlProductDao;
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }




    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart shoppingCart = new ShoppingCart();


        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";


        try (Connection connection = getConnection()){

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1,userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){

                //int userIdFromDB = rs.getInt("user_id");
                int productIDFromDB = rs.getInt("product_id");
                int quantityFromDB = rs.getInt("quantity");

                System.out.println(productIDFromDB);
                System.out.println(quantityFromDB);

//                Product product = productDao.getById(productIDFromDB);
   //             ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//
//                shoppingCartItem.setProduct(product);
  //              shoppingCartItem.setQuantity(quantityFromDB);
//
  //               shoppingCart.add(shoppingCartItem);
            }



        }catch(SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart create(int userId, int productId) {
        String sql = "INSERT INTO shopping_cart(user_id, product_id, quantity) " +
                " VALUES (?, ?, ?);";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1,userId);
            statement.setInt(2,productId);
            statement.setInt(3, 1);

            statement.executeUpdate();

            return getByUserId(userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        @Override
    public ShoppingCart update(int userId, int productId) {
        String sql = "UPDATE shopping_cart" +
                " SET userId = ? " +
                "   , product_Id = ? " +
                "   , quantity = ? ";


    return null;
    }

    @Override
    public void delete(int userId) {

    }
}
