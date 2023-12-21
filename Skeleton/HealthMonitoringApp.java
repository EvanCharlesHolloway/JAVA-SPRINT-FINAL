import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao(); // Assuming UserDao already exists
    private static HealthDataDao healthDataDao = new HealthDataDao();
    private static MedicineReminderManager reminderManager = new MedicineReminderManager();

    public static void main(String[] args) {
        // Initialize database connection
        try (Connection databaseConnection = DatabaseConnection.getCon()) {
            // Test registering a new user
            registerNewUser();
            // Test logging in the user
            testLoginUser();
            // Test adding health data
            addHealthData();
            // Test generating recommendations
            generateRecommendations();
            // Test adding a medicine reminder
            addMedicineReminder();
            // Test getting reminders for a specific user
            getRemindersForUser();
            // Test getting due reminders for a specific user
            getDueRemindersForUser();
            // Test doctor portal
            testDoctorPortal();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerNewUser() {
        User user1 = new User(101, "Jimmothy", "Bimmothy", "Jimbim@gmail.com", "Mothy", false);
        userDao.createUser(user1);
    }

    private static void testLoginUser() {
        String userEmail = "tscholefield@over-blog.com"; // Replace with a valid user email
        String userPassword = "zE9(GbVHAICJGj8"; // Replace with a valid user password from the users table.

        boolean loginSuccess = loginUser(userEmail, userPassword);
        if (loginSuccess) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Incorrect email or password. Please try again.");
        }
    }

    private static boolean loginUser(String email, String password) {
        User user = userDao.getUserByEmail(email);

        return user != null && user.getPassword().equals(password);
    }

    private static void addHealthData() {
        HealthData healthData1 = new HealthData(5, 70.5, 175.0, 8000, 75, "2023-01-15");
        boolean success = healthDataDao.createHealthData(healthData1);

        if (success) {
            int userId = 5; // Replace with a valid user ID
            List<HealthData> healthDataList = healthDataDao.getHealthDataByUserId(userId);

            System.out.println("Health data for the user:");
            for (HealthData healthData : healthDataList) {
                System.out.println(healthData);
            }
        } else {
            System.out.println("Failed to add health data."); } }

    private static void generateRecommendations() {
        // Implement logic to generate health recommendations
        // Replace with actual code
        RecommendationSystem recommendationSystem = new RecommendationSystem();
        HealthData healthData = new HealthData(5, 70.5, 175.0, 8000, 75, "2023-01-15");





        List<String> recommendations = recommendationSystem.generateRecommendations(healthData);
        System.out.println("Health Recommendations:");
        for (String recommendation : recommendations) {
            System.out.println(recommendation);
        }}
    private static void addMedicineReminder() {
        // Implement logic to add a medicine reminder
        // Replace with actual code
        MedicineReminder reminder = new MedicineReminder();
        reminder.setUserId(5);
        reminder.setMedicineName("Aspirin");
        reminder.setDosage("1 pill");
        reminder.setSchedule("Once a day");
        reminder.setStartDate("2023-01-15");
        reminder.setEndDate("2023-02-15");

        reminderManager.addReminder(reminder);
    }

    private static void getRemindersForUser() {
        // Implement logic to get reminders for a specific user
        // Replace with actual code
        int userId = 5; // Replace with a valid user ID
        List<MedicineReminder> userReminders = reminderManager.getRemindersForUser(userId);

        System.out.println("Reminders for the user:");
        for (MedicineReminder reminder : userReminders) {
            System.out.println(reminder);
        }
    }

    private static void getDueRemindersForUser() {
        // Implement logic to get due reminders for a specific user
        // Replace with actual code
        int userId = 5; // Replace with a valid user ID
        List<MedicineReminder> dueReminders = reminderManager.getDueReminders(userId);

        System.out.println("Due reminders for the user:");
        for (MedicineReminder reminder : dueReminders) {
            System.out.println(reminder);
        }
    }

    private static void testDoctorPortal() {
        int doctorId = 53; // use a doctor id that is correct from the database
        DoctorPortalDao doctorDao = new DoctorPortalDao();
        User doctor = doctorDao.getDoctorById(doctorId);
        if (doctor != null) {
            System.out.println("Doctor details:");
            System.out.println(doctor);
            // Fetch patients associated with the doctor
            List<User> patients = doctorDao.getPatientsByDoctorId(doctorId);
            System.out.println("Patients associated with the doctor:");
            for (User patient : patients) {
                System.out.println(patient);
            }
            // Assume you have a PatientDao
            UserDao patientDao = new UserDao();
            // Fetch health data for a specific patient
            int patientId = 1; // Replace with a valid patient ID
            User patient = patientDao.getUserById(patientId);
            if (patient != null) {
                System.out.println("Health data for the patient:");
                // Implement logic to fetch health data for the patient
            } else {
                System.out.println("Patient not found.");
            }
        } else {
            System.out.println("Doctor not found.");
        }
    }
}

