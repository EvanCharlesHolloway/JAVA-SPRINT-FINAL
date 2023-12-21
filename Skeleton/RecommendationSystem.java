import java.util.ArrayList;
import java.util.List;
public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;
    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();
        // Check if heart rate is too low
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Uh-oh, your heart rate is kinda low. Maybe spice things up with more exercise? Get that heart pumpin'!");
        } else if (heartRate > MAX_HEART_RATE) {
            recommendations.add("Whoa, slow down! Your heart rate is on the high side. Take a breather and maybe talk to a health wizard about it.");
        }
        // Check if steps are too low
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("Guess what? Your step count is a bit shy of the daily goal. Let's make walking a new BFF, shall we?");
        }
        // Feel free to add more advice based on different health data, like sleep, diet, or superhero-like abilities!
        return recommendations;
    }
}
