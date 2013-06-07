/*Author - Sonal Raj for Team Mediakart.com
  name : search.java
  Purpose : This servlets connects to the appropriate database and queries the table of the
            appropriate media table containing the data . .
		      This java class accepts the search string from the html index form and separates the keywords
            which will be used to search the database of the respective media directories
				
			*/
				
 import java.io.*;
 import java.sql.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 
 public class search extends HttpServlet
 {
  PrintWriter pw = null;
  public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
  {
    res.setContentType("text/html");
	 pw = res.getWriter();
	 
	 //Output the Header
	 pw.print( "\n"+"<!DOCTYPE html>"+
"  <html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" +
"  <head>"+
"  <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"+
"	<meta name=\"robots\" content=\"index, follow\" />"+
"	<meta name=\"keywords\" content=\"\" />"+
"	<meta name=\"description\" content=\"\" />"+
"	<title>Mediakart : Image Gallery</title>"+
"	<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"css/stylesheet.css\" />"+
"	<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"img/favicon.ico\" />"+
"	<!--[if lt IE 7]>"+
"	<script type=\"text/javascript\" src=\"js/IE7.js\"></script>"+
"	<![endif]-->"+
"	<script type=\"text/javascript\" src=\"js/jquery-1.3.2.min.js\"></script>"+
"	<script type=\"text/javascript\" src=\"js/jquery.anchor.js\"></script>"+
"	<script type=\"text/javascript\" src=\"js/jquery.easySlider1.5.js\"></script>"+
"	<script type=\"text/javascript\" src=\"js/jquery.autogrow.js\"></script>"+
"	<script type=\"text/javascript\" src=\"js/subfolder_sifr.lite.js\"></script>"+
"	<script type=\"text/javascript\">"+
"	// ### sifr.lite.js"+
"		window.onload = function () {"+
"			var vegur = new Font('Vegur-EL_0500.swf', {tags:'h1,h2'});"+
"				vegur.replace();"+
"		};"+
"	// ### jquery.autogrow.js & jquery.easySlider.packed.js"+
"		$(document).ready (function() {"+
"			$('textarea.expanding').autogrow();"+
"			$(\"#slider\").easySlider({"+
"				orientation: 'horizontal'"+
"			});"+
"		});"+
"	</script>"+
"</head>"+
"<body>"+
"    <div id=\"container\">"+
"    <a name=\"top\" id=\"top\"></a>"+
"  	<div id=\"header\">"+
"			<div id=\"header_l\">"+
"				<h2>"+
"				   <a href=\"index.html\">Home    </a>"+
"					<a href=\"images.html\">Images    </a>"+
"           	<a href=\"video.html\">Videos    </a>"+ 
"              <a href=\"audio.html\">Audio    </a>"+
"              <a href=\"about.html\">About</a>"+
"			</h2>"+
"		</div><!-- #header_l -->"+
"     <div id=\"header_r\">"+
"		<a href=\"index.html\"><img src=\"img/logo-trans.png\" alt=\"Mediakart.com\" /></a>"+
"		</div><!-- #header_r -->"+
"	</div><!-- #header -->"+
"  <div class=\"clear\">&nbsp;</div>"+
"        <!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>Here's what <br /><span class=\"top\">we</span> <span class=\"bold\">Found</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr -->");
//end of header . . 
//Put the dynamic contents of the page here . . . 
//|###########################################################################################################

         //obtain the search string entered by the user
			String nul = null;
   	   String key = "image";
			String value = null;
			value = req.getParameter(key);
			if(value==nul)
			{
			  key = "all";
			  value = req.getParameter(key);
			  if(value==nul)
			  {
			    key = "video";
				 value = req.getParameter(key);
				 if(value==nul)
				 {
				  key = "audio";
				  value = req.getParameter(key);
				 }
			  }
			}//end of all ifs
			pw.println("<br /><br /><br />");
			
			//testing : 
			//pw.println("<h3>Key : "+key+"&& Value : "+value+"+</h3><br /><br /><br />");
			
		
			 //Now we process the string to separae the keywords that will be used to search the database
   		String terms[] = value.split(" ");
		   
	/*		for(String term:terms)
			 {
			   pw.println("<h3>"+term+"</h3><br />");
          }
	*/		
			//Establish a JDBC connection with Database
         try{
			    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");;
				 Connection c = DriverManager.getConnection("jdbc:odbc:mydsn","system","mca6");
				 pw.println("<h3><span class=\"bold\"></span>Connected to Database </h3><br />");
				 Statement s = c.createStatement();
				 int ss = 0;
				 ResultSet rs;
//################################# PICTURE SEARCH CODE #######################################				 
				  if(key.equals("pics"))
				    {
					   //for(String term:terms)
				       {
					      rs = s.executeQuery("select * from pics where pdesc like '%as%'");
							while(rs.next())
							{
							 pw.println("<br /><h3>ID:"+rs.getString(1)+" </h3>");
							 pw.println("<br /><h3>URL:"+rs.getString(2)+" </h3>");
							 pw.println("<br /><h3>NAME :"+rs.getString(3)+" </h3>");
							 pw.println("<br /><h3>CATEGORY :"+rs.getString(4)+" </h3>");
							 pw.println("<br /><h3>DESCRIPTION :"+rs.getString(5)+" </h3><br />");														
							}
						 }
											  
					 }
				 
				 }//end of try       
         catch(SQLException e)
			    {
				   pw.println("<h3><span class=\"bold\">Sorry</span>,There was an error connecting to the Database.</h3>");
   			   pw.println("<h3><span class=\"bold\"></span>Please Try again Later.</h3>");
				 }
			catch(Exception e)
		       {
		      	pw.println("<h3><span class=\"bold\">Sorry</span>,Unable to process at this time!!</h3>");
   			   pw.println("<h3><span class=\"bold\"></span>Please Try again Later.</h3>");
		       }
				 

//#############################################################################################################
//End of the Dynamic content Section . . . .
//Beginning of the Footer . . .
pw.print("\n"+
"<div class=\"clear\">&nbsp;</div>"+
"		<div id=\"footer\">"+
"			<div id=\"footer_l\">"+
"				Copyright &copy; 2012 by Sonal Raj. All rights reserved."+
"			</div><!-- #footer_l -->"+
"			<div id=\"footer_r\">"+
"				<!-- ### DO NOT REMOVE! ### -->"+
"			</div><!-- #footer_r -->"+
"		</div><!-- #footer -->"+
"		<div class=\"clear\">&nbsp;</div>"+
"	</div><!-- #container -->"+
"</body>"+
"</html>"+"\n");
//End of the Hard-Coded Footer

} //end of service() method

}//end of class search



	 
	 
	 
	 