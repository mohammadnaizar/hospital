package hospital;

import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * CIT300 - Data Structures and Algorithms - Individual Mid Assignment
 *
 * Demonstrates four core data structures working together:
 *   1. Patient Records        -> Binary Search Tree (PatientBST)
 *   2. Emergency Patient Queue -> Queue (EmergencyQueue)
 *   3. Treatment History      -> Stack (TreatmentStack)
 *   4. Patient Visit History  -> Singly Linked List (VisitHistory, inside Patient)
 *
 * This class provides a console (menu-driven) interface so all
 * operations can be demonstrated interactively.
 */
public class HospitalManagementSystem {

    private static final PatientBST patientRecords = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentHistory = new TreatmentStack();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=================================================");

        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> patientRecordsMenu();
                case 2 -> emergencyQueueMenu();
                case 3 -> treatmentHistoryMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // =================================================================
    // MAIN MENU
    // =================================================================

    private static void printMainMenu() {
        System.out.println("\n------------------- MAIN MENU -------------------");
        System.out.println("1. Patient Records          (Binary Search Tree)");
        System.out.println("2. Emergency Patient Queue   (Queue)");
        System.out.println("3. Treatment History         (Stack)");
        System.out.println("4. Patient Visit History     (Singly Linked List)");
        System.out.println("0. Exit");
        System.out.println("--------------------------------------------------");
    }

    // =================================================================
    // 1. PATIENT RECORDS - BST
    // =================================================================

    private static void patientRecordsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Records (BST) ---");
            System.out.println("1. Insert new patient");
            System.out.println("2. Search patient by ID");
            System.out.println("3. Delete patient by ID");
            System.out.println("4. Display all patients (in-order traversal)");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> insertPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> {
                    System.out.println("\nPatients in ascending order of Patient ID:");
                    patientRecords.inorderTraversal();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void insertPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientRecords.search(id) != null) {
            System.out.println("A patient with ID " + id + " already exists.");
            return;
        }
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        System.out.println("Patient added successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("\nPatient Found:\n" + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientRecords.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "No patient found with ID " + id);
    }

    // =================================================================
    // 2. EMERGENCY PATIENT QUEUE - QUEUE
    // =================================================================

    private static void emergencyQueueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Emergency Patient Queue (Queue) ---");
            System.out.println("1. Enqueue patient (add to waiting queue)");
            System.out.println("2. Dequeue patient (send next patient for treatment)");
            System.out.println("3. Display all patients waiting");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> enqueuePatient();
                case 2 -> dequeuePatient();
                case 3 -> {
                    System.out.println("\nPatients currently waiting:");
                    emergencyQueue.displayQueue();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void enqueuePatient() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Please register the patient first (Patient Records menu).");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println("Patient " + patient.getName() + " added to the emergency queue.");
    }

    private static void dequeuePatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient != null) {
            System.out.println("Now treating: " + patient.toShortString());
        }
    }

    // =================================================================
    // 3. TREATMENT HISTORY - STACK
    // =================================================================

    private static void treatmentHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Treatment History (Stack) ---");
            System.out.println("1. Push completed treatment record");
            System.out.println("2. Pop most recent treatment record");
            System.out.println("3. Display treatment records");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> pushTreatment();
                case 2 -> popTreatment();
                case 3 -> {
                    System.out.println("\nTreatment records (most recent first):");
                    treatmentHistory.displayHistory();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void pushTreatment() {
        int id = readInt("Enter Patient ID whose treatment is complete: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        System.out.print("Enter treatment details: ");
        String details = scanner.nextLine();
        System.out.print("Enter completion date (e.g. 2026-09-04): ");
        String date = scanner.nextLine();

        TreatmentRecord record = new TreatmentRecord(id, patient.getName(), details, date);
        treatmentHistory.push(record);
        System.out.println("Treatment record saved.");
    }

    private static void popTreatment() {
        TreatmentRecord record = treatmentHistory.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record: " + record);
        }
    }

    // =================================================================
    // 4. PATIENT VISIT HISTORY - SINGLY LINKED LIST
    // =================================================================

    private static void visitHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Visit History (Singly Linked List) ---");
            System.out.println("1. Add a visit to a patient's history");
            System.out.println("2. Remove a visit from a patient's history");
            System.out.println("3. Search for a visit");
            System.out.println("4. Display a patient's full visit history");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addVisit();
                case 2 -> removeVisit();
                case 3 -> searchVisit();
                case 4 -> displayVisitHistory();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static Patient getPatientForVisitOps() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        }
        return patient;
    }

    private static void addVisit() {
        Patient patient = getPatientForVisitOps();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        System.out.print("Enter Visit Date (e.g. 2026-09-04): ");
        String date = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        patient.getVisitHistory().addVisit(visitId, date, doctor, diagnosis, treatment);
        System.out.println("Visit added to patient's history.");
    }

    private static void removeVisit() {
        Patient patient = getPatientForVisitOps();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "No visit found with ID " + visitId);
    }

    private static void searchVisit() {
        Patient patient = getPatientForVisitOps();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        String result = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(result != null ? "Visit found -> " + result : "No visit found with ID " + visitId);
    }

    private static void displayVisitHistory() {
        Patient patient = getPatientForVisitOps();
        if (patient == null) return;

        System.out.println("\nVisit history for " + patient.getName() + " (ID:" + patient.getPatientId() + "):");
        patient.getVisitHistory().displayVisits();
    }

    // =================================================================
    // HELPERS
    // =================================================================

    /** Reads an integer safely, re-prompting on invalid input. */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
