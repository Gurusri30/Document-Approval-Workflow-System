<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Document</title>
</head>
<body>

<h3>Submit Document</h3>

<form action="MainServlet1" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Title: <input type="text" name="title"><br><br>

    Type: <input type="text" name="type"><br><br>

    Submitter: <input type="text" name="submitter"><br><br>

    Submit Date: <input type="date" name="submitDate"><br><br>

    Remarks: <input type="text" name="remarks"><br><br>

    <input type="submit" value="Submit">

</form>

</body>
</html>
