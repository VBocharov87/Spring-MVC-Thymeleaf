package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;
import web.service.CarServiceImpl;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(value = "/cars")
    public String carWelcome(@RequestParam(value = "count", defaultValue = "5") int count, Model model) {

        model.addAttribute("cars", carService.getCars(count));

        return "cars";
    }
}
