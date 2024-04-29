package com.coloc.crud.coloc.services.imp;

import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.models.Task;
import com.coloc.crud.coloc.models.User;
import com.coloc.crud.coloc.repositories.FlatShareRepository;
import com.coloc.crud.coloc.repositories.TaskRepository;
import com.coloc.crud.coloc.repositories.UserRepository;
import com.coloc.crud.coloc.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final FlatShareRepository flatShareRepository;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository, UserRepository userRepository, FlatShareRepository flatShareRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.flatShareRepository = flatShareRepository;
    }

    @Override
    public Task createTask(Task task, String username, Long idFlat) {
        User assignedTo = userRepository.findByUsername(username);
        FlatShare flatShare = flatShareRepository.findById(idFlat).orElse(null);


        task.setAssignedTo(assignedTo);
        task.setFlatShareTasks(flatShare);

        assert flatShare != null;
        flatShare.getTasks().add(task);

        taskRepository.save(task);

        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks(Long flatShareId) {
        return taskRepository.findTasksByFlatShareId(flatShareId);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = getTaskById(id);
        existingTask.setDescriptionT(task.getDescriptionT());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setAssignedTo(task.getAssignedTo());
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
