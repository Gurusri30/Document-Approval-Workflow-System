package com.kce.hospital.service;

import com.kce.hospital.model.Invoice;
import com.kce.hospital.model.TestOrder;

public class InvoiceService {
    public Invoice generateInvoice(TestOrder order) {
        return new Invoice(order);
    }

    public void recordPayment(Invoice invoice, double amount) {
        invoice.makePayment(amount);
    }
}
