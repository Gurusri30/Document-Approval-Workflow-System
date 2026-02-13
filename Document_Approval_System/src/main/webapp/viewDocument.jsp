<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Document</title>
</head>
<body>

<h3>View Document</h3>

<form action="MainServlet1" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Title: <input type="text" name="title"><br><br>

    Submit Date: <input type="date" name="submitDate"><br><br>

    <input type="submit" value="Search">

</form>

</body>
</html>
