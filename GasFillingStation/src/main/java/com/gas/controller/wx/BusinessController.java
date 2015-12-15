package com.gas.controller.wx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Card;
import com.gas.service.CardService;

@RestController
@RequestMapping("/wx")
public class BusinessController {
    @Autowired
    private CardService cardService;

    @RequestMapping("/api/card/list")
    public List<Card> list(HttpServletRequest request, Model model) {
        return cardService.getList();
    }

}
