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
			String t1;
			
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
				 //pw.println("<h3><span class=\"bold\"></span>Connected to Database Key = "+key+" & Value = "+value+"</h3><br />");
				 Statement s = c.createStatement();
				 int ss = 0;
				 ResultSet rs;
				 //pw.println("<br /><br /><br />");
//################################# PICTURE SEARCH CODE ###############################################				 
				  if(key.equals("image"))
				    {
					   for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from pics where pdesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>ID  : "+rs.getString(1)+" <br />");
							 //pw.println("<h3>URL:"+rs.getString(2)+" </h3>");
							 pw.println("NAME  : "+rs.getString(3)+" <br />");
							 pw.println("CATEGORY  : "+rs.getString(4)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(5)+" </p><br />");
							 pw.println("  </div>");// inner text-div -c
							 pw.println("  <div class=\"divein\">");//inner pic div - o
							 pw.println("     <img src=\""+rs.getString(2)+"\" width=\"100%\" height=\"100%\"></img>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c
							 //pw.println("<br />");  														
							}
							pw.println("<br />");
						 }
						 
				 //################# SEARCH THE EXTERNAL LINKS IMAGE TABLE##################
             
pw.println("<br /><br /><br /><br /><br />");
pw.println("<br /><br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>External<br /><span class=\"top\">links to</span> <span class=\"bold\">Images</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");

				  for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from piclinks where ldesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                     // pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>IMAGE ID  : "+rs.getString(1)+" <br />");
							 pw.println("<h3>IMAGE NAME  : "+rs.getString(3)+" </h3><br />");
							 t1 = rs.getString(2);
							 pw.println("IMAGE URL  : <a href=\""+t1+"\">"+t1+"</a></p><br />");
							// pw.println("  </div>");// inner text-div -c
							 pw.println("</div>");//outer div -c
							 //pw.println("<br />");  														
							}
							pw.println("<br />");
						 }

							


				 //################# END OF Ext. Link IMAGE SEARCH #########################							  
					 }
//################################# VIDEO SEARCH CODE ##################################################
		      if(key.equals("video"))
				    {
					   for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from vids where vdesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>ID  : "+rs.getString(1)+"<br />");
							 //pw.println("<h3>URL :"+rs.getString(2)+" </h3>");
							 pw.println("NAME  : "+rs.getString(6)+"     <br />Duration : "+rs.getString(5)+" <br />");
							 pw.println("CATEGORY  : "+rs.getString(3)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(4)+" </p><br />");
							 pw.println("  </div>");// inner text-div -c
							 pw.println("  <div class=\"diveinn\">");//inner pic div - o
							 pw.println("     <video src=\""+rs.getString(2)+"\" width=\"240\" height=\"135\" controls></video>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c

														
							}
							pw.println("<br />");
						 }
					//################# SEARCH THE EXTERNAL LINKS VIDEO TABLE##################
             
pw.println("<br /><br /><br /><br /><br />");
pw.println("<br /><br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>External<br /><span class=\"top\">links to</span> <span class=\"bold\">Videos</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");

				  for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from vidlinks where ldesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                     // pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>VIDEO ID  : "+rs.getString(1)+" <br />");
							 pw.println("<h3>VIDEO NAME  : "+rs.getString(3)+" </h3><br />");
							 t1 = rs.getString(2);
							 pw.println("VIDEO URL : <a href=\""+t1+"\">"+t1+"</a></p><br />");
							// pw.println("  </div>");// inner text-div -c
							 pw.println("</div>");//outer div -c
							 //pw.println("<br />");  														
							}
							pw.println("<br />");
						 }

							


				 //################# END OF Ext. Link VIDEO SEARCH #########################	 
											  
					 }			
					 
//################################# AUDIO SEARCH CODE ##################################################

               if(key.equals("audio"))
				    {
					   for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from audio where adesc like '%"+term+"%'");
							while(rs.next())
							{
                      pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2a\">");//inner text-div -o
							 pw.print("<p>ID  : "+rs.getString(1)+"    <br />");
							 //pw.println("URL:"+rs.getString(2)+" </h3>");
							 pw.println("NAME  : "+rs.getString(5)+" <br />");
							 pw.println("VOICE OF  : "+rs.getString(6)+"                  ");
						// pw.println(" <audio src=\""+rs.getString(2)+"\" width=\"200px\" height=\"95px\" controls></audio><br />");
							 pw.println("CATEGORY  : "+rs.getString(3)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(4)+" </p><br />");
							 pw.println("  </div>");// inner text-div -c
						    pw.println("  <div class=\"diveina\">");//inner pic div - o
							 pw.println("     <audio src=\""+rs.getString(2)+"\" width=\"240\" height=\"135\" controls></audio>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c
														
							}
							pw.println("<br />");
						 }
				//################# SEARCH THE EXTERNAL LINKS AUDIO TABLE##################
             
