package com.taskapi.taskapi.clases.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskapi.taskapi.clases.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
