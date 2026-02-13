package com.wipro.document.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.document.bean.DocumentBean;
import com.wipro.document.util.DBUtil;

public class DocumentDAO {

    public String createRecord(DocumentBean bean) {

        String generatedId = generateDocumentID(bean.getTitle(), bean.getSubmitDate());

        String query = "INSERT INTO DOCUMENT_TB "
                + "(DOCUMENTID, TITLE, TYPE, SUBMITTER, SUBMITDATE, STATUS, REMARKS) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, generatedId);
            ps.setString(2, bean.getTitle());
            ps.setString(3, bean.getType());
            ps.setString(4, bean.getSubmitter());
            ps.setDate(5, new java.sql.Date(bean.getSubmitDate().getTime()));

            // Default status
            if (bean.getStatus() == null || bean.getStatus().isEmpty()) {
                ps.setString(6, "Pending");
            } else {
                ps.setString(6, bean.getStatus());
            }

            ps.setString(7, bean.getRemarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return generatedId;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "FAIL";
    }

    public DocumentBean fetchRecord(String title, Date submitDate) {

        String query = "SELECT * FROM DOCUMENT_TB WHERE TITLE=? AND SUBMITDATE=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setDate(2, new java.sql.Date(submitDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    DocumentBean bean = new DocumentBean();
                    bean.setDocumentId(rs.getString("DOCUMENTID"));
                    bean.setTitle(rs.getString("TITLE"));
                    bean.setType(rs.getString("TYPE"));
                    bean.setSubmitter(rs.getString("SUBMITTER"));
                    bean.setSubmitDate(rs.getDate("SUBMITDATE"));
                    bean.setStatus(rs.getString("STATUS"));
                    bean.setRemarks(rs.getString("REMARKS"));

                    return bean;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean recordExists(String title, Date submitDate) {
        return fetchRecord(title, submitDate) != null;
    }

    public List<DocumentBean> fetchAllRecords() {

        List<DocumentBean> list = new ArrayList<>();

        String query = "SELECT * FROM DOCUMENT_TB ORDER BY SUBMITDATE DESC";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                DocumentBean bean = new DocumentBean();
                bean.setDocumentId(rs.getString("DOCUMENTID"));
                bean.setTitle(rs.getString("TITLE"));
                bean.setType(rs.getString("TYPE"));
                bean.setSubmitter(rs.getString("SUBMITTER"));
                bean.setSubmitDate(rs.getDate("SUBMITDATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public String generateDocumentID(String title, Date submitDate) {

        String id = "";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT DOCUMENT_SEQ.NEXTVAL FROM DUAL")) {

            if (rs.next()) {
                int seq = rs.getInt(1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String datePart = sdf.format(submitDate);

                String titlePart = title.substring(0, Math.min(2, title.length())).toUpperCase();

                id = datePart + titlePart + seq;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
