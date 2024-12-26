package com.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Payload.TaskDto;
import com.demo.Service.TaskService;

import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	//save the task 
	@PostMapping("/{userid}/tasks")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(name ="userid") long userid,
			@RequestBody TaskDto taskDto){
				return new ResponseEntity<>(taskService.saveTask(userid, taskDto),HttpStatus.CREATED);
		
		
		
	}
	@GetMapping("/{userid}/tasks")
	public ResponseEntity<List<TaskDto>> getallTask(@PathVariable(name ="userid") long userid){
		
		return new ResponseEntity<>(taskService.getAllTasks(userid),HttpStatus.OK);
		
	}
	
    @GetMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<TaskDto> getTask(@PathVariable(name = "userid") long userid,
                                           @PathVariable(name = "taskid") long taskid) {
        TaskDto taskDto = taskService.getTask(userid, taskid); // Correct method call
        return new ResponseEntity<>(taskDto, HttpStatus.OK); // Returns 200 OK with the task
    }
    
    @DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "userid") long userid,
                                           @PathVariable(name = "taskid") long taskid) {
        // Call service to delete the task
        taskService.deleteTask(userid, taskid);

        // Return 204 No Content response indicating that the task was successfully deleted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }



}

