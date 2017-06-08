package com.nuance.qa.tool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GreetingController extends BaseController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private SimpMessageSendingOperations messaging;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("item", new Item());
		return "index";
	}
	
	@PostMapping("/purchase")
	public String purchase(@ModelAttribute Item item, RedirectAttributes model) {
		rabbitTemplate.convertAndSend(QatoolApplication.queueName, "Some one buy a ticket");
		logger.info(item.getId() + ", " + item.getContent());
		Order order = new Order();
		order.setAmount(55);
		messaging.convertAndSend("/topic/customerservice", order);
		model.addAttribute("amount", 10);
		model.addAttribute("username", "huajian");
		model.addFlashAttribute("order", order);
		return "redirect:/confirm/{username}";
	}
	
	@GetMapping("/order")
	public String orders() {
		return "order";
	}
	
	@GetMapping("/confirm/{username}")
	public String confirmOrder(HttpServletRequest request, Model model, @PathVariable String username) {
		logger.info("the path parameter = " + username);
		logger.info("the query parameter = " + request.getParameter("amount"));
		if (model.containsAttribute("order")) {
			System.out.println("contains order: ");
		} else {
			System.out.println("not containing order");
		}
		Greeting greeting = new Greeting();
		greeting.setId(5);
		greeting.setContent("this is from tester.");
		model.addAttribute("greeting", greeting);
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
