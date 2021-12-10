package net.javaguide.springboot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import java.util.List;

@Entity
@Table(name="books")
public class Book {
	
	@Column(name="username")
	private String username;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="title")
	private String title;
	
	@ElementCollection
	private List<String> genre;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="")
	private String author;
	
	@Column(name="photo")
	private String photo;
	
	@ElementCollection
	private List<Double> coords;

	public Book() {}
	public Book(String username, int rating, String isbn, String title, List<String> genre, String comments, String author,
			String photo, List<Double> coords) {
		super();
		this.username = username;
		this.rating = rating;
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.comments = comments;
		this.author = author;
		this.photo = photo;
		this.coords = coords;
	}

	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Double> getCoords() {
		return coords;
	}

	public void setCoords(List<Double> coords) {
		this.coords = coords;
	}
	
}
