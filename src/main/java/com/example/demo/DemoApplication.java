package com.example.demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());
	public static void main(String[] args) {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.fatal("This is a fatal message");
		logger.error("This is an error message");
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/multiply")
	public String getMultiplyPage() {
		return "multiply";
	}

//	@PostMapping("/result")
//	public String getResult(@RequestParam int factorA, @RequestParam int factorB, Model model) {
//		Multiplication multiplication = multiplicationService.createMultiplication(factorA, factorB);
//		model.addAttribute("multiplication", multiplication);
//		return "result";
//	}
}