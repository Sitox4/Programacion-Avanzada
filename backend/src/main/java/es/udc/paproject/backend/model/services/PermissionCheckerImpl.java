package es.udc.paproject.backend.model.services;

import java.util.Optional;

import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.entities.SesionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private SesionDao sesionDao;
	
	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {
		
		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);
		
		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		return user.get();
		
	}

	@Override
	public Sesion checkSesion(Long sesionId) throws InstanceNotFoundException {
		Optional<Sesion> sesion = sesionDao.findById(sesionId);

		if (!sesion.isPresent()) {
			throw new InstanceNotFoundException("project.entities.sesion", sesionId);
		}

		return sesion.get();
	}

}
