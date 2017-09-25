package com.blog.uu;

import java.sql.Timestamp;
import java.util.List;

public interface UserDAO {
	public void createUser(User user);
	public User findUser(String username);
	public void removeUser(User user);
	public List<User>getUsers();
	public void update(User user);
	public void removeUser(String username);
	public int login(String username,String password);
	public Blog viewblog(int blogid);
	public Blog searchblog(String category);
	public void createblog(Blog blog);
	public void deleteblog(int blog);
	public void updateblog(int bid,String title,Timestamp time,String des);	
	public void updateblog1(int bid,Blog blog);	
	public void postcomment(Comment comment);
}
