package service.implement;

import database.Connector;
import model.Task;
import service.ITaskService;

import java.util.List;

public class TaskService implements ITaskService {
    private final Connector connector = Connector.getInstance();

    @Override
    public boolean addTask(Task task) {
        if (task.name == null || task.name.isEmpty()) {
            return false;
        }
        return connector.createTask(task);
    }

    @Override
    public List<Task> getTasks(int userId) {
        return connector.getTasks(userId);
    }
}
