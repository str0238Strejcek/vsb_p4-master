package cz.vsb.p4.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * This is test controller to demonstrate spring MVC capabilities
 */
@Controller
public class StromController {

    private final StromRepository stromRepository;

    @Autowired
    public StromController(StromRepository stromRepository) {
        this.stromRepository = stromRepository;
        stromRepository.saveStrom(new Strom("Testovaci strom 1", "Praha", 0));
        stromRepository.saveStrom(new Strom("Testovaci strom 2", "Praha", 11));
        stromRepository.saveStrom(new Strom("Testovaci strom 3", "Praha", 0));
        stromRepository.saveStrom(new Strom("Testovaci strom 4", "Brno", 10));
        stromRepository.saveStrom(new Strom("Testovaci strom 5", "Ostrava", 11));
        stromRepository.saveStrom(new Strom("Testovaci strom 6", "Ostrava", 11));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String listAll(Model model) {
        List<Strom> stromy = stromRepository.getAll();
        model.addAttribute("result", stromy);
        return "/index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{nazev}")
    public String deleteStrom(@PathVariable("nazev") String nazev) {
        stromRepository.deleteStrom(nazev);
        return "redirect:/";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/filter")
    public String FilterStrom(@RequestParam("updateclass") Integer updateclass,
                              @RequestParam("townname") String param1) {
        stromRepository.filter(updateclass, param1);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/resetFilter")
    public String resetFilter() {
        stromRepository.resetFilter();
        return "redirect:/";
    }



}
