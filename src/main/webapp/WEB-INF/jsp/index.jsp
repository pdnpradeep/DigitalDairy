<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

Hello pradeep it is starting site
<br><br>
<label for="search" class="Searchlabel">Search Here</label>
<div id="onetwo">
<input  id="searchinput"  name="searchinput" class="searchinputclass" placeholder="Enter Text zHere" required autofocus>
</div>
<script>



$( document ).ready(function() {
		var data =$("#searchinput").val()
   		 $( "#searchinput" ).autocomplete({
       			source:"/SolrQuery.html?"+data,
       			dataType: "JSON",
       			appendTo: "#onetwo",

       		});
});

</script>
