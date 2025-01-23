package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;
import web.model.User;

@Controller
@RequestMapping("/user")
public class UsersController {


    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", userService.getAll());
        return "showUsers";
    }


    @GetMapping("/person")
    public String getPerson1(@RequestParam ("id") int id, Model model) {
        model.addAttribute("user1", userService.getById(id));
        return "userID";
    }


    @GetMapping("/new")
    public String form(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping
    //эта аннотация гвоорит о том, что этот метод будет вызываться по ссылке /user, но
    //только с POST запросами (при обработе POST запроса)
    public String addPerson(@ModelAttribute("user") User user) {
        //аннотация @ModelAttribute говорит о том что в полях объекта user
        //будут лежать значения которые пришли в теле запроса, то есть связывает
        //данные из тела запроса с полями объекта, и назначает через сеттеры
        userService.save(user);
        return "redirect:/user"; //по дефолу GET запрос
    }





    @GetMapping("/edit")
    public String editForm1(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping()
    public String edit(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.update(id, user);
        return "redirect:/user";
    }


    @DeleteMapping()
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/user";
    }

}
