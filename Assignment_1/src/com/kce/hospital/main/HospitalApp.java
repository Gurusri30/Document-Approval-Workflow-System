package com.kce.hospital.main;
import com.kce.hospital.model.*;
import com.kce.hospital.service.*;
import com.kce.hospital.exception.InvalidOrderException;

import java.util.*;
public class HospitalApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Patient> patients = new ArrayList<>();
        List<LabTest> tests = new ArrayList<>();
        List<TestOrder> orders = new ArrayList<>();
        OrderService orderService = new OrderService();
        int patientId = 1, testId = 1, orderId = 1;
        int choice;
        do {
            System.out.println("\n--- Hospital Lab System  ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Lab Test");
            System.out.println("3. Create Test Order");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String pname = sc.next();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    patients.add(new Patient(patientId++, pname, age));
                    System.out.println("✅ Patient added.");
                    break;
                case 2:
                    System.out.print("Enter test name: ");
                    String tname = sc.next();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    tests.add(new LabTest(testId++, tname, price));
                    System.out.println("✅ Lab Test added.");
                    break;
                case 3:
                    if (patients.isEmpty() || tests.isEmpty()) {
                        System.out.println("⚠️ Please add at least one patient and one test first!");
                        break;
                    }
                    System.out.println("Choose Patient ID:");
                    for (Patient p : patients) {
                        System.out.println(p.getId() + " - " + p.getName());
                    }
                    int pid = sc.nextInt();
                    Patient selectedPatient = null;
                    for (Patient p : patients) {
                        if (p.getId() == pid) {
                            selectedPatient = p;
                            break;
                        }
                    }
                    System.out.println("Choose Test ID:");
                    for (LabTest t : tests) {
                        System.out.println(t.getId() + " - " + t.getName());
                    }
                    int tid = sc.nextInt();
                    LabTest selectedTest = null;
                    for (LabTest t : tests) {
                        if (t.getId() == tid) {
                            selectedTest = t;
                            break;
                        }
                    }

                    if (selectedPatient != null && selectedTest != null) {
                        try {
                            List<LabTest> selectedTests = new ArrayList<>();
                            selectedTests.add(selectedTest);
                            TestOrder order = orderService.createOrder(orderId++, selectedPatient, selectedTests);
                            orders.add(order);
                            System.out.println("✅ Order created for " + selectedPatient.getName());
                        } catch (InvalidOrderException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("⚠️ Invalid patient or test.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
