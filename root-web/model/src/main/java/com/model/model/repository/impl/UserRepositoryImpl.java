package com.model.model.repository.impl;

import org.springframework.stereotype.Repository;

import com.model.model.entity.User;
import com.model.model.repository.UserRepository;
import com.org.framework.data.BaseHibernateJpaRepository;

/**
 * User Repository implementation
 *
 * @author: Y Kamesh Rao
 * @created: 3/26/12 8:30 AM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
@Repository
public class UserRepositoryImpl extends BaseHibernateJpaRepository<User, Long>implements UserRepository {

	@Override
	public User findByEmail(String email) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.email = ?")
				.setString(0, email).uniqueResult();
	}

	@Override
	public User findByUsername(String username) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.userName = ?")
				.setString(0, username).uniqueResult();
	}
}
