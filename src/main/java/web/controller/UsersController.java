package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.DAO.UserDao;
import web.Service.UserService;
import web.models.User;

@Controller
@RequestMapping("/user")
public class UsersController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", userService.getAll());
        return "showUsers";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        //получаем конкретного человека по его айди
        model.addAttribute("user1", userService.getById(id));
        return "userID";
    }

    @GetMapping("/new")
    public String form(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping
    //эта аннотация гвоорит о том, что этот метод будет вызываться по ссылке /people, но
    //только с POST запросами (при обработе POST запроса)
    public String addPerson(@ModelAttribute("user") User user) {
        //аннотация @ModelAttribute говорит о том что в полях объекта person
        //будут лежать значения которые пришли в теле запроса, то есть связывает
        //данные из тела запроса с полями объекта, и назначает через сеттеры
        userService.save(user);
        return "redirect:/user"; //по дефолу GET запрос
    }


    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") int id){
        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/user";
    }

}
