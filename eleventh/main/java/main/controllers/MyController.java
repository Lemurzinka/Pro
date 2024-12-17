package main.controllers;

import main.Apartment;
import main.services.ApartmentsService;
import main.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    static final int ITEMS_PER_PAGE = 6;

    private final ApartmentsService apartmentsService;
private final UserService userService;
private final PasswordEncoder passwordEncoder;


    public MyController(ApartmentsService apartmentsService, UserService userService, PasswordEncoder passwordEncoder) {
        this.apartmentsService = apartmentsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping(value = "/newuser")
    public  String update(@RequestParam String login,
                          @RequestParam String password,
                          @RequestParam(required = false) String email,
                          @RequestParam(required = false) String phone,
                          @RequestParam(required = false) String address,
                          Model model) {
        String passHash = passwordEncoder.encode(password);

        if(! userService.addUser(login, passHash, email, phone, address)) {
            model.addAttribute("exists", true);
            model.addAttribute("login", login);
            return "register";
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false, defaultValue = "0")Integer page) {
        if (page <0){
            page = 0;
        }
        List<Apartment> apartments = apartmentsService
                .findAll(PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

    model.addAttribute("apartments", apartments);
    model.addAttribute("allPages", getPageCount());

    return "index";
    }

    @GetMapping("/reset")
    public String reset(){
        return "redirect:/";
    }

    @GetMapping("/apartmentAddPage")
    public String apartmentAddPage(Model model){
        return "apartmentAddPage";
    }

    @PostMapping("/search")
  public String searchByPrice(@RequestParam("priceFrom") double priceFrom,
                              @RequestParam("priceTo") double priceTo,
                              Model model){
        List<Apartment> apartments = apartmentsService.findByPrice(priceFrom, priceTo, PageRequest.of(0, ITEMS_PER_PAGE));
  model.addAttribute("apartments", apartments);
  return "searchResults";
    }

    @PostMapping("/apartment/delete")
    public ResponseEntity<Void> delete(
            @RequestParam (value = "toDelete[]", required = false) long[] toDelete){
        if(toDelete != null && toDelete.length > 0){
            apartmentsService.deleteApartment(toDelete);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/apartment/add")
public String apartmentAdd(@RequestParam String district,
                           @RequestParam String address,
                           @RequestParam double area,
                           @RequestParam int countOfRooms,
                           @RequestParam double price){
        Apartment apartment = new Apartment(district, address, area, countOfRooms, price);
        apartmentsService.addApartment(apartment);
        return "redirect:/";
}

@PostMapping(value = "apartment/update")
public String updateApartment(@RequestParam long id,
                              @RequestParam String district,
                              @RequestParam String address,
                              @RequestParam double area,
                              @RequestParam int countOfRooms,
                              @RequestParam double price){
        apartmentsService.updateApartment(district, address, area, countOfRooms, price, id);
return "redirect:/";
    }

    private long getPageCount() {
        long totalCount = apartmentsService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

}
