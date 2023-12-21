import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

/**
*The UserDao handles database operations related to entities.
*/
public class UserDao {

    /**
*Creates a user in the database.
*
*@param user The user object containing user information.
*@return True if the user creation is successful, false otherwise.
*/
    public boolean createUser(User user) {
        // Hash the user's password before storing it in the database
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, user.isDoctor());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
*Retrieves a user from the database based on the user's ID.
*
*@param id The ID of the user to retrieve.
*@return The User object representing the retrieved user.
*/
    public User getUserById(int id) {
        User user = null;
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
*Retrieves a user from the database based on the user's email.
*
*@param email The email of the user to retrieve.
*@return The User object representing the retrieved user.
*/
    public User getUserByEmail(String email) {
        User user = null;
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }





    /**
*Updates a user's information in the database.
*
*@param user The User object containing updated information.
*@return True if the update is successful, false otherwise.
*/
    public boolean updateUser(User user) {
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET first_name=?, last_name=?, password=?, is_doctor=? WHERE id=?")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isDoctor());
            statement.setInt(5, user.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
*Deletes a user from the database based on the user's ID.
*
*@param id The ID of the user to delete.
*@return True if the deletion is successful, if not false.
*/


    public boolean deleteUser(int id) {
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
*Verifies a user's password against the hashed password stored in the database.
*
*@param email The email.
*@param password The password.
*@return True if the password is correct, false otherwise.
*/
    public boolean verifyPassword(String email, String password) {
        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement("SELECT password FROM users WHERE email = ?")) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
*Creates a User object from the result set obtained from a database query.
*
*@param resultSet The result set containing user information.
*@return The User object created from the result set.
*@throws SQLException If there is an SQL exception.
*/
    public User createUserFromResultSet(ResultSet resultSet) throws SQLException {


        
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        boolean isDoctor = resultSet.getBoolean("is_doctor");



        return new User(id, firstName, lastName, email, password, isDoctor);
    }
}

