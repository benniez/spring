<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript">
var jq=jQuery.noConflict();
</script>

<title>Spring - jQuery</title>

</head>
<body>

Demo
<div>
Add 2 numbers :
	<input id="inputNumber1" name="inputNumber1" type="text" size="5"/>
	+
	<input id="inputNumber2" name="inputNumber2" type="text" size="5"/>
	<input type="submit" value="add" onclick="add()"/>
	Sum:<span id="sum">?</span>
</div>

<script type="text/javascript">
function add(){
	jq(function (){
		jq.post("http://localhost:8080/spring/ajax/add",
			{inputNumber1: jq("#inputNumber1").val(),
			 inputNumber2: jq("#inputNumber2").val()},
			 function(data){
				 debugger;
				jq("#sum").replaceWith('<span id="sum">'+data+'</span>'); 
			 });		
	});
}

</script>



</body>
</html>