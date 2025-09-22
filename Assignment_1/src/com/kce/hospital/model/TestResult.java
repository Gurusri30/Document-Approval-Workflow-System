package com.kce.hospital.model;

public class TestResult {
    private Sample sample;
    private String value;

    public TestResult(Sample sample, String value) {
        this.sample = sample;
        this.value = value;
    }

    public String getValue() { return value; }
}
