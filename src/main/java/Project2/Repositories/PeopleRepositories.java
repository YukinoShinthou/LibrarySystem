package Project2.Repositories;

import Project2.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepositories extends JpaRepository<Person,Integer> {

    List<Person> findByName(String name);
}
