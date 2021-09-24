<style>
	#nav {
		position: absolute;
		float: left;
		width: 15%;
		height: 95%;
		background: #F8F8FF;
		overflow: hidden;
	}

	.vmenu {
		width: 100px;
	}
	
	.vmenu a {
		color: black;
		display: block;
		padding: 10px ;
		width: 200px;
	  	text-decoration: none;
	  	font-size: 20px;
	}
	
	.vmenu a:hover {
		background-color: #ddd;
  		color: black;
		width: 100%;
	}
	
	.vmenu a.active {
		background-color: #04AA6D;
		color: white;
		width: 100%;
	}
</style>

<div id="nav" class="vmenu">
	<a class="menu_link${HOME}" href="<%=request.getContextPath()%>/HomeController">Home</a>
	<a class="menu_link${PROD}" href="<%=request.getContextPath()%>/ProductController">Products</a>
	<a class="menu_link${MOV}" href="<%=request.getContextPath()%>/MovementController">Movements</a>
</div>

<script>
	var header = document.getElementById("nav");
	var links = header.getElementsByClassName("menu_link");
	var currentLocation = location.href;
	
	for (var i = 0; i < links.length; i++) {
		if (links[i].heref == currentLocation ||
		   	('http://localhost:8080/stock-control-jspXservlet/' == currentLocation &&
		   	  links[i] == 'http://localhost:8080/stock-control-jspXservlet/HomeController')
		   ) links[i].className += " active";		
		
		
		links[i].addEventListener("click", function() {
			var current = document.getElementsByClassName("active");
			current[0].className = current[0].className.replace(" active", "")
			
			this.className.replace(" active", "")
			this.className += " active";
		});
	}
</script>