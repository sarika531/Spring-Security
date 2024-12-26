package com.demo.ServiceImpl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Task;
import com.demo.Entity.User;
import com.demo.Exception.ApiException;
import com.demo.Exception.TaskNotFound;
import com.demo.Exception.UserNotFound;
import com.demo.Payload.TaskDto;
import com.demo.Repository.TaskRepository;
import com.demo.Repository.UserRepository;
import com.demo.Service.TaskService;

@Service
public class TaskServiceImpl  implements TaskService{
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		User user = userRepository.findById(userid).orElseThrow(
			    () -> new UserNotFound(String.format("User Id %d not found", userid))
			);

	  Task task = modelMapper.map(taskDto , Task.class);
	  task.setUsers(user);
	  //after setting the user we are storing th data in db
	  Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask ,TaskDto.class);
	}
	@Override
	public List<TaskDto> getAllTasks(long userid) {
	    // Ensure the user exists
	    userRepository.findById(userid).orElseThrow(
	            () -> new UserNotFound(String.format("User Id %d not found", userid))
	    );

	    // Fetch tasks by user ID
	    List<Task> tasks = taskRepository.findByUsersId(userid);

	    // Convert tasks to TaskDto
	    return tasks.stream()
	                .map(task -> modelMapper.map(task, TaskDto.class))
	                .collect(Collectors.toList());
	}
	@Override
	public TaskDto getTask(long userid, long taskid) {
	    // Retrieve the user from the user repository
	    User user = userRepository.findById(userid).orElseThrow(
	            () -> new UserNotFound(String.format("User Id %d not found", userid))
	    );

	    // Retrieve the task from the task repository
	    Task task = taskRepository.findById(taskid).orElseThrow(
	            () -> new TaskNotFound(String.format("Task Id %d not found", taskid))
	    );

	    // Check if the task belongs to the given user
	    if (user.getId() != task.getUsers().getId()) {
	        throw new ApiException(String.format("Task Id %d does not belong to User Id %d", taskid, userid));
	    }

	    // Map the task to a TaskDto and return it
	    return modelMapper.map(task, TaskDto.class);
	}
	 
	@Override
	public void deleteTask(long userid, long todoid) {
	    // Ensure the user exists
	    User user = userRepository.findById(userid).orElseThrow(
	            () -> new UserNotFound(String.format("User Id %d not found", userid))
	    );

	    // Retrieve the task
	    Task task = taskRepository.findById(todoid).orElseThrow(
	            () -> new TaskNotFound(String.format("Task Id %d not found", todoid))
	    );

	    // Ensure the task belongs to the user
	    if (user.getId() != task.getUsers().getId()) {
	        throw new ApiException(String.format("Task Id %d does not belong to User Id %d", todoid, userid));
	    }

	    // Delete the task
	    taskRepository.delete(task); // Deleting the task from the repository
	}


}
