package DataAccess;

import java.util.List;

import org.springframework.stereotype.Repository;

import Entity.Users;

@Repository
public interface IHibernateDal {

	List<Users> getAll();
	void add(Users user);
	void update(Users user);
	void delete(Users user);
	Users getById(int id);
}
