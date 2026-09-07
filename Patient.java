package hospital;

/**
 * Represents a single patient record stored in the Patient BST.
 * Each patient also owns a Singly Linked List (VisitHistory) that
 * tracks their previous hospital visits.
 */
public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String contactNumber;
    private String medicalCondition;

    // Every patient has their own visit history (Singly Linked List)
    private VisitHistory visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public VisitHistory getVisitHistory() {
        return visitHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    @Override
    public String toString() {
        return "Patient ID   : " + patientId +
                "\nName         : " + name +
                "\nAge          : " + age +
                "\nContact No.  : " + contactNumber +
                "\nCondition    : " + medicalCondition;
    }

    /**
     * Short one-line representation, used when listing many patients
     * (e.g. in-order traversal output or queue display).
     */
    public String toShortString() {
        return "[ID:" + patientId + " | " + name + " | Age:" + age +
                " | " + contactNumber + " | " + medicalCondition + "]";
    }
}
