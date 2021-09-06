package Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAccess.IHibernateDal;
import Entity.Users;

@Service
public class UserManager implements UserService {
	
	private IHibernateDal hibernateDal;
	@Autowired
	public UserManager(IHibernateDal hibernateDal) {
		this.hibernateDal = hibernateDal;
	}
	@Override
	@Transactional
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return hibernateDal.getAll();
	}
	@Override
	@Transactional
	public void add(Users user) {
		hibernateDal.add(user);
		
	}
	@Override
	@Transactional
	public void update(Users user) {
		hibernateDal.update(user);
		
	}
	@Override
	@Transactional
	public void delete(Users user) {
		hibernateDal.delete(user);
		
	}
	@Override
	@Transactional
	public Users getById(int id) {
		
		return hibernateDal.getById(id);
	}
	
	
	
	
}
