<%@ page import="com.wipro.document.bean.DocumentBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Document</title>
</head>
<body>

<h3>Document Details</h3>

<%
DocumentBean bean = (DocumentBean) request.getAttribute("document");

if (bean == null) {
%>
    <h4>No matching records exists! Please try again!</h4>
<%
} else {
%>

Document ID: <%= bean.getDocumentId() %><br><br>
Title: <%= bean.getTitle() %><br><br>
Type: <%= bean.getType() %><br><br>
Submitter: <%= bean.getSubmitter() %><br><br>
Submit Date: <%= bean.getSubmitDate() %><br><br>
Status: <%= bean.getStatus() %><br><br>
Remarks: <%= bean.getRemarks() %><br><br>

<%
}
%>

<a href="menu.html">Back</a>

</body>
</html>
