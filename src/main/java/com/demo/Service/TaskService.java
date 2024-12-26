package com.demo.Service;

import java.util.List;

import com.demo.Payload.TaskDto;

public interface TaskService {
	
	
	public TaskDto saveTask(long userid, TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userid);
	
	public TaskDto getTask(long userid,long todoid);
	

	public void deleteTask(long userid, long taskid);
	

}
