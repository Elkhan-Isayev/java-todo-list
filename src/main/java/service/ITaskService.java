package service;

import model.Task;

import java.util.List;

public interface ITaskService {
    boolean addTask(Task task);

    List<Task> getTasks(int userId);
}
