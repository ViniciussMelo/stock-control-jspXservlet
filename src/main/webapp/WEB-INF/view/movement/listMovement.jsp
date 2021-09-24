<%@page import ="java.util.List"%>
<%@page import ="model.Movement"%>
<style>
	#main {
		position: absolute;
		left: 15%;
		height: 95%;
		padding: 0px 20px 0 20px;
		width: 82%;
		background-color: #DCDCDC;
	}
	
	#movements {
	  font-family: Arial, Helvetica, sans-serif;
	  width: 100%;
	  border-spacing: 0px;
	  border-collapse: separate;
	  border: 1px solid black;
	}
	
	#movements td, #movements th {
	  border: 1px solid #ddd;
	  padding: 8px;
	  width:0.1%;
	}
	
	#movements tr:nth-child(even){background-color: #f2f2f2;}
	
	#movements tr:hover {background-color: #ddd;}
	
	#movements th {
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
</style>
<div id="main">
	<table id="movements" border="1">
		<thead>
	    	<tr>
	        	<th>Row</th>
	            <th>Product name</th>
	            <th>Quantity</th>
	            <th>Type</th>
	            <th>Actions</th>
	        </tr>
		</thead>
	    <tbody>
	 		<%
				int i = 1;
			    List<Movement> movements = (List) request.getAttribute("movements");
			%>
			<%
				if (movements != null) {
					for (Movement mov : movements) {
			%>
			<tr>
				<td><%=i++%></td>
				<td><%=mov.getProductName()%></td>
				<td><%=mov.getQuantity()%></td>
				<td><%=mov.getType()%></td>
				<td >
					<a href="<%=request.getContextPath()%>/MovementController?action=editMovement&id=<%=mov.getId()%>" class="buttonActionEdit">Edit</a>
					<a href="<%=request.getContextPath()%>/MovementController?action=deleteMovement&id=<%=mov.getId()%>" class="buttonActionDelete">Remove</a>
				</td>
			</tr>
			<% 
					}
				}
			%>
	      </tbody>
	  </table>
	  <br>
	  <a href="<%=request.getContextPath()%>/MovementController?action=insertMovement" class="button" >Add Movement</a>
</div>