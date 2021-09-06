package DataAccess;
import java.util.List;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import Entity.Users;

public class HibernateDal implements IHibernateDal  {

	private EntityManager entityManager;
	@Autowired
	public HibernateDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Users> getAll() {
		Session session=entityManager.unwrap(Session.class);
		List<Users> users= session.createQuery("from User",Users.class).getResultList();
		return users;
	}

	@Override
	@Before(value = "")
	public void add(Users user) {
		Session session=entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
		
	}

	@Override
	public void update(Users user) {
		Session session=entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
		
	}

	@Override
	public void delete(Users user) {
		Session session=entityManager.unwrap(Session.class);
		Users userToDelete=session.get(Users.class, user.getId());
		session.delete(userToDelete);
		
	}

	@Override
	public Users getById(int id) {
		Session session=entityManager.unwrap(Session.class);
		Users city=session.get(Users.class, id);
		return city;
	}
	
}
