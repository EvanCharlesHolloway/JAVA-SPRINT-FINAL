// Doctor.java

// Represents a Doctor, extending the functionality of the User class.
public class Doctor extends User {
    // Stores the unique medical license number of the doctor.
    private String medicalLicenseNumber;
    // Stores the specific area of expertise or specialization of the doctor.
    private String specialization;
    // Constructor for creating a Doctor object with specified attributes.
    public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        super(id, firstName, lastName, email, password, isDoctor);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;}
    // Retrieves the medical license number of the doctor.
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }
    // Sets or updates the medical license number of the doctor.
    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }
    // Retrieves the specific area of expertise or specialization of the doctor.
    public String getSpecialization() {
        return specialization;
    }
    // Sets or updates the specialization of the doctor.
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
