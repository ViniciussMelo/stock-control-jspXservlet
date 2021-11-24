<%@page import ="java.util.List"%>
<%@page import ="model.Type"%>
<style>
	#main {
		position: absolute;
		left: 15%;
		height: 95%;
		padding: 0px 20px 0 20px;
		width: 82%;
		background-color: #DCDCDC;
	}
	
	#products {
	  font-family: Arial, Helvetica, sans-serif;
	  width: 100%;
	  border-spacing: 0px;
	  border-collapse: separate;
	  border: 1px solid black;
	}
	
	#products td, #products th {
	  border: 1px solid #ddd;
	  padding: 8px;
	  width:0.1%;
	}
	
	#products tr:nth-child(even){background-color: #f2f2f2;}
	
	#products tr:hover {background-color: #ddd;}
	
	#products th {
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
	<table id="products" border="1">
		<thead>
	    	<tr>
	        	<th>Row</th>
	            <th>Id</th>
	            <th>Description</th>
	            <th>Actions</th>
	        </tr>
		</thead>
	    <tbody>
	 		<%
				int i = 1;
			    List<Type> types = (List) request.getAttribute("types");
			%>
			<%
				if (types != null) {
					for (Type type : types) {
			%>
			<tr>
				<td><%=i++%></td>
				<td><%=type.getId()%></td>
				<td><%=type.getDescription()%></td>
				<td >
					<a href="<%=request.getContextPath()%>/TypeController?action=editType&id=<%=type.getId()%>" class="buttonActionEdit">Edit</a>
					<a href="<%=request.getContextPath()%>/TypeController?action=deleteType&id=<%=type.getId()%>" class="buttonActionDelete">Remove</a>
				</td>
			</tr>
			<% 
					}
				}
			%>
	      </tbody>
	  </table>
	  <br>
	  <a href="<%=request.getContextPath()%>/TypeController?action=insertType" class="button" >Add Type</a>
</div>