package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.AttachmentVO;
import vo.ParkingEmployeeVO;
import vo.ParkingVO;
import vo.PersonVO;
import vo.UserVO;
import dao.AttachmentDAO;
import dao.ParkingDAO;
import dao.ParkingEmployeeDAO;
import dao.PersonDAO;
import dao.UserDAO;



/**
 * Servlet implementation class Circular
 */
@SuppressWarnings("rawtypes")
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// mode=0 Add New
		//mode=1 Insert
		//mode=2 Edit
		//mode=3 update
		//mode=4 Delete
		//mode=5 Show 		
		
		//Mode Type defined to String to control over illegal parameter passing through URL
		String mode="0";
		 
		//Getting the mode variable, if not than default to 0(zero)  
		if(request.getParameter("mode")!=null)
		   mode = request.getParameter("mode");
		else
			mode="0";
		
		
		if(mode.equals("2")){
			edit(request, response);
		}
		else if(mode.equals("4")) {
			delete(request, response);
			
		}else if(mode.equals("5")){
			show(request, response);
		}else if(mode.equals("6")){
			selectParking(request, response);
		}else if(mode.equals("7")){
			showAdmin(request, response);
		}else if(mode.equals("8")){
			showEmployee(request, response);
		}else{
			//default Add
			response.sendRedirect(request.getContextPath()+"/views/admin/User/addUser.jsp");
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// mode=0 Add New
		//mode=1 Insert
		//mode=2 Edit
		//mode=3 update
		//mode=4 Delete
		//mode=5 Show 
		//Mode Type defined to String to control over illegal parameter passing through URL
		 String mode="0";
		
		//Getting the mode variable, if not than default to 0(zero) 
		if(request.getParameter("mode")!=null)
			   mode = request.getParameter("mode");
			else
				doGet(request, response);
			
			
			if(mode.equals("1")){
				       insert(request, response);
			}else if(mode.equals("7")){
				allotParking(request, response);
			}else if(mode.equals("3")){
						update(request, response);
			}else if(mode.equals("3a")){
					updateCurrentUser(request, response);
			}else{
						//default Add
						response.sendRedirect(request.getContextPath()+"/views/admin/User/addUser.jsp");
						}//default
					
			
			
			
			
		
		
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
		
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		int userType = Integer.parseInt(request.getParameter("userType"));
		
		PersonVO personVO = new PersonVO();
		personVO.setEmail(email);
		personVO.setFirstName(firstName);
		personVO.setLastName(lastName);
		personVO.setMobileNumber(mobileNumber);
		
		PersonDAO dao = new PersonDAO();
		dao.insert(personVO);
		
		UserVO VO_obj = new UserVO();
		VO_obj.setPassword(password);
		VO_obj.setPersonID(personVO);
		VO_obj.setUserName(userName);
		VO_obj.setUserType(userType);
		 AttachmentVO attachmentVO = new AttachmentVO();

			//getting file from Session
			
			
			
					List myList=(List)session.getAttribute("fileList");
					
					 Iterator itr =  myList.iterator();
			         
			         System.out.println("Reading for session");
			         int i =0;
			         while (itr.hasNext()) {
			        	 
			        	
			     		attachmentVO.setAttachmentType("Profile Picture");
			     		
			        	 //file name
			     		attachmentVO.setPath((String)itr.next());
			        	 
			        	
			        	 
			        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
				     		attachmentDAO.insert(attachmentVO);
				     
			        	 
			        	 i++;
						}
					
			         VO_obj.setAttachmentID(attachmentVO);
					
					session.removeAttribute("fileList");
					
					VO_obj.setAttachmentID(attachmentVO);
		
		
		UserDAO DAO_obj=new UserDAO();
		DAO_obj.insert(VO_obj);
		
		show(request, response);
		
	}
	
