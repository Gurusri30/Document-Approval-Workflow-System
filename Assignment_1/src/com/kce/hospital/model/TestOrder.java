package com.kce.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class TestOrder {
    private int id;
    private Patient patient;
    private List<TestOrderItem> items = new ArrayList<>();

    public TestOrder(int id, Patient patient) {
        this.id = id;
        this.patient = patient;
    }

    public void addOrderItem(TestOrderItem item) {
        items.add(item);
    }

    public int getId() { return id; }
    public Patient getPatient() { return patient; }
    public List<TestOrderItem> getItems() { return items; }
}
