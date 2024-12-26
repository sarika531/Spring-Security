package com.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Entity.Task;
import com.demo.Entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Correct the method to use 'users.id' as the reference for querying by user ID
    List<Task> findByUsersId(long userid);
}

