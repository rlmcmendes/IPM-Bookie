package net.javaguide.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book/")
public class BookController {
	
	@Autowired
	private BookRepository br;
	@PostMapping("addBook")  
	public void addBook(@RequestBody Book b){
		br.save(b);
	}
	@PutMapping("updateOwner/{barCode}/{email}")  
	public void updateOwner(@PathVariable("barCode") int barCode,@PathVariable("email") String email){
		Book b=br.getById(barCode);
		b.setUserEmail(email);
		br.save(b);
	}
	
	@DeleteMapping("deleteBook/{barCode}")  
	public void deleteBook(@PathVariable("barCode") int barCode){
		br.delete(br.findById(barCode).get());
	}
	
	@GetMapping("books")
	public List<Book> getBooks() {
		return br.findAll();
	}

}
