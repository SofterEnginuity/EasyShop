package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart addProduct(int userId, int productId);

    ShoppingCart addProduct(int userId, int productId, int quantity);
    ShoppingCart getQuantity(int userId, int productId, int quantity);

    ShoppingCart updateQuantity(int userId, int productId, int quantity);
    ShoppingCart removeProduct(int userId, int productId);


    ShoppingCart clearCart(int userId);
    // add remove product method
    // you might need a method to check if we have a product in a shopping cart



//    void delete(ShoppingCart shoppingCart, int userId);
}
