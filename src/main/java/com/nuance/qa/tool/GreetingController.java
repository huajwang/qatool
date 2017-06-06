package com.nuance.qa.tool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("item", new Item());
		return "index";
	}
	
	@PostMapping("/purchase")
	@ResponseBody
	public String purchase(@ModelAttribute Item item) {
		System.out.println("kkadaffffffffffffffffffffffffffffff - " + item.getId() + ", " + item.getContent());
		return "result";
	}

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }

}
