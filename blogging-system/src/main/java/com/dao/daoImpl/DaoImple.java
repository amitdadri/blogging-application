package com.blog.uu;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.MyUtil;

public class DaoImple implements UserDAO {
private Session session;
	@Override
	public void createUser(User user) {
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	//@Override
	public User findUser(String username) {
		User user1=null;
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		user1=(User) session.get(User.class,username);
		return user1;
	}

	@Override
	public void update(User user) {		
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	@Override
	public List<User> getUsers() {
		List<User> user;
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		user = session.createSQLQuery("SELECT * FROM users").list();
		//user = (List<User>) session.createCriteria(User.class).list();
		return user;
	}

	@Override
	public void removeUser(User user) {
		
	}

	@Override
	public void removeUser(String unametobedeleted) {
		User user3=new User();
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		System.out.println(unametobedeleted);
		user3 = (User)session.get(User.class,unametobedeleted);
	    session.delete(user3);
	    session.flush();
//		user3.setUsername(unametobedeleted);;
//		session.delete(user3);
		session.getTransaction().commit();
	}

	@Override
	public int login(String u, String p) {
		User user1=null;
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		Query query=session.createSQLQuery("select * from users where username='"+u+"' and password='"+p+"'");
		int size=query.list().size();
		session.getTransaction().commit();
		if(size==1)
		{
			System.out.println("successful");
			return 0;
		}
		else
		{
			System.out.println("please enter correct credentials");
			return 1;
			
		}
	}
	@Override
	public Blog viewblog(int blogid) {
		Blog blog=null;
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		blog=(Blog) session.get(Blog.class,blogid);
		return blog;
	}


	public Blog searchblog(String category) {
		Blog blog=null;
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		blog=(Blog) session.get(Blog.class,category);
		return blog;
	}

	
	public void createblog(Blog blog) {
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		session.save(blog);
		session.getTransaction().commit();
	}

	@Override
	public void deleteblog(int blogidtobedeleted) {
		Blog blog3=new Blog();
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		blog3 = (Blog)session.get(Blog.class,blogidtobedeleted);
	    session.delete(blog3);
	    session.flush();
//		user3.setUsername(unametobedeleted);;
//		session.delete(user3);
		session.getTransaction().commit();
	}

	//@Override
	
	public void updateblog(int bid,String title,Timestamp time,String des) {
		Transaction t = null;
		this.session=MyUtil.getSesso();
		t = session.beginTransaction();
		//Query query=session.createQuery("update Blog set blogtitle='"+title+"',modifieddate='"+time+"',description='"+des+"' where blogid="+bid);
//		query.setParameter("title", title);
//		query.setParameter("time", time);
//		query.setParameter("des", des);
		//query.setParameter("bid", bid);
		//query.executeUpdate();
		Blog bl=new Blog();
		bl.setBlogid(bid);
		bl.setBlogtitle(title);
		bl.setDescription(des);
		session.saveOrUpdate(bl);
		t.commit();
	    
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	
	
	
	
	@Override
	public void postcomment(Comment comment) {
		this.session=MyUtil.getSesso();
		session.beginTransaction();
		session.save(comment);
		session.getTransaction().commit();
		
	}

//	@Override
	public void updateblog1(int bid,Blog blog) {
		 this.session = MyUtil.getSesso();
		try{
			session.getSessionFactory();
			session.beginTransaction();
			
			Query query=session.createQuery("from Blog where blogid=:bid");
			query.setParameter("bid", bid);
			Iterator<Blog> it = query.iterate();
			while(it.hasNext()){
				Blog p = it.next();
				p.setBlogtitle(blog.getBlogtitle());
				p.setCategory(blog.getCategory());
				p.setCreateddate(blog.getCreateddate());
				p.setModifieddate(blog.getModifieddate());
				p.setSummary(blog.getSummary());
				p.setDescription(blog.getDescription());
				p.setAveragerating(blog.getAveragerating());
				session.saveOrUpdate(p);
			}
			//query.executeUpdate();
		   
			session.getTransaction().commit();
			}catch(Exception e){
				//LOGGER.debug(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}finally{
				//session.flush();
				session.close();
			}
		
	}
	

}