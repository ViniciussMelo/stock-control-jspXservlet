<style>
	#main {
		position: absolute;
		left: 15%;
		height: 95%;
		padding: 0px 20px 0 20px;
		width: 82%;
		background-color: #DCDCDC;
	}
	
	/* Add padding to containers */
	.container {
	  padding: 16px 100px 16px 16px;
	}
	
	/* Full-width input fields */
	input[type=text], input[type=number], input[type=password] {
	  width: 100%;
	  padding: 15px;
	  margin: 5px 0 22px 0;
	  display: inline-block;
	  border: none;
	  background: #f1f1f1;
	}
	
	input[type=text]:focus, input[type=number]:focus, input[type=password]:focus {
	  background-color: white;
	  outline: none;
	}
	
	#inputReadonly {
	  width: 100%;
	  padding: 15px;
	  margin: 5px 0 22px 0;
	  display: inline-block;
	  border: none;
	  background: gray;
	}
	
	/* Overwrite default styles of hr */
	hr {
	  border: 1px solid #f1f1f1;
	  margin-bottom: 25px;
	}
	
	/* Set a style for the submit button */
	.registerbtn {
	  background-color: #04AA6D;
	  color: white;
	  padding: 16px 20px;
	  margin: 8px 0;
	  border: none;
	  cursor: pointer;
	  width: 100%;
	  opacity: 0.9;
	}
	
	.registerbtn:hover {
	  opacity: 1;
	}
	
	/* Add a blue text color to links */
	a {
	  color: dodgerblue;
	}
	
	/* Set a grey background color and center the text of the "sign in" section */
	.signin {
	  background-color: #f1f1f1;
	  text-align: center;
	}
	
	
	.containerCheck {
	  display: block;
	  position: relative;
	  padding-left: 35px;
	  margin-bottom: 12px;
	  cursor: pointer;
	  font-size: 22px;
	  -webkit-user-select: none;
	  -moz-user-select: none;
	  -ms-user-select: none;
	  user-select: none;
	}
</style>
<div id="main">
	<form action="<%=request.getContextPath()%>/ProductController?action=saveProduct" method="POST">
		<div class="container">
			<h3>Product</h3>
			<label><b>Barcode</b></label>
	   		<input type="text" placeholder="Enter barcode" name="barcode" id="inputReadonly" readonly="readonly" value="${prod.barcode}" required>
			<br>
			<label><b>Name</b></label>
	   		<input type="text" placeholder="Enter name" name="name" id="name" value="${prod.name}" required>
			<br>
			<label><b>Price</b></label>
	   		<input type="number" onkeypress="return onlyNumberKey(event)" placeholder="Enter price" name="price" id="price" value="${prod.price}" required>
			<br>
			<button type="submit" class="registerbtn">Register</button>
		</div>
	</form>
</div>
<script>
	function onlyNumberKey(evt) {
	    
	    // Only ASCII character in that range allowed
	    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
	        return false;
	    return true;
	}
</script>