package com.example.plansets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping(path = "/")
public class DemoApplication {

	private static Map<String,List<String>> planSteps = new HashMap<>();
	static {
		// first pre-canned plan
		List<String> steps = new ArrayList<>();
		steps.add("do the first thing you can think of");
		steps.add("recap what you learned in your notes");
		steps.add("do the next thing that you identified in your learning");
		steps.add("are you done? If no, return to step 2; else, good job");
		planSteps.put("research", steps);
		// second pre-canned plan
		steps = new ArrayList<>();
		steps.add("call the dog");
		steps.add("secure the dog on a leash");
		steps.add("put dog in the tub");
		steps.add("wash all the parts");
		steps.add("wrap up in a towel and dry well");
		steps.add("set him free and watch him spazz out");
		planSteps.put("washing-a-dog", steps);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping()
	public String ping() {
		return "pong";
	}

	@GetMapping(path = "/plan-sets", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<String> planNames() {
		return planSteps.keySet().stream().collect(Collectors.toList());
	}
	
	@GetMapping(path ="/plan-sets/{planName}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<String> namedPlanSteps(@PathVariable("planName") String planName) {
		if (!planSteps.containsKey(planName)) {
			throw new NoSuchElementException("unknow plan");
		}
		return planSteps.get(planName);
	}
	
}
