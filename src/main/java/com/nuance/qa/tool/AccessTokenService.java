package com.nuance.qa.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {
	
	@Autowired
	private AccessTokenRepository accessTokenRepository;

	public String findAccessToken(String userId) {
		return accessTokenRepository.findAccessToken(userId);
	}

}
