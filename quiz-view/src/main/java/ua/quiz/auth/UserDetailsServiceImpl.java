package ua.quiz.auth;

import ua.quiz.da.entity.UserEntity;
import ua.quiz.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author o_sshyik
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userDao.get(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("No such user : " + username);
		}
		if (userEntity.getRole() == null) {
			throw new UsernameNotFoundException("User " + username + " has no authority");
		}
		return buildUserDetails(userEntity);
	}

	private UserDetails buildUserDetails(UserEntity userEntity) {
		String username = userEntity.getLogin();
		String password = userEntity.getPassword();
		boolean enabled = true;

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		return new User(username, password, true, true, true, true, authorities);
	}
}
