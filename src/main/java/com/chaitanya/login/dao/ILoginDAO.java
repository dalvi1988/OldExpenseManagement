package com.chaitanya.login.dao;

import com.chaitanya.login.model.Login;

public interface ILoginDAO {

	Login findByUserName(String username);

}