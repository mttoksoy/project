package Services;

import java.util.List;

import Entity.Users;

public interface UserService {

	List<Users> getAll();
	void add(Users user);
	void update(Users user);
	void delete(Users user);
	Users getById(int id);
}
