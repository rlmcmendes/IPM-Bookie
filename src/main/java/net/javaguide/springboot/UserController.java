package net.javaguide.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("api/user/")
public class UserController {

	@Autowired
	private UserRepository ur;
	
	@PostMapping("addUser")  
	public void addUser(@RequestBody User u){
		ur.save(u);
	}
	@PutMapping("updateUser")  
	public void updateUser(String email,String password,boolean isAuthor){
		User u=ur.getById(email);
		u.setPass(password);
		u.setAuthor(isAuthor);
		ur.save(u);
	}
	
	//do not use this in the real app
	@DeleteMapping("deleteUser/{email}")  
	public void deleteUser(@PathVariable("email") String email){
		ur.deleteById(email);
	}
	@GetMapping("getUserById/{email}")
	public User getUserById(@PathVariable("email") String email) {
		return ur.findById(email).get();
	}
	@GetMapping("users")
	public List<User> getUsers() {
		return ur.findAll();
	}
	@GetMapping("getBooks/{email}")
	public List<Integer> getBooksFromUser(@PathVariable("email") String email) {
		return ur.getById(email).getBooks();
	}
	
	@PostMapping("addBook/{email}")  
	public void addBookToUser(@PathVariable("email") String email,@RequestBody Book b) throws URISyntaxException{
		User u=ur.getById(email);
		final String uri = "http://localhost:8080/api/book/addBook";
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.postForObject(url,b,Book.class);
		u.addBook(b.getBarCode());
		ur.save(u);
	}
	
	@DeleteMapping("deleteBook/{email}/{barCode}")  
	public void deleteBookFromUser(@PathVariable("email") String email,@PathVariable("barCode") int barCode) throws URISyntaxException{
		User u=ur.getById(email);
		final String uri = "http://localhost:8080/api/book/deleteBook/"+barCode;
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(url);
		u.delBook(barCode);
		ur.save(u);
	}
	
	@PostMapping("trade/{email1}/{email2}/{barCode}")
	public void trade(@PathVariable("email1") String email1,@PathVariable("email2") String email2,@PathVariable("barCode")int barCode) throws URISyntaxException {
		User u1=ur.getById(email1);
		User u2=ur.getById(email2);
		final String uri = "http://localhost:8080/api/book/updateOwner/"+barCode+"/"+email2;
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put(url, null);
		u1.delBook(barCode);
		u2.addBook(barCode);
		ur.save(u1);
		ur.save(u2);
	}
	
	//email1 follows email2
	@PostMapping("follow/{email1}/{email2}")  
	public void follow(@PathVariable("email1") String email1,@PathVariable("email2") String email2){
		User u1=ur.getById(email1);
		User u2=ur.getById(email2);
		u1.addFollowing(email2);
		ur.save(u1);
		u2.addFollower(email1);
		ur.save(u2);
	}
	
	//email1 unfollows email2
	@PostMapping("unfollow/{email1}/{email2}")  
	public void unfollow(@PathVariable("email1") String email1,@PathVariable("email2") String email2){
		User u1=ur.getById(email1);
		User u2=ur.getById(email2);
		u1.delFollowing(email2);
		ur.save(u1);
		u2.delFollower(email1);
		ur.save(u2);
	}
}
