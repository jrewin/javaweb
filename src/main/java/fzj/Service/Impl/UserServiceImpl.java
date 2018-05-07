package fzj.Service.Impl;

import fzj.Model.User;
import fzj.Service.UserService;

public class UserServiceImpl implements UserService {

	public boolean UserLogin(User user) {

		if(user.getUsername().equals("username") && user.getPassword().equals("password")) {
			return true;
		}
		
		return false;
	}

	
}
