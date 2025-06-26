package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart create(int userId, int product_Id);

    ShoppingCart update(int userId, int productId);
    void delete(int userId);


}
