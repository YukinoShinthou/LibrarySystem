package Project2.Controlles;

import Project2.Models.Book;
import Project2.Models.Person;
import Project2.Services.BooksService;
import Project2.Services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final BooksService booksService;
    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String AllPeople(Model model){
        model.addAttribute("people" , peopleService.findAll());

        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showOnePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOnePerson(id));
        Person owner = peopleService.findOnePerson(id);
        model.addAttribute("books", booksService.foundByPersonId(owner));

        return "people/showOnePerson";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/newPerson";
    }
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult  ){
        if (bindingResult.hasErrors()){
            return "people/newPerson";
        }
        else {
            person.setCreatedAt(new Date());
            peopleService.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findOnePerson(id));
        return "people/editPerson";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "people/editPerson";
        }
        else {
            peopleService.update(id, person);
            return "redirect:/people";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
