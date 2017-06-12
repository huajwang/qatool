package com.nuance.qa.tool;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {
	
	@Autowired
	private EntityManager em;

	@Override
	public String findAccessToken(String userId) {
		return em.find(User.class, userId).getAccess_token();
	}

}
