<%--
  Created by IntelliJ IDEA.
  User: Pradeep.P
  Date: 22-05-2015
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>

<br><br>
<br><br>
<br><br>
<center>
<select id="jobsid">
    <option value="ResendVerificationTokenJob">ResendConformationMails</option>
    <option value="rememberthis">rememberthis</option>
    <option value="mercedes">Mercedes</option>
    <option value="audi">Audi</option>
</select>


<span>
    <input type="button" value="ExecuteJobs" id="execute-job">
</span>
<h2 id="result"></h2>
</center>

<br><br>
<br><br>
<br><br>
<center>
    <tiles:insertAttribute name="footer"/>
</center>
<script>
    $('#execute-job').off('click').on('click', function(e) {
        var jobname = $("#jobsid").val();
alert("this is eexecuting" +jobname );

       $.ajax ({
            type: 'POST' ,
            url: "/execute.html",
             data: {
               jobname:jobname
            },
           error: function() {$("#result").html("Execution Failed");},
           success: function(htmlData) {
               if(htmlData != undefined && htmlData != null){
                   $("#result").html("Execution success" +htmlData);
               }
           }
        });
      /*  var jobname = $("#course-drop-down-wrapper .sbHolder").find(".sbSelector").attr("selectedid");
        $.ajaxq("noload","queue",{
            type: 'POST' ,
            url: '/secure/runJob',
            data: {
                jobname:jobname
            },
            error: function() {$("#result").html("Execution Failed");},
            success: function(htmlData) {
                if(htmlData != undefined && htmlData != null){
                    $("#result").html("Execution success");
                }
            }
        });*/
    });

</script>
</body>
</html>
