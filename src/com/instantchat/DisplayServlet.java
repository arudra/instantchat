package com.instantchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class DisplayServlet extends HttpServlet
{
	
	private String getLogoutURL (HttpServletRequest req) throws IOException
	{
		try {
			URI thisUri = new URI(req.getRequestURL().toString());
			URI logout = new URI(thisUri.getScheme(),
			    thisUri.getUserInfo(),
			    thisUri.getHost(),
			    thisUri.getPort(),
			    "",
			    "",
				"");

			return logout.toString();
			
		} catch (Exception e)
		{
			throw new IOException(e.getMessage());
		}
		
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    
	    final UserService userService = UserServiceFactory.getUserService();
		
		ArrayList<ChatRoom> list = RoomList.getInstance().getList();
		ChatRoom room;
		
		Logger.getAnonymousLogger().log(Level.INFO, "Displaying Chat Rooms");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Display</title>");			
		out.println("<style>");
		out.println("body{");
		out.println("	font-family: 'Terminal Dosis', Arial, sans-serif;");
		out.println("}");
		out.println(".container{");
		out.println("	position:relative;");
		out.println("}");
		out.println(".content{");
		out.println("	position:relative;");
		out.println("}");
		out.println("h1{");
		out.println("	margin:0px;");
		out.println("	padding:20px;");
		out.println("	font-size:32px;");
		out.println("	color:#000;");
		out.println("    text-shadow:1px 1px 1px rgba(255,255,255,0.9);");
		out.println("	text-align:center;");
		out.println("	font-weight:400;");
		out.println("}");
		out.println("h1 span{");
		out.println("    display:block;");
		out.println("	font-size:14px;");
		out.println("	color:#666;");
		out.println("    font-style:italic;");
		out.println("    font-family:Georgia, serif;");
		out.println("	padding-top:5px;");
		out.println("}");
		out.println("");
		out.println(".ca-menu{");
		out.println("    padding: 0;");
		out.println("    margin: 20px auto;");
		out.println("    width: 500px;");
		out.println("}");
		out.println(".ca-menu li{");
		out.println("    width: 500px;");
		out.println("    height: 100px;");
		out.println("    overflow: hidden;");
		out.println("    display: block;");
		out.println("    background: #fff;");
		out.println("    -webkit-box-shadow: 1px 1px 2px rgba(0,0,0,0.2);");
		out.println("    -moz-box-shadow: 1px 1px 2px rgba(0,0,0,0.2);");
		out.println("    box-shadow: 1px 1px 2px rgba(0,0,0,0.2);");
		out.println("    margin-bottom: 4px;");
		out.println("    border-left: 10px solid #000;");
		out.println("    -webkit-transition: all 300ms ease-in-out;");
		out.println("	-moz-transition: all 300ms ease-in-out;");
		out.println("	-o-transition: all 300ms ease-in-out;");
		out.println("	-ms-transition: all 300ms ease-in-out;");
		out.println("	transition: all 300ms ease-in-out;");
		out.println("}");
		out.println(".ca-menu li:last-child{");
		out.println("    margin-bottom: 0px;");
		out.println("}");
		out.println(".ca-menu li a{");
		out.println("    text-align: left;");
		out.println("    display: block;");
		out.println("    width: 100%;");
		out.println("    height: 100%;");
		out.println("    color: #333;");
		out.println("    position:relative;");
		out.println("}");
		out.println(".ca-icon{");
		out.println("    font-family: 'WebSymbolsRegular', cursive;");
		out.println("    font-size: 35px;");
		out.println("    text-shadow: 0px 0px 1px #333;");
		out.println("    line-height: 90px;");
		out.println("    position: absolute;");
		out.println("    width: 400px;");
		out.println("    left: 50px;");
		out.println("    text-align: center;");
		out.println("    -webkit-transition: all 300ms linear;");
		out.println("    -moz-transition: all 300ms linear;");
		out.println("    -o-transition: all 300ms linear;");
		out.println("    -ms-transition: all 300ms linear;");
		out.println("    transition: all 300ms linear;");
		out.println("}");
		out.println(".ca-menu li:hover{");
		out.println("    border-color: #FF9900;");
		out.println("    background: #000;");
		out.println("}");
		out.println(".ca-menu li:hover .ca-icon{");
		out.println("    color: #FF9900;");
		out.println("    text-shadow: 0px 0px 1px #FF9900;");
		out.println("    font-size: 65px;");
		out.println("}");
		out.println(".ca-menu li:hover {");
		out.println("    color: #FF9900;");
		out.println("    font-size: 14px;");
		out.println("}");
		out.println(".ca-menu li:hover {");
		out.println("    color: #fff;");
		out.println("    font-size: 30px;");
		out.println("}");
		out.println("#newroom, #roompassword {");
		out.println("  width:100%;");
		out.println("  height:100%;");
		out.println("  opacity:.95;");
		out.println("  top:0;");
		out.println("  left:0;");
		out.println("  display:none;");
		out.println("  position:fixed;");
		out.println("  background-color:#313131;");
		out.println("  overflow:auto");
		out.println("}");
		out.println("img#close {");
		out.println("  position:absolute;");
		out.println("  right:-14px;");
		out.println("  top:-14px;");
		out.println("  cursor:pointer");
		out.println("}");
		out.println("div#popupContact {");
		out.println("  position:absolute;");
		out.println("  left:50%;");
		out.println("  top:17%;");
		out.println("  margin-left:-202px;");
		out.println("  font-family:'Raleway',sans-serif");
		out.println("}");
		out.println("form {");
		out.println("  max-width:300px;");
		out.println("  min-width:250px;");
		out.println("  padding:10px 50px;");
		out.println("  border:2px solid gray;");
		out.println("  border-radius:10px;");
		out.println("  font-family:raleway;");
		out.println("  background-color:#fff");
		out.println("}");
		out.println("p {");
		out.println("  margin-top:30px");
		out.println("}");
		out.println("h2 {");
		out.println("  background-color:#FFFFB1;");
		out.println("  padding:20px 35px;");
		out.println("  margin:-10px -50px;");
		out.println("  text-align:center;");
		out.println("  border-radius:10px 10px 0 0");
		out.println("}");
		out.println("hr {");
		out.println("  margin:10px -50px;");
		out.println("  border:0;");
		out.println("  border-top:1px solid #ccc");
		out.println("}");
		out.println("input[type=text] {");
		out.println("  width:82%;");
		out.println("  padding:10px;");
		out.println("  margin-top:30px;");
		out.println("  border:1px solid #ccc;");
		out.println("  padding-left:40px;");
		out.println("  font-size:16px;");
		out.println("  font-family:raleway");
		out.println("}");
		out.println("#roomname {");
		out.println("  background-repeat:no-repeat;");
		out.println("  background-position:5px 7px");
		out.println("}");
		out.println("#password {");
		out.println("  background-repeat:no-repeat;");
		out.println("  background-position:5px 7px");
		out.println("}");
		out.println("#submit {");
		out.println("  text-decoration:none;");
		out.println("  width:100%;");
		out.println("  text-align:center;");
		out.println("  display:block;");
		out.println("  background-color:#FFBC00;");
		out.println("  color:#3F2B08;");
		out.println("  border:1px solid #FFCB00;");
		out.println("  padding:10px 0;");
		out.println("  font-size:20px;");
		out.println("  cursor:pointer;");
		out.println("  border-radius:5px");
		out.println("}");
		out.println("button{");
		out.println("  width:200px;");
		out.println("  height:45px;");
		out.println("  border:1px solid #ffad41; -webkit-border-radius: 3px; -moz-border-radius: 3px;border-radius: 3px;font-size:15px;font-family:arial, helvetica, sans-serif; padding: 9px 10px 10px 10px; text-decoration:none; display:inline-block;font-weight:bold; color: #FFFFFF;");
		out.println("  background-color: #ffc579; background-image: -webkit-gradient(linear, left top, left bottom, from(#ffc579), to(#fb9d23));");
		out.println("  background-image: -webkit-linear-gradient(top, #ffc579, #fb9d23);");
		out.println("  background-image: -moz-linear-gradient(top, #ffc579, #fb9d23);");
		out.println("  background-image: -ms-linear-gradient(top, #ffc579, #fb9d23);");
		out.println("  background-image: -o-linear-gradient(top, #ffc579, #fb9d23);");
		out.println("  background-image: linear-gradient(to bottom, #ffc579, #fb9d23);filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#ffc579, endColorstr=#fb9d23);");
		out.println("  cursor:pointer");
		out.println("}");
		out.println("button:hover{");
		out.println("  border:1px solid #ff9913;");
		out.println("  background-color: #ffaf46; background-image: -webkit-gradient(linear, left top, left bottom, from(#ffaf46), to(#e78404));");
		out.println("  background-image: -webkit-linear-gradient(top, #ffaf46, #e78404);");
		out.println("  background-image: -moz-linear-gradient(top, #ffaf46, #e78404);");
		out.println("  background-image: -ms-linear-gradient(top, #ffaf46, #e78404);");
		out.println("  background-image: -o-linear-gradient(top, #ffaf46, #e78404);");
		out.println("  background-image: linear-gradient(to bottom, #ffaf46, #e78404);filter:progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#ffaf46, endColorstr=#e78404);");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		
		out.println("<body id='body' style='overflow:hidden;'>");
		out.println("<h1>Chat Rooms</h1>");
		out.println("<div id='newroom'>");
		out.println("<div id='popupContact'>");
		out.println("<form action=\"\\instantchat\" id='newroomform' method='get' name='form'>");
		out.println("<img id='close' src='/images/buttonClose.png' onclick ='div_newroom_hide()' height='30' width='30'>");
		out.println("<h2>Create New Chat Room</h2>");
		out.println("<hr>");
		out.println("<input id='roomname' name='roomname' placeholder='Chat Room Name' type='text'>");
		out.println("<input id='password' name='password' placeholder='Chat Room Password' type='text'>");
		out.println("<p></p>");
		out.println("<a href='javascript:%20check_empty()' id='submit'>Submit</a>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div id='roompassword'>");
		out.println("<div id='popupContact'>");
		out.println("<form action='\' id='passwordform' method='get' name='passwordform'>");
		out.println("<img id='close' src='/images/buttonClose.png' onclick ='div_roompassword_hide()' height='30' width='30'>");
		out.println("<h2>Enter Password</h2>");
		out.println("<hr>");
		out.println("<input id='roompass' name='password' placeholder='Chat Room Password' type='text'>");
		out.println("<p></p>");
		out.println("<a href='javascript:%20submit_password()' id='submit'>Submit</a>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
	
		
		
		int i = 0;
		while (i < list.size())
		{
			room = list.get(i);

			//out.println("<p><button id='passwordpopup' onclick=\"div_roompassword_show('" + room.getLink() + "')\">" + room.getName() + "</button></p>");
			
			out.println("        <div class='container'>");
			out.println("            <div class='content'>");
			out.println("                <ul class='ca-menu'>");
			out.println("                    <li>");
			out.println("                        <a href='#' onclick=\"div_roompassword_show('" + room.getLink() + "'); return false;\">");
			out.println("                            <span class='ca-icon'>" + room.getName() + "</span>");
			out.println("                        </a>");
			out.println("                    </li>");
			out.println("                </ul>");
			out.println("            </div>");
			out.println("        </div>");
			
			
			
			i++;
		}
		out.println("<p><button id='newroompopup' onclick='div_newroom_show()'>Create New Room</button></p>");
		out.println("<p> <a href =" + userService.createLogoutURL(getLogoutURL(req)) + ">Logout</a> </p>");
		out.println("<script>");
		out.println("var clickedOnce = 0; ");
		out.println("var roomlink = ''; ");
		out.println("function submit_password() {");
		out.println("setTimeout(function(){ window.location.assign(roomlink + '&password=' + document.getElementById('roompass').value); }, 100);");
		out.println("}");
		out.println("function check_empty() {");
		out.println("if (document.getElementById('roomname').value == '') {");
		out.println("alert('Chat room name can not be empty!');");
		out.println("} else {");
		out.println("clickedOnce=1;");
		out.println("setTimeout(function(){ window.location.assign('\\instantchat?roomname=' + document.getElementById('roomname').value + '&password=' + document.getElementById('password').value); }, 100);");
		out.println("}");
		out.println("}");
		out.println("function div_newroom_show() {");
		out.println("document.getElementById('newroom').style.display = 'block';");
		out.println("}");
		out.println("function div_newroom_hide(){");
		out.println("document.getElementById('newroom').style.display = 'none';");
		out.println("}");
		out.println("function div_roompassword_show(roomlinkpassed) {");
		out.println("roomlink = roomlinkpassed;");
		out.println("document.getElementById('roompassword').style.display = 'block';");
		out.println("}");
		out.println("function div_roompassword_hide(){");
		out.println("document.getElementById('roompassword').style.display = 'none';");
		out.println("}");
		out.println(" function attachClicks () { ");
		out.println(" if (document.getElementById) { ");
		out.println(" document.getElementById('submit').onclick = function() {  ");
		out.println("  if (clickedOnce==1) {  ");
		out.println("  alert('Already Submitted'); return false;  ");
		out.println("  }    ");
		out.println("  };  ");
		out.println(" } ");
		out.println(" } ");
		out.println(" window.onload = function() { attachClicks(); };");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
