package Project2.Repositories;


import Project2.Models.Book;
import Project2.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepositories extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findByBookNameStartingWith(String startingWith);

}
