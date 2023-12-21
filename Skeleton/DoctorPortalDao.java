import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The DoctorPortalDao class handles database operations related to the doctor portal.
 */
public class DoctorPortalDao {
    private UserDao userDao; // User DAO for handling user-related operations
    /**
     * Constructor to initialize the UserDao.
     */
    public DoctorPortalDao() {
        userDao = new UserDao(); // Create a new instance of UserDao
    }
    /**
     * Retrieves a doctor by their ID from the database.
     *
     * @param doctorId The ID of the doctor to retrieve.
     * @return A User object representing the doctor.
     */
    public User getDoctorById(int doctorId) {
        Connection connection = null; // Database connection object
        PreparedStatement statement = null; // Prepared statement for SQL query
        ResultSet resultSet = null; // Result set for query results
        User doctor = null; // User object to store the doctor
        try {
            connection = DatabaseConnection.getCon(); // Establish a database connection
            String query = "SELECT * FROM users WHERE id = ? AND is_doctor = ?"; // SQL query
            statement = connection.prepareStatement(query); // Create a prepared statement
            statement.setInt(1, doctorId); // Set the first parameter (doctorId)
            statement.setBoolean(2, true);  // Set the second parameter (is_doctor)
            resultSet = statement.executeQuery(); // Execute the query and get the result set
            // If a result is found, create a doctor User object
            if (resultSet.next()) {
                doctor = userDao.createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for any SQL exception
        } finally {
            DatabaseConnection.close(connection, statement, resultSet); // Close database resources
        }
        return doctor;
    }
    /**
     * Retrieves a list of patients associated with a doctor from the database.
     *
     * @param doctorId The ID of the doctor.
     * @return A list of User objects representing patients.
     */
    public List<User> getPatientsByDoctorId(int doctorId) {
        Connection connection = null; // Database connection object
        PreparedStatement statement = null; // Prepared statement for SQL query
        ResultSet resultSet = null; // Result set for query results
        List<User> patients = new ArrayList<>(); // List to store patient User objects
        try {
            connection = DatabaseConnection.getCon(); // Establish a database connection
            String query = "SELECT * FROM users WHERE id IN (SELECT patient_id FROM doctor_patient WHERE doctor_id = ?)"; // SQL query
            statement = connection.prepareStatement(query); // Create a prepared statement
            statement.setInt(1, doctorId); // Set the parameter (doctorId)
            resultSet = statement.executeQuery(); // Execute the query and get the result set
            // Create User objects for each patient and add to the list
            while (resultSet.next()) {
                User patient = userDao.createUserFromResultSet(resultSet);
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for any SQL exception
        } finally {
            DatabaseConnection.close(connection, statement, resultSet); // Close database resources
        }
        return patients;
    }
    /**
     * Adds a patient to the list of patients for a specific doctor in the database.
     *
     * @param doctorId  The ID of the doctor.
     * @param patientId The ID of the patient to add.
     */
    public void addPatientToDoctor(int doctorId, int patientId) {
        Connection connection = null; // Database connection object
        PreparedStatement statement = null; // Prepared statement for SQL query
        try {
            connection = DatabaseConnection.getCon(); // Establish a database connection
            String query = "INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?)"; // SQL query
            statement = connection.prepareStatement(query); // Create a prepared statement
            statement.setInt(1, doctorId); // Set the first parameter (doctorId)
            statement.setInt(2, patientId); // Set the second parameter (patientId)
            statement.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for any SQL exception
        } finally {
            DatabaseConnection.close(connection, statement); // Close database resources
        }
    }
    /**
     * Placeholder method; to be implemented.
     *
     * @param doctorId The ID of the doctor.
     * @return A list of User objects representing users associated with the doctor.
     */
    public List<User> getUsersForDoctor(int doctorId) {
        return null; // Placeholder implementation
    }
}
