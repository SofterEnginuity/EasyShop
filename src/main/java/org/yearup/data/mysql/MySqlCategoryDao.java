package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    DataSource dataSource;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM categories")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


    @Override
    public List<Category> getById(int categoryId)
    {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM categories WHERE category_id LIKE ?")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }




    @Override
    public Category create(Category category)
    {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO categories VALUES(?,?,?)")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
