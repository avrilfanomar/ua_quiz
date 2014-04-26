package ua.quiz.dao.impl;

import ua.quiz.da.entity.UserEntity;
import ua.quiz.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author o_sshyik
 */
@Repository
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
}
