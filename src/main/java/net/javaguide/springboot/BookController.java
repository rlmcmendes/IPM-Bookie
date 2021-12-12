package net.javaguide.springboot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public Book addBook(@RequestBody Book b){
		return br.save(b);
	}
	@PutMapping("updateOwner/{id}/{username}")  
	public void updateOwner(@PathVariable("id") String id,@PathVariable("username") String username){
		Book b=br.getById(Integer.parseInt(id));
		b.setUsername(username);
		br.save(b);
	}
	
	@DeleteMapping("deleteBook/{id}")  
	public void deleteBook(@PathVariable("id") String id){
		br.delete(br.findById(Integer.parseInt(id)).get());
	}
	
	@GetMapping("books")
	public List<Book> getBooks() {
		return br.findAll();
	}
	@GetMapping("getBook/{id}")
	public Book getBook(@PathVariable("id") String id) {
		return br.findById(Integer.parseInt(id)).get();
	}
	@GetMapping("getBookByName/{title}")
	public List<Book> getBookByName(@PathVariable("title") String title) {
		List<Book> list=new LinkedList<>();
		for(Book b:br.findAll()) {
			if(b.getTitle().contains(title)) {
				list.add(b);
			}
		}
		return list;
	}
	@GetMapping("getBookByGenre/{genre}")
	public List<Book> getBookByGenre(@PathVariable("genre") String genre) {
		Map<Book,Boolean> m=new HashMap<>();
		List<Book> list=new LinkedList<>();
		for(Book b:br.findAll()) {
			for(String  s:b.getGenre()) {
				if(s.contains(genre) && (m.get(b)==null || !m.get(b))) {
					list.add(b);
					m.put(b, true);
				}
			}
		}
		return list;
	}

}
