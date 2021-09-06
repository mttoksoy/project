package Services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Entity.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private Users users ;
	
	public MyUserDetailsService(Users user) {
		this.users = user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return new User(users.getName(),users.getPassword(),new ArrayList<>());
	}
}
