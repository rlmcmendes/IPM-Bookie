package net.javaguide.springboot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="username")
	private String username;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="pass")
	private String pass;
	@Column(name="age")
	private int age;
	@Column(name="isAuthor")
	private boolean isAuthor;
	@ElementCollection
	private List<String> following;
	@ElementCollection
	private List<String> followers;
	@ElementCollection
	private List<Integer> books;
	
	public User() {
		following = new LinkedList<>();
		followers = new LinkedList<>();
		books = new LinkedList<>();
	}
	
	public User(String username, String name, String email, String pass, int age, boolean isAuthor) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.age = age;
		this.isAuthor = isAuthor;
		following = new LinkedList<>();
		followers = new LinkedList<>();
		books = new LinkedList<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAuthor() {
		return isAuthor;
	}

	public void setAuthor(boolean isAuthor) {
		this.isAuthor = isAuthor;
	}

	public List<String> getFollowing() {
		return following;
	}

	public void follow(String username) {
		following.add(username);
	}
	public void unfollow(String username) {
		following.remove(username);
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void addFollower(String username) {
		followers.add(username);
	}
	public void removeFollower(String username) {
		followers.remove(username);
	}
	public List<Integer> getBooks() {
		return books;
	}

	public void addBook(int book) {
		books.add(book);
	}
	
	public void deleteBook(int id) {
		for(int i=0;i<books.size();i++) {
			if(books.get(i)==id) {
				books.remove(i);
			}
		}
	}
	
	public int getBook(int id) {
		for(int b: books) {
			if(b==id) {
				return b;
			}
		}
		return -1;
	}
	

}
