package ua.quiz.dao.impl;

import org.springframework.stereotype.Repository;
import ua.quiz.dao.SimpleDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author o_sshyik
 */
public class AbstractDao<Entity> implements SimpleDao<Entity> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Entity get(Serializable id) {
		return (Entity) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}

	@Override
	public Serializable save(Entity entity) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public Class getEntityClass() {
		return (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
