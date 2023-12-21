import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class handles the data access operations for health-related data.
 */
public class HealthDataDao {
    /**
     * Creates a new health data entry in the database.
     *
     * @param healthData The health data to be stored.
     * @return True if the creation is successful; false otherwise.
     */
    public boolean createHealthData(HealthData healthData) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establish a database connection
            connection = DatabaseConnection.getCon();
            // SQL query to insert health data
            String query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // Set values for the parameters
            statement.setInt(1, healthData.getUserId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
            // Convert the date string to java.sql.Date
            statement.setDate(6, java.sql.Date.valueOf(healthData.getDate()));
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            // Retrieve generated keys (auto-incremented ID)
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int healthDataId = generatedKeys.getInt(1);
                    healthData.setId(healthDataId);
                }
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the database connection and statement
            DatabaseConnection.close(connection, statement);
        }
    }
    /**
     * Retrieves health data based on its unique identifier.
     *
     * @param id The identifier of the health data.
     * @return The health data if found; otherwise, null.
     */
    public HealthData getHealthDataById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        HealthData healthData = null;
        try {
            // Establish a database connection
            connection = DatabaseConnection.getCon();
            // SQL query to select health data by ID
            String query = "SELECT * FROM health_data WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            // Process the result set
            if (resultSet.next()) {
                healthData = createHealthDataFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection, statement, and result set
            DatabaseConnection.close(connection, statement, resultSet);
        }
        return healthData;
    }
    /**
     * Retrieves a list of health data entries associated with a specific user.
     *
     * @param userId The identifier of the user.
     * @return The list of health data entries for the user.
     */
    public List<HealthData> getHealthDataByUserId(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<HealthData> healthDataList = new ArrayList<>();
        try {
            // Establish a database connection
            connection = DatabaseConnection.getCon();
            // SQL query to select health data by user ID
            String query = "SELECT * FROM health_data WHERE user_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            // Process the result set
            while (resultSet.next()) {
                HealthData healthData = createHealthDataFromResultSet(resultSet);
                healthDataList.add(healthData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection, statement, and result set
            DatabaseConnection.close(connection, statement, resultSet);
        }
        return healthDataList;
    }
    /**
     * Updates an existing health data entry in the database.
     *
     * @param healthData The health data to be updated.
     * @return True if the update is successful; false otherwise.
     */
    public boolean updateHealthData(HealthData healthData) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establish a database connection
            connection = DatabaseConnection.getCon();
            // SQL query to update health data
            String query = "UPDATE health_data SET weight=?, height=?, steps=?, heart_rate=?, date=? WHERE id=?";
            statement = connection.prepareStatement(query);
            // Set values for the parameters
            statement.setDouble(1, healthData.getWeight());
            statement.setDouble(2, healthData.getHeight());
            statement.setInt(3, healthData.getSteps());
            statement.setInt(4, healthData.getHeartRate());
            statement.setString(5, healthData.getDate());
            statement.setInt(6, healthData.getId());
            // Execute the update
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the database connection and statement
            DatabaseConnection.close(connection, statement);
        }
    }
    /**
     * Deletes a health data entry from the database.
     *
     * @param id The identifier of the health data to be deleted.
     * @return True if the deletion is successful; false otherwise.
     */
    public boolean deleteHealthData(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establish a database connection
            connection = DatabaseConnection.getCon();
            // SQL query to delete health data by ID
            String query = "DELETE FROM health_data WHERE id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            // Execute the deletion
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close the database connection and statement
            DatabaseConnection.close(connection, statement);
        }
    }
    /**
     * Creates a HealthData object from a ResultSet.
     *
     * @param resultSet The result set containing health data information.
     * @return The HealthData object.
     * @throws SQLException If a database access error occurs.
     */
    private HealthData createHealthDataFromResultSet(ResultSet resultSet) throws SQLException {
        HealthData healthData = new HealthData();
        healthData.setId(resultSet.getInt("id"));
        healthData.setUserId(resultSet.getInt("user_id"));
        healthData.setWeight(resultSet.getDouble("weight"));
        healthData.setHeight(resultSet.getDouble("height"));
        healthData.setSteps(resultSet.getInt("steps"));
        healthData.setHeartRate(resultSet.getInt("heart_rate"));
        healthData.setDate(resultSet.getString("date"));
        return healthData;
    }
}
