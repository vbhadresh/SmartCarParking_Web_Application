 <head><title>User</title></head>
 <%@include file="/WEB-INF/file/mst_emp_header.jsp" %>
 <div class="rightpanel">
        
        <ul class="breadcrumbs">
            <li><a href=""><i class="iconfa-home"></i></a> </li>
            <li><span class="separator"></span>User</li>
            <li><span class="separator"></span>Manage User</li>
        </ul>   
 
  <div class="pageheader">
             <div class="pageicon"><span class="iconfa-hdd"></span></div>
            <div class="pagetitle">
               <h5>User</h5>
                <h1>Manage User</h1>
            </div>
        </div><!--pageheader-->
        
		
		
		
		<div class="maincontent">
            <div class="maincontentinner">
            
      <a href="${pageContext.request.contextPath}/P_UserController" class="btn btn-info"><i class="iconfa-pencil"></i></a> Add User       
                <h4 class="widgettitle">User</h4>
                <table id="dyntable" class="table table-bordered responsive">
                    <colgroup>
                        <col class="con0" style="align: center; width: 4%" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                    </colgroup>
                    <thead>
                   
                        <tr>
                          	<th class="head0 nosort"><input type="checkbox" class="checkall" /></th>
                            <th class="head0">User Name </th>
                            <th class="head1">Name </th>
                      <th class="head0">Mobile Number </th>
                           <th class="head0"> Profile Picture </th>
                            <th class="head1">Action</th>
                            
                        </tr>
                       
                    </thead>
                    <tbody>
                    
                                           
 				<c:forEach items="${sessionScope.obj_all}" var="record">
 					
 				
                        <tr class="gradeX">
                          <td class="aligncenter"><span class="center">
                            <input type="checkbox" />
                          </span></td>
                            <td>${record.userName}</td>
                            <td>${record.personID.firstName } ${record.personID.lastName }</td>
                         <td>${record.personID.mobileNumber }</td>
                            
                            <td>   <img src="${pageContext.request.contextPath}/attachments/${record.attachmentID.path }" style="height:86px;width:86px;" alt="" />
                     </td>
                            <td class="center">
                             
                            <a href="<%=request.getContextPath() %>/P_UserController?mode=6&id=${record.userID}" class="btn btn-info"><i class="icon-edit"></i> Manage Vehicle</a>
                              
                            <a href="<%=request.getContextPath() %>/P_UserController?mode=2&id=${record.userID}" class="btn btn-info"><i class="icon-edit"></i></a>
                            <a href="<%=request.getContextPath() %>/P_UserController?mode=4&id=${record.userID}" class="btn btn-info"><i class="iconsweets-trashcan"></i></a>
                           
                           
                             
                            
                           
                            </td>
                            
                        </tr>
                  </c:forEach>     
                    </tbody>
                </table>
                
            
            
            </div><!--maincontentinner-->
        </div><!--maincontent-->
        
 
 <%@include file="/WEB-INF/file/mst_footer.jsp" %>
 
 <%@include file="/WEB-INF/file/mst_table.jsp" %> 