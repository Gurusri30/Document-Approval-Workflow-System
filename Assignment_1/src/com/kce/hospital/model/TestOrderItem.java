package com.kce.hospital.model;

public class TestOrderItem {
    private LabTest test;
    private Sample sample;

    public TestOrderItem(LabTest test) {
        this.test = test;
    }

    public LabTest getTest() { return test; }
    public Sample getSample() { return sample; }
    public void setSample(Sample sample) { this.sample = sample; }
}
