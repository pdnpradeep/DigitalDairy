<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

Hello pradeep it is starting site
<br><br>
<label for="search" class="Searchlabel">Search Here</label>
<div id="onetwo">
<input  id="searchinput"  name="searchinput" class="searchinputclass" placeholder="Enter Text zHere" required autofocus>
</div>
<span class="btn btn-info btn-lg" id="searchbutton">Search</span>
<table  border='1'>
	<tbody id="resultaftersearch"></tbody>
</table>
TO UPLAD A FILE: <a href="/upload.html">upload</a>
<script>



$( document ).ready(function() {
	var data = $("#searchinput").val()
	$("#searchinput").autocomplete({
		source: "/SolrQuery.html?" + data,
		dataType: "JSON",
		appendTo: "#onetwo",

	});
	$("#searchbutton").click(function () {
		$.ajax({
			type: 'POST',
			url: "/searchbuttonclick.html",
			data: {
				term: $("#searchinput").val()
			},
			error: function () {
				$("#result").html("Search Failed");
			},
			success: function (htmlData) {
				if (htmlData != undefined && htmlData != null) {
					var reslt = JSON.parse(htmlData);
					//alert("success" +reslt.count );
					alert(reslt.length);
					for(i=0;i<reslt.length-1;i++){
						$('#resultaftersearch').append("<tr>");
						$('#resultaftersearch').append("<td>"+reslt[i].id+"</td>");
						$('#resultaftersearch').append("<td>"+reslt[i].title+"</td>");
						$('#resultaftersearch').append("<td>"+reslt[i].author+"</td>");
						$('#resultaftersearch').append("</tr>");

					}

				}
			}
		});
	});
});

</script>
