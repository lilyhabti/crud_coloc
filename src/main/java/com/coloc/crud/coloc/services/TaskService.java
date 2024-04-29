package com.coloc.crud.coloc.services;

import com.coloc.crud.coloc.models.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task, String username, Long idFlat);
    Task getTaskById(Long id);
    List<Task> getAllTasks(Long flatShareId);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}
