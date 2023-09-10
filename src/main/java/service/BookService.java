package service;

import java.util.List;

import entity.Book;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class BookService {

	public Uni<Book> findBookById(Long id) throws Exception {
		Uni<Book> bookUni = Book.findBookById(id);
		return bookUni;
	}
	
	public Uni<List<Book>> getAllBooks() throws Exception {
		Uni<List<Book>> bookList = Book.getAllBooks();
		return bookList;
	}
	
	public Uni<Book> createBook(Book book) throws Exception {
		validateTitle(book);
		
		Uni<Book> bookUni = Book.addBook(book);
		
		return bookUni;
	}
	
	public Uni<Book> updateBook(Long id, Book book) {
		validateTitle(book);
		
		Uni<Book> bookUni = Book.updateBook(id, book);

		return bookUni;
	}

	public Uni<Boolean> deleteBook(Long id) {
		Uni<Boolean> deleted = Book.deleteBookById(id);
		
		return deleted;
	}
	
	private void validateTitle(Book book) {
	    if (book == null || book.getTitle() == null) {
            throw new WebApplicationException("Book title was not set on request.", Status.BAD_REQUEST);
        }		
	}
}