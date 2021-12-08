package net.javaguide.springboot;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookieDbApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BookieDbApplication.class, args);
	}
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private BookRepository br;

	@Override
	public void run(String... args) throws Exception {
			
			this.ur.save(new User("user","email","pass",18,true,25376487,575859));
			this.ur.save(new User("user","user1","pass",18,true,567574,4758));
			this.ur.save(new User("user","user2","pass",18,true,464647,4657));
			
	}
		
	}
