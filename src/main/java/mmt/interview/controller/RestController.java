package mmt.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mmt.interview.beans.Task;
import mmt.interview.servicepkg.TaskService;

@Controller
public class RestController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(path = "/invokeServices", produces = "application/json; charset=UTF-8")
    @ResponseBody
	public Long executeRestCalls(@RequestBody List<Task> tasks)
	{
		return taskService.executeTasks(tasks);
	}
}
