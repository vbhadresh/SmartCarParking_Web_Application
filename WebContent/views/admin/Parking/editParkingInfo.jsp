  <head><title>Parking</title></head>

 <%@include file="/WEB-INF/file/mst_admin_header.jsp" %>
 <div class="rightpanel">
        
        <ul class="breadcrumbs">
            <li><a href=""><i class="iconfa-home"></i></a> </li>
            <li><span class="separator"></span>Parking</li>
            <li><span class="separator"></span>Add Parking</li>
        </ul>   
  <div class="pageheader">
            
            <div class="pageicon"><span class="iconfa-hdd"></span></div>
            <div class="pagetitle">
               <h5>Parking</h5>
                <h1>Edit Parking Info </h1>
            </div>
        </div><!--pageheader-->
  	
		<div class="maincontent">
            <div class="maincontentinner">
            
            <div class="widget">
            <h4 class="widgettitle">Add Parking:</h4>
            <div class="widgetcontent">
            	 <c:forEach items="${sessionScope.obj}" var="record">
                <form class="stdform stdform2" action="<%=request.getContextPath() %>/ParkingController" method="post">
                    	<input type="hidden" name="mode" value="3">
                    	<input type="hidden" name="parkingID" value="${record.parkingID}">
                   	
                <p>
                            <label>Parking Details</label>
               </p>
                               		
					
					<p>
                            <label>Parking Name</label>
                            <span class="field">		
                            <input type="text" name="parkingName" value="${record.parkingName}" class="input-large" placeholder="Parking Name" />
							
							</span>
					</p>

						<p>
							<label>addressLine1 </label> <span class="field"><textarea
									id="autoResizeTA" name="addressLine1" cols="80" rows="5"
									class="span5" style="resize: vertical; height: 100px;"
									placeholder="Description">${record.addressLine1 }</textarea></span>
						</p>

						<p>
							<label>addressLine2 </label> <span class="field"><textarea
									id="autoResizeTA" name="addressLine2" cols="80" rows="5"
									class="span5" style="resize: vertical; height: 100px;"
									placeholder="Description">${record.addressLine2 }</textarea></span>
						</p>
						
						<p>
                            <label>Landmark</label>
                            <span class="field">		
                            <input type="text" name="landmark" class="input-large" value="${record.landmark}" placeholder="landmark" />
							
							</span>
					</p>
						<p>
                            <label>Pincode</label>
                            <span class="field">		
                            <input type="text" name="pincode" class="input-large" value="${record.pincode}" placeholder="eg 1234" />
							
							</span>
					</p>
					
					<p>
                            <label>Latitude</label>
                            <span class="field">		
                            <input type="text" name="mapLat" class="input-large" value="${record.mapLat}" placeholder="eg 1234" />
							
							</span>
					</p>
					
					<p>
                            <label>Longitude</label>
                            <span class="field">		
                            <input type="text" name="mapLon" value="${record.mapLon}" class="input-large" placeholder="eg 1234" />
							
							</span>
					</p>
			 
					 <p>
                            <label>Commission</label>
                            <span class="field">		
                            <input type="text" name="commission" value="${record.commission}" class="input-large" placeholder="Commission" />
							
							</span>
					 </p>
						    	
	
					<p class="stdformbutton">
                                <button class="btn btn-primary">Submit </button>
                                <button type="reset" class="btn">Reset </button>
                            </p>
                    	
                   </form>
                   </c:forEach>
              	</div>
          	</div>
                
            </div><!--maincontentinner-->
        </div><!--maincontent-->
        
 <%@include file="/WEB-INF/file/mst_footer.jsp" %>
 
        