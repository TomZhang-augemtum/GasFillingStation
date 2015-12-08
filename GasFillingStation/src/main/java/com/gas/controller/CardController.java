package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Car;
import com.gas.model.Card;
import com.gas.model.Role;
import com.gas.model.User;
import com.gas.service.CarService;
import com.gas.service.CardService;
import com.gas.service.UserService;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @RequestMapping("/api/card/list")
    public List<Card> list(HttpServletRequest request, Model model) {
        return cardService.getList();
    }

    @RequestMapping("/api/card/list/pagenation")
    public Page<Card> listByPagenation(HttpServletRequest request, Model model) {
        Pageable page = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        return cardService.getListByPagenationAndOrder(page);
    }

    @RequestMapping(value = "/api/card/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, User user, Car car) {
        Role role = new Role();
        role.setId(3L);
        user.setRole(role);
        User res = userService.saveUser(user);
        car.setUserid(res.getId());
        carService.save(car);
        Card card = new Card();
    }

    @RequestMapping("/api/card/delete")
    public void delete(HttpServletRequest request, Card card) {
        cardService.delete(card);
    }
}
