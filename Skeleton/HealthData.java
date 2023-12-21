/**
 * Represents health-related data for a user.
 */
public class HealthData {
    private int id;             // Unique identifier for health data
    private int userId;         // ID of the user
    private double weight;      // User's weight
    private double height;      // User's height
    private int steps;           // Number of steps taken
    private int heartRate;       // User's heart rate
    private String date;         // Date of the health data
    // Constructors
    /**
     * Default constructor for HealthData.
     */
    public HealthData() {
    }
    /**
     * Creates health data with user information.
     *
     * @param userId    User's ID.
     * @param weight    User's weight.
     * @param height    User's height.
     * @param steps     Number of steps taken.
     * @param heartRate User's heart rate.
     * @param date      Date of the health data.
     */
    public HealthData(int userId, double weight, double height, int steps, int heartRate, String date) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
    }
    // Getters and Setters
    /**
     * Gets the health data ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the health data ID.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets the user ID.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Gets the user's weight.
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Sets the user's weight.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Gets the user's height.
     */
    public double getHeight() {
        return height;
    }
    /**
     * Sets the user's height.
     */
    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * Gets the number of steps taken.
     */
    public int getSteps() {
        return steps;
    }
    /**
     * Sets the number of steps taken.
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }
    /**
     * Gets the user's heart rate.
     */
    public int getHeartRate() {
        return heartRate;
    }
    /**
     * Sets the user's heart rate.
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
    /**
     * Gets the date of the health data.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the health data.
     */
    public void setDate(String date) {
        this.date = date;
    }
    // toString method
    /**
     * Returns a string representation of the health data.
     */
    @Override
    public String toString() {
        return "HealthData{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", height=" + height +
                ", steps=" + steps +
                ", heartRate=" + heartRate +
                ", date='" + date + '\'' +
                '}';
    }
}
