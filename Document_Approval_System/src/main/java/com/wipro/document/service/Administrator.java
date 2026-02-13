package com.wipro.document.service;

import java.util.Date;
import java.util.List;

import com.wipro.document.bean.DocumentBean;
import com.wipro.document.dao.DocumentDAO;
import com.wipro.document.util.InvalidInputException;

public class Administrator {

    DocumentDAO dao = new DocumentDAO();

    public String addRecord(DocumentBean bean) {

        try {

            if (bean == null ||
                bean.getTitle() == null ||
                bean.getType() == null ||
                bean.getSubmitter() == null ||
                bean.getSubmitDate() == null)
                throw new InvalidInputException();

            if (bean.getTitle().length() < 2)
                return "INVALID TITLE";

            if (bean.getType().length() < 2)
                return "INVALID TYPE";

            if (bean.getSubmitter().length() < 2)
                return "INVALID SUBMITTER NAME";

            if (dao.recordExists(bean.getTitle(), bean.getSubmitDate()))
                return "ALREADY EXISTS";

            String id = dao.generateDocumentID(bean.getTitle(), bean.getSubmitDate());
            bean.setDocumentId(id);
            bean.setStatus("Pending");

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public DocumentBean viewRecord(String title, Date submitDate) {
        return dao.fetchRecord(title, submitDate);
    }

    public List<DocumentBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
