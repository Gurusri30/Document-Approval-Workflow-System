package com.kce.hospital.model;

public class Sample {
    private TestOrderItem orderItem;
    private TestResult result;

    public Sample(TestOrderItem item) {
        this.orderItem = item;
    }

    public TestOrderItem getOrderItem() { return orderItem; }
    public TestResult getResult() { return result; }
    public void setResult(TestResult result) { this.result = result; }
}
