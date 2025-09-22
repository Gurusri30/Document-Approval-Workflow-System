package com.kce.hospital.service;

import com.kce.hospital.model.*;
import com.kce.hospital.exception.InvalidOrderException;
import java.util.List;

public class OrderService {
    public TestOrder createOrder(int orderId, Patient patient, List<LabTest> tests) throws InvalidOrderException {
        if (patient == null || tests == null || tests.isEmpty()) {
            throw new InvalidOrderException("Patient or tests cannot be empty.");
        }
        TestOrder order = new TestOrder(orderId, patient);
        for (LabTest test : tests) {
            order.addOrderItem(new TestOrderItem(test));
        }
        return order;
    }

    public Sample collectSample(TestOrderItem item) {
        Sample sample = new Sample(item);
        item.setSample(sample);
        return sample;
    }

    public TestResult recordResult(Sample sample, String value) {
        TestResult result = new TestResult(sample, value);
        sample.setResult(result);
        return result;
    }
}