pw.println("<br /><br /><br /><br /><br />");
pw.println("<br /><br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>External<br /><span class=\"top\">links to</span> <span class=\"bold\">Audio</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");

				  for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from audiolinks where ldesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                     // pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>AUDIO ID  : "+rs.getString(1)+" <br />");
							 pw.println("<h3>AUDIO NAME  : "+rs.getString(3)+" </h3><br />");
							 t1 = rs.getString(2);
							 pw.println("AUDIO URL  : <a href=\""+t1+"\">"+t1+"</a></p><br />");
							// pw.println("  </div>");// inner text-div -c
							 pw.println("</div>");//outer div -c
							 //pw.println("<br />");  														
							}
							pw.println("<br />");
						 }

							


				 //################# END OF Ext. Link AUDIO SEARCH #########################
											  
					 }

//################################# ALL SEARCH CODE : IMAGE+AUDIO+VIDEO ################################

               if(key.equals("all"))
				    { 
					   //pic search
						
pw.println("<br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>Among <br /><span class=\"top\">the</span> <span class=\"bold\">Pictures</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");

					   for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from pics where pdesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>ID : "+rs.getString(1)+" <br />");
							 //pw.println("URL:"+rs.getString(2)+" <br />");
							 pw.println("NAME  : "+rs.getString(3)+" <br />");
							 pw.println("CATEGORY  : "+rs.getString(4)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(5)+" </p><br />");	
							 pw.println("  </div>");// inner text-div -c
							 pw.println("  <div class=\"divein\">");//inner pic div - o
							 pw.println("     <img src=\""+rs.getString(2)+"\" width=\"100%\" height=\"100%\" ></img>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c
							 													
							}
							pw.println("<br />");
						 }//end of pic search
						 //start of video search'
pw.println("<br /><br /><br /><br /><br />");
pw.println("<br /><br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>Among <br /><span class=\"top\">the</span> <span class=\"bold\">Videos</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");
						  for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from vids where vdesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2\">");//inner text-div -o
							 pw.println("<p>ID : "+rs.getString(1)+" <br />");
							 //pw.println("URL :"+rs.getString(2)+" <br />");
							 pw.println("NAME  : "+rs.getString(6)+"<br />Duration : "+rs.getString(5)+" <br />");
							 pw.println("CATEGORY  : "+rs.getString(3)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(4)+" </p><br />");
							 pw.println("  </div>");// inner text-div -c
							 pw.println("  <div class=\"diveinn\">");//inner pic div - o
							 pw.println("     <video src=\""+rs.getString(2)+"\" width=\"240\" height=\"135\" controls></video>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c														
							}
							pw.println("<br />");
						 }//end of video search
						 //start of audio search
pw.println("<br /><br /><br /><br /><br />");						 
pw.println("<br /><br /><br /><!-- ### WORK ### --><a name=\"work\" id=\"work\"></a>"+
"		<div class=\"hr\">"+
"			<h3>Among <br /><span class=\"top\">the </span> <span class=\"bold\">Audio</span></h3>"+
"			<div class=\"line\" id=\"line_work\"><a href=\"#top\" class=\"anchorLink\">TOP</a></div><!-- #line -->"+
"		</div><!-- #hr --><br /><br />");						 
						for(int i = 0;i<terms.length;i++)
				       {
						   String term = terms[i];
						   //pw.println("<br /><h3>Term = "+term+" </h3>");
					      rs = s.executeQuery("select * from audio where adesc like '%"+term+"%'");
							while(rs.next())
							{
							 pw.println("<div class=\"dyndiv\">"); //outer div - o
                      pw.println("  <div class=\"divein2a\">");//inner text-div -o
							 pw.print("<p>ID:"+rs.getString(1)+"    <br />");
							// pw.println("URL:"+rs.getString(2)+" <br />");
							 pw.println("NAME  : "+rs.getString(5)+" <br />");
							 pw.println("VOICE OF  : "+rs.getString(6)+" <br />");
							 pw.println("CATEGORY  : "+rs.getString(3)+" <br />");
							 pw.println("DESCRIPTION  : "+rs.getString(4)+" </p><br />");	
							 pw.println("  </div>");// inner text-div -c
							 pw.println("  <div class=\"diveina\">");//inner pic div - o
							 pw.println("     <audio src=\""+rs.getString(2)+"\" width=\"240\" height=\"135\" controls></audio>");
							 pw.println("  </div>");//inner pic-div -c
							 pw.println("</div>");//outer div -c													
							}
							pw.println("<br />");
							
						 }//end of audio search					  
					 }
					 
//################################# End of search codes ################################################				 
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



	 
	 
	 
	 