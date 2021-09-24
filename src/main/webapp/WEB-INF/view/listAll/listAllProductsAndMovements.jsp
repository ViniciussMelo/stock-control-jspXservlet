<%@page import ="java.util.List"%>
<%@page import ="model.Movement"%>
<%@page import ="model.Product"%>
<style>
	#main {
		position: absolute;
		left: 15%;
		height: 95%;
		padding: 0px 20px 0 20px;
		width: 82%;
		background-color: #DCDCDC;
	}
	
	table {
	  font-family: Arial, Helvetica, sans-serif;
	  width: 100%;
	  border-spacing: 0px;
	  border-collapse: separate;
	  border: 1px solid black;
	}
	
	td, th {
	  border: 1px solid #ddd;
	  padding: 8px;
	  width:0.1%;
	}
	
	tr:nth-child(even){background-color: #f2f2f2;}
	
	tbody tr:hover {background-color: #ddd;}
	
	th {
	  padding-top: 12px;
	  padding-bottom: 12px;
	  text-align: left;
	  background-color: #04AA6D;
	  color: white;
	}
	
	.button {
	  background-color: #4CAF50; /* Green */
	  border: none;
	  color: white;
	  padding: 15px 32px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 16px;
	}
	
	.buttonActionEdit {
	  background-color: #4CAF50; /* Green */
	  border: none;
	  color: white;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 12px;
	  padding: 10px 20px;
	}
	
	.buttonActionDelete {
	  background-color: red;
	  border: none;
	  color: white;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 12px;
	  padding: 10px 15px;
	}
	
	tfoot {
	  background-color: #04AA6D;
	}
	
	tfoot:hover {
		background-color: #04AA6D
	}
</style>
<div id="main">
	<h3>Products</h3>
	<table id="products" border="1">
		<thead>
	    	<tr>
	        	<th>Row</th>
	            <th>Barcode</th>
	            <th>Name</th>
	            <th>Price</th>
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
			</tr>
			<% 
					}
				}
			%>
	      </tbody>
	      <tfoot>
	      	<tr>
	      		<th scope="rowgroup" colspan="3">Records</th>
            	<td><%=products.size()%></td>
	      	</tr>
	      </tfoot>
	  </table>
	<br>
	<h3>Movements</h3>
	<table id="movements" border="1">
		<thead>
	    	<tr>
	        	<th>Row</th>
	            <th>Product name</th>
	            <th>Quantity</th>
	            <th>Type</th>
	        </tr>
		</thead>
	    <tbody>
	 		<%
				int j = 1;
			    List<Movement> movements = (List) request.getAttribute("movements");
			%>
			<%
				if (movements != null) {
					for (Movement mov : movements) {
			%>
			<tr>
				<td><%=j++%></td>
				<td><%=mov.getProductName()%></td>
				<td><%=mov.getQuantity()%></td>
				<td><%=mov.getType()%></td>
			</tr>
			<% 
					}
				}
			%>
	      </tbody>
	      <tfoot>
	      	<tr>
	      		<th scope="rowgroup" colspan="3">Records</th>
            	<td><%=movements.size()%></td>
	      	</tr>
	      </tfoot>
	  </table>
</div>