package com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name ="Taskname" , nullable = false)
	private String taskname;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
	private User users;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Task(long id, String taskname, User users) {
		super();
		this.id = id;
		this.taskname = taskname;
		this.users = users;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskname=" + taskname + ", users=" + users + "]";
	}

}
