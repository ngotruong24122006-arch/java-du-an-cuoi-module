package org.example.business.impl;

import org.example.business.Login;
import org.example.dao.impl.LoginDAOImpl;

public class LoginImpl implements Login {
    @Override
    public boolean login(String username, String password) {
        return new LoginDAOImpl().checkLogin(username,password);
    }
}
