package org.yearup.data.mysql;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.yearup.data.ShoppingCartDao;
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.ShoppingCartItem;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//@Component

//should return shopping cart instead of whatever
//remove by id



//public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
//
//@Autowired
//private MySqlProductDao productDao;
//    public MySqlShoppingCartDao(DataSource dataSource)
//    {
//        super(dataSource);
//    }
//
//@Override
//public void addItem(ShoppingCartItem shoppingCartItem){
//        String sql = "INSERT INTO shopping_cart" +
//                " SET name = ? " +
//                "   , price = ? " +
//                "   , category_id = ? " +
//                "   , description = ? " +
//                "   , color = ? " +
//                "   , image_url = ? " +
//                "   , stock = ? " +
//                "   , featured = ? " ;
//
//        try (Connection connection = getConnection())
//        {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, shoppingCartItem.getProduct().getName());
//            statement.setBigDecimal(2, shoppingCartItem.getProduct().getPrice());
//            statement.setInt(3,shoppingCartItem.getProduct().getCategoryId());
//            statement.setString(4,shoppingCartItem.getProduct().getDescription());
//            statement.setString(5,shoppingCartItem.getProduct().getColor());
//            statement.setString(6,shoppingCartItem.getProduct().getImageUrl());
//            statement.setInt(7,shoppingCartItem.getProduct().getStock());
//            statement.setBoolean(8,shoppingCartItem.getProduct().isFeatured());
//
//
//            statement.executeUpdate();
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public ShoppingCart getByUserId(int userId) {
//        return null;
//    }
//}
