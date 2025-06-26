package org.yearup.data;

import org.yearup.models.Category;
import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart create(ShoppingCart shoppingCart);

    void update(int userId, int productId);
    void delete(int userId);


}
