package com.dk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;

@Controller
public class RandomController {
    int current_number;
    int guesses;
    Random random = new Random();

    {
        current_number=0;
        guesses=0;
    }

    @GetMapping("/random")
    public String CheckRandom(@RequestParam(value = "num",required = false,defaultValue = "") String num,
                              Model model) {
        if(current_number==0)
        {
            model.addAttribute("InfoString","Press number-creation button");
            return "random";
        }
        int temp;
        try {
            temp = Integer.parseInt(num);
        }
        catch (Exception ex)
        {
            model.addAttribute("input",num);
            model.addAttribute("InfoString","Check your input");
            model.addAttribute("TotalGuesses",guesses);
            return "random";
        }
        guesses++;
        model.addAttribute("input",num);
        model.addAttribute("TotalGuesses",guesses);
        if(temp>current_number)
        {
            model.addAttribute("InfoString","Your number is greater");
        }
        if(temp<current_number)
        {
            model.addAttribute("InfoString","Your number is lower");
        }
        if(temp==current_number)
        {
            model.addAttribute("InfoString","You guessed right!");
        }
        return "random";
    }

    @PostMapping("/random")
    public String ChangeRandom(Model model) {
        current_number = random.nextInt(100)+1;
        System.out.printf("%d\n",current_number);
        model.addAttribute("InfoString","New number was created");
        guesses=0;
        model.addAttribute("TotalGuesses",guesses);
        return "random";
    }
}