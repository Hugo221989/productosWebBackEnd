package com.shop.restfull.service;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
