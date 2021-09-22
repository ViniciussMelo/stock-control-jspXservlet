<%@page import ="java.util.List"%>
<%@page import ="model.Product"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Show All Users</title>
	</head>
<body>
	<div>
		<table border=1>
	        <thead>
	            <tr>
	                <th>Row</th>
	                <th>Barcode</th>
	                <th>Name</th>
	                <th>Price</th>
	                <th>Active</th>
	            </tr>
	        </thead>
	        <tbody>
	   		<%
				int i = 1;
			    List<Product> products = (List) request.getAttribute("products");
			%>
			<%
				if (products != null) {
				for (Product prod : products) {
			%>
				<tr>
					<td><%=i++%></td>
					<td><%=prod.getBarcode()%></td>
		            <td><%=prod.getName()%></td>
		            <td><%=prod.getPrice()%></td>
		            <td><%=prod.isActive()%></td>
	            </tr>
			<% 
				}
				}
			%>
	        </tbody>
	    </table>
	</div>
    <p><a href="ProductController?action=insertProduct">Add Product</a></p>
</body>
</html>