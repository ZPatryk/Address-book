package com.springtest.testconnect.controller;
import com.springtest.testconnect.Entity.User;
import com.springtest.testconnect.Entity.OrganizationalUnit;
import com.springtest.testconnect.service.UserService;
import com.springtest.testconnect.service.OrganizationalUnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private final UserService userService;
    private final OrganizationalUnitService unitService;

    public IndexController(UserService userService, OrganizationalUnitService unitService){
        this.userService=userService;
        this.unitService=unitService;
    }

    // Klasa kontrolera obsługująca zwracanie wszystkich użytkowników
    @GetMapping("/")
    public String displayHomePage(Model model) {
        Map<OrganizationalUnit, List<User>> usersByUnit = userService.getAllUsersGroupedByUnit();
        List<User> usersWithoutUnit = userService.getUsersWithoutUnit();

        model.addAttribute("usersByUnit", usersByUnit);
        model.addAttribute("usersWithoutUnit", usersWithoutUnit);

        return "index";
    }


    // Klasa kontrolera obsługująca zwracanie jednostki orgnanizacyjnej
    @GetMapping("/{unitId}")
    public String getDepartment(Model model, @PathVariable Long unitId) {
        List<User> departmentUsers = userService.getUsersByUnitId(unitId);
        String selectedUnitName = unitService.getUnitNameById(unitId); // Metoda do pobrania nazwy jednostki organizacyjnej
        model.addAttribute("departmentUsers", departmentUsers);
        model.addAttribute("selectedUnitName", selectedUnitName);
        return "department";
    }

    // Klasa kontrolera obsługująca zwracanie użutkownika po wpisaniu nazwy
    @PostMapping("/search")
    public ModelAndView performSearch(@RequestParam("query") String query) {
        List<User> usersByFname = userService.searchUsersByFname(query);

        ModelAndView modelAndView = new ModelAndView("searchResults");
        modelAndView.addObject("query", query);
        modelAndView.addObject("usersByFname", usersByFname);
        return modelAndView;
    }


}

