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
	@Column(name="email")
	private String email;
	@Column(name="name")
	private String name;
	@Column(name="pass")
	private String pass;
	@Column(name="age")
	private int age;
	@Column(name="author")
	private boolean isAuthor;
	@Column(name="lat")
	private int latitude;
	@Column(name="lon")
	private int longitude;
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	@ElementCollection
	private List<String> following;
	@ElementCollection
	private List<String> followers;
	@ElementCollection
	private List<Integer> books;
	
	public User() {
		following=new LinkedList<>();
		followers=new LinkedList<>();
		books=new LinkedList<>();
	}
	public User(String name, String email,String pass, int age, boolean isAuthor,int latitude,int longitude) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.isAuthor = isAuthor;
		this.pass=pass;
		this.latitude=latitude;
		this.longitude=longitude;
		following=new LinkedList<>();
		followers=new LinkedList<>();
		books=new LinkedList<>();
		
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
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
	public void addFollower(String u) {
		followers.add(u);
	}
	public void delFollower(String u) {
		followers.remove(u);
	}
	public void addFollowing(String u) {
		following.add(u);
	}
	public void delFollowing(String u) {
		following.remove(u);
	}
	public void addBook(Integer b) {
		books.add(b);
	}
	public void delBook(Integer b) {
		books.remove(b);
	}
	public List<Integer> getBooks() {
		return books;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public List<String> getFollowing() {
		return following;
	}
	
	
}
