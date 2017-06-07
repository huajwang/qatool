package com.nuance.qa.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController extends BaseController {
	
	@Autowired
	private SimpMessageSendingOperations messaging;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("item", new Item());
		return "index";
	}
	
	@PostMapping("/purchase")
	@ResponseBody
	public String purchase(@ModelAttribute Item item) {
		logger.info(item.getId() + ", " + item.getContent());
		Order order = new Order();
		order.setAmount(55);
		messaging.convertAndSend("/topic/customerservice", order);
		return "result";
	}
	
	@GetMapping("/order")
	public String orders() {
		return "order";
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
