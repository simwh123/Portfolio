package com.simwo.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simwo.spring.dto.ItemOption;
import com.simwo.spring.service.MapleService;
import com.simwo.spring.service.SeleniumService;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MapleController {
    private String world;
    private final MapleService mapleService;
    private final SeleniumService seleniumService;

    @GetMapping("/")
    public String maple(Model model) {

        return "maple";
    }

    @PostMapping("/serchId")
    public String serch(@RequestParam(name = "name") String Id, Model model) {
        mapleService.ocid(Id);
        ArrayList<String[]> a = mapleService.item(Id);
        String[] basic = mapleService.character(Id);
        world = basic[1];
        System.out.println();
        model.addAttribute("data", a);
        model.addAttribute("img", basic[0]);
        model.addAttribute("id", Id);
        return "maple";
    }

    @PostMapping("/check-item")
    @ResponseBody
    public ResponseEntity<?> checkItem(@RequestBody ItemOption item) {

        String option1 = mapleService.stringSplit(item.getItemOption_1());
        String option2 = mapleService.stringSplit(item.getItemOption_2());
        String option3 = mapleService.stringSplit(item.getItemOption_3());

        String price = seleniumService.check(world, item.getItemName(), option1, option2, option3);

        return ResponseEntity.ok().body(price);
    }
}
