package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.User;

@Local
public interface UserService {
	User getByUsername(String uname);

	User getUser(int id);

	User insertUser(User user);

	User updateUser(User user);

	void deleteUser(User user);

	List<User> getAll();

	boolean login(String username, String password);
}
