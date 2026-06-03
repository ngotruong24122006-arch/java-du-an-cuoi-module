package org.example.dao;

import org.example.model.Admin;

public interface LoginDAO {
    boolean checkLogin(String username,String password);
}
