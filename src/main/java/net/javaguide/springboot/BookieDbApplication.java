package net.javaguide.springboot;

import java.util.LinkedList;
import java.util.List;

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
			
			this.ur.save(new User("user","name","email","pass",18,true));
			this.ur.save(new User("user1","name2","user1","pass",18,true));
			this.ur.save(new User("user2","name3","user2","pass",18,true));
			
			
	}
		
	}
