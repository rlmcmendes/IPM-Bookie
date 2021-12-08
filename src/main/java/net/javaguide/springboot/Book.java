package net.javaguide.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	@Column(name="barCode")
	private int barCode;
	@Column(name="name")
	private String name;
	@Column(name="isbn")
	private int ISBN;
	@Column(name="userEmail")
	private String userEmail;
	@Column(name="genre")
	private String genre;
	
	public Book() {}

	public Book(int barCode, String name, int iSBN, String userEmail, String genre) {
		super();
		this.barCode = barCode;
		this.name = name;
		ISBN = iSBN;
		this.userEmail = userEmail;
		this.genre = genre;
	}

	public int getBarCode() {
		return barCode;
	}

	public void setBarCode(int barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	

}
