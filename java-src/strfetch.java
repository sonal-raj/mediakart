/* Author - Sonal Raj for Team Mediakart.com
   Name : strfetch.java
	Purpose : This java class accepts the search string from the html index form and separates the keywords
	          which will be used to search the database of the respective media directories 
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class strfetch extends HttpServlets
    {
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	     {
		   //obtain the search string entered by the user
		   String key = req.getParameter("cf");
			
			//Now we process the string to separae the keywords that will be used to search the database
			String terms[] = key.split(" ");
			
			//Now we send each term at a time to the search servlet to query the database
			
			
			
			
	  				 
				 