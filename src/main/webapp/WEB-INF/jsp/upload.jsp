<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page session="false" %>

<form method="POST" action="uploadFile.html" enctype="multipart/form-data">
    File to upload: <input type="file" name="file">
    <input type="submit" value="Upload"> Press here to upload the file!
</form>

