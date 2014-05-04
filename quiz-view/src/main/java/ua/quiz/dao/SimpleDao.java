package ua.quiz.dao;

import java.io.Serializable;

/**
 * @author o_sshyik
 */
public interface SimpleDao<Entity> {
	Entity get(Serializable id);

	Serializable save(Entity entity);
}
