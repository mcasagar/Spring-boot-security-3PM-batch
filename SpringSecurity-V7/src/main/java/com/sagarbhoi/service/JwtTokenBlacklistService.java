package com.sagarbhoi.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenBlacklistService {
	// JWT → Expiration time (epoch milliseconds) 
	private final Map<String, Long> blacklist = new ConcurrentHashMap<>(); 
	
	public void blacklistToken(String token, Date expiration) { 
		// Store token only until its expiration time 
		blacklist.put(token, expiration.getTime()); 
	} 
	public boolean isBlacklisted(String token) { 
		Long expirationTime = blacklist.get(token); 
		if (expirationTime == null) {
			return false; 
		} 
		// Remove token if it has already expired 
		if (System.currentTimeMillis() >= expirationTime) { 
			blacklist.remove(token); 
			return false; 
		} 
			return true; 
		}
}