protected void allotParking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
		
		UserVO userVO = new UserVO();
		userVO.setUserID(Long.valueOf(request.getParameter("userID")));
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(Long.valueOf(request.getParameter("parkingID")));
		
		ParkingEmployeeVO employeeVO = new ParkingEmployeeVO();
		employeeVO.setParkingID(parkingVO);
		employeeVO.setUserID(userVO);
		
		ParkingEmployeeDAO DAO_obj=new ParkingEmployeeDAO();
		DAO_obj.insert(employeeVO);
		
		show(request, response);
		
	}
	
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
    	
		
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		long personID = Long.valueOf(request.getParameter("personID"));
		long uID = Long.valueOf(request.getParameter("userID"));
		int userType = Integer.valueOf(request.getParameter("userType"));
		
		UserDAO DAO_obj=new UserDAO();
		UserVO vo1 = null;
		
		PersonVO personVO = new PersonVO();
		personVO.setPersonID(personID);
		personVO.setEmail(email);
		personVO.setFirstName(firstName);
		personVO.setLastName(lastName);
		personVO.setMobileNumber(mobileNumber);
		
		PersonDAO dao = new PersonDAO();
		dao.update(personVO);
		
		
		UserVO VO_obj = new UserVO();
		VO_obj.setPassword(password);
		VO_obj.setPersonID(personVO);
		VO_obj.setUserName(userName);
		VO_obj.setUserID(uID);
		VO_obj.setUserType(userType);
		
		if(session.getAttribute("fileList") != null){
			 AttachmentVO attachmentVO = new AttachmentVO();

				//getting file from Session
				
				
				
						List myList=(List)session.getAttribute("fileList");
						
						 Iterator itr =  myList.iterator();
				         
				         System.out.println("Reading for session");
				         int i =0;
				         while (itr.hasNext()) {
				        	 
				        	
				     		attachmentVO.setAttachmentType("Parking data");
				     		
				        	 //file name
				     		attachmentVO.setPath((String)itr.next());
				        	 
				        	
				        	 
				        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
					     		attachmentDAO.insert(attachmentVO);
					     
				        	 
				        	 i++;
							}
						
				         VO_obj.setAttachmentID(attachmentVO);
						
						session.removeAttribute("fileList");
						
						VO_obj.setAttachmentID(attachmentVO);
			
			
		}else{
	
			List lsTemp = DAO_obj.getElementByID(VO_obj);
			Iterator itrTemp = lsTemp.iterator();
			
			vo1 = (UserVO)itrTemp.next();
			
			VO_obj.setAttachmentID(vo1.getAttachmentID());
		}
		
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
 protected void updateCurrentUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
    	
		
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		long personID = Long.valueOf(request.getParameter("personID"));
		long uID = Long.valueOf(request.getParameter("userID"));
		int userType = Integer.valueOf(request.getParameter("userType"));
		
		UserDAO DAO_obj=new UserDAO();
		UserVO vo1 = null;
		
		PersonVO personVO = new PersonVO();
		personVO.setPersonID(personID);
		personVO.setEmail(email);
		personVO.setFirstName(firstName);
		personVO.setLastName(lastName);
		personVO.setMobileNumber(mobileNumber);
		
		PersonDAO dao = new PersonDAO();
		dao.update(personVO);
		
		
		UserVO VO_obj = new UserVO();
		VO_obj.setPassword(password);
		VO_obj.setPersonID(personVO);
		VO_obj.setUserName(userName);
		VO_obj.setUserID(uID);
		VO_obj.setUserType(userType);
		
		if(session.getAttribute("fileList") != null){
			 AttachmentVO attachmentVO = new AttachmentVO();

				//getting file from Session
				
				
				
						List myList=(List)session.getAttribute("fileList");
						
						 Iterator itr =  myList.iterator();
				         
				         System.out.println("Reading for session");
				         int i =0;
				         while (itr.hasNext()) {
				        	 
				        	
				     		attachmentVO.setAttachmentType("Profile Picture");
				     		
				        	 //file name
				     		attachmentVO.setPath((String)itr.next());
				        	 
				        	
				        	 
				        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
					     		attachmentDAO.insert(attachmentVO);
					     
				        	 
				        	 i++;
							}
						
				         VO_obj.setAttachmentID(attachmentVO);
						
						session.removeAttribute("fileList");
						
						VO_obj.setAttachmentID(attachmentVO);
			
			
		}else{
	
			List lsTemp = DAO_obj.getElementByID(VO_obj);
			Iterator itrTemp = lsTemp.iterator();
			
			vo1 = (UserVO)itrTemp.next();
			
			VO_obj.setAttachmentID(vo1.getAttachmentID());
		}
		
		DAO_obj.update(VO_obj);
		session.setAttribute("path", VO_obj.getAttachmentID().getPath()); 
		response.sendRedirect(request.getContextPath()+"/views/admin/home.jsp");
	}
    
        
    protected void selectParking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long userID=Long.parseLong(request.getParameter("id"));
    	UserVO VO_obj =new UserVO();
		VO_obj.setUserID(userID);
		
		ParkingDAO DAO_obj=new ParkingDAO();
		List obj= DAO_obj.showAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("userEmployeeID", userID);
		session.setAttribute("obj_all", obj);
		
		
		response.sendRedirect(request.getContextPath()+"/views/admin/User/alotParking.jsp");
	}
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long userID=Long.parseLong(request.getParameter("id"));
    	UserVO VO_obj =new UserVO();
		VO_obj.setUserID(userID);
		
		UserDAO DAO_obj=new UserDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/User/editUser.jsp");
	}
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
		
		
    	long uID=Long.parseLong(request.getParameter("id"));
    	UserVO VO_obj =new UserVO();
		VO_obj.setUserID(uID);
		
		
		UserDAO DAO_obj=new UserDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserDAO DAO_obj=new UserDAO();
		
		List obj= DAO_obj.showAll();
		ParkingEmployeeDAO dao = new ParkingEmployeeDAO();
		List employeeList = dao.showAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		session.setAttribute("eList", employeeList);
		
		
		response.sendRedirect(request.getContextPath()+"/views/admin/User/manageUser.jsp");
	
	}

    protected void showEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserDAO DAO_obj=new UserDAO();
		
		List obj= DAO_obj.showAllEmployee();
		
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/User/manageUser.jsp");
	
	}
    
    protected void showAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserDAO DAO_obj=new UserDAO();
    	List obj= DAO_obj.showAllAdmin();
		
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/User/manageUser.jsp");
	
	}
}
