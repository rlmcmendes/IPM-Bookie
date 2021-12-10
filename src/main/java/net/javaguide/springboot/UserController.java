package net.javaguide.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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
	@PutMapping("updateUser/{username}/{pass}/{isAuthor}")  
	public void updateUser(@PathVariable("username") String username,@PathVariable("pass") String pass,@PathVariable("isAuthor") boolean isAuthor){
		User u=ur.getById(username);
		u.setPass(pass);
		u.setAuthor(isAuthor);
		ur.save(u);
	}
	@GetMapping("getUserById/{username}")
	public User getUserById(@PathVariable("username") String username) {
		return ur.findById(username).get();
	}
	@GetMapping("users")
	public List<User> getUsers() {
		return ur.findAll();
	}
	@GetMapping("getBooks/{username}")
	public List<Integer> getBooksFromUser(@PathVariable("username") String username) {
		return ur.getById(username).getBooks();
	}
	
	@PostMapping("addBook/{username}")  
	public void addBookToUser(@PathVariable("username") String username,@RequestBody Book b) throws URISyntaxException{
		User u=ur.getById(username);
		final String uri = "http://localhost:8080/api/book/addBook";
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    Book res=restTemplate.postForObject(url,b,Book.class);
		u.addBook(res.getId());
		ur.save(u);
	}
	
	@DeleteMapping("deleteBook/{username}/{id}")  
	public void deleteBookFromUser(@PathVariable("username") String username,@PathVariable("id") int id) throws URISyntaxException{
		User u=ur.getById(username);
		final String uri = "http://localhost:8080/api/book/deleteBook/"+id;
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(url);
		u.deleteBook(id);
		ur.save(u);
	}

	private void tradeABook(String username1, String username2,int id) throws URISyntaxException {
		User u1=ur.getById(username1);
		User u2=ur.getById(username2);
		final String uri = "http://localhost:8080/api/book/updateOwner/"+id+"/"+username2;
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put(url, null);
		u1.deleteBook(id);
		u2.addBook(id);
		ur.save(u1);
		ur.save(u2);
	}
	
	@PostMapping("trade/{username1}/{username2}/{id1}/{id2}")
	public void trade(@PathVariable("username1") String username1,@PathVariable("username2") String username2,
				@PathVariable("id1")int id1, @PathVariable("id2")int id2) throws URISyntaxException {
			tradeABook(username1,username2,id1);
			tradeABook(username2,username1,id2);
	}
	
	
	
	//email1 follows email2
	@PostMapping("follow/{username1}/{username2}")  
	public void follow(@PathVariable("username1") String username1,@PathVariable("username2") String username2){
		User u1=ur.getById(username1);
		User u2=ur.getById(username2);
		u1.follow(username2);
		ur.save(u1);
		u2.addFollower(username1);
		ur.save(u2);
	}
	
	//email1 unfollows email2
	@PostMapping("unfollow/{username1}/{username2}")  
	public void unfollow(@PathVariable("username1") String username1,@PathVariable("username2") String username2){
		User u1=ur.getById(username1);
		User u2=ur.getById(username2);
		u1.unfollow(username2);
		ur.save(u1);
		u2.removeFollower(username1);
		ur.save(u2);
	}
	@GetMapping("getFollowers/{username}")
	public List<String> listFollowers(@PathVariable("username") String username) {
		User u=ur.getById(username);
		return u.getFollowers();
	}
	@GetMapping("getFollowing/{username}")
	public List<String> listFollowing(@PathVariable("username") String username) {
		User u=ur.getById(username);
		return u.getFollowing();
	}
	@GetMapping("getUserByBook/{id}")
	public User getUserByBook(@PathVariable("id") int id) throws URISyntaxException {
		final String uri = "http://localhost:8080/api/book/getBook/"+id;
		URI url=new URI(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    return ur.findById(restTemplate.getForObject(url, Book.class).getUsername()).get();
	}
}
