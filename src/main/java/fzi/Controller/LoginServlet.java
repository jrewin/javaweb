package fzi.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fzj.Model.User;
import fzj.Service.UserService;
import fzj.Service.Impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("passowrd");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		UserService userServiceImpl = new UserServiceImpl();
		if(userServiceImpl.UserLogin(user)) {
			resp.sendRedirect("/index.jsp");
			
		}
	}
}
