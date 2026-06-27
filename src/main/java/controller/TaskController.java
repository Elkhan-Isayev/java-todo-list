package controller;

import animation.Shaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Task;
import model.User;
import service.ITaskService;
import service.implement.TaskService;
import session.Session;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskController {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ITaskService taskService = new TaskService();

    // Task list page (addItem.fxml)
    @FXML private Label noTaskLabel;
    @FXML private ImageView addItemAddButton;
    @FXML private ListView<String> taskListView;

    // Add task form page (addItemForm.fxml)
    @FXML private Button saveTaskButton;
    @FXML private TextField taskField;
    @FXML private TextField descriptionField;

    @FXML
    void initialize() {
        if (taskListView != null) {
            loadTasks();
        }
    }

    private void loadTasks() {
        User currentUser = Session.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }
        List<Task> tasks = taskService.getTasks(currentUser.userId);
        taskListView.getItems().clear();
        for (Task task : tasks) {
            String description = (task.description == null || task.description.isEmpty())
                    ? "" : " — " + task.description;
            taskListView.getItems().add(
                    task.dateCreated.format(DATE_FORMAT) + "  •  " + task.name + description);
        }
        boolean empty = tasks.isEmpty();
        noTaskLabel.setVisible(empty);
        taskListView.setVisible(!empty);
    }

    public void handleMouseClickAddItemButton() {
        new Shaker(addItemAddButton).shake();
        switchScene(addItemAddButton, "/view/addItemForm.fxml");
    }

    public void handleSaveTaskButtonClick() {
        User currentUser = Session.getInstance().getCurrentUser();
        String name = taskField.getText() == null ? "" : taskField.getText().trim();
        String description = descriptionField.getText() == null ? "" : descriptionField.getText().trim();

        if (currentUser == null || name.isEmpty()) {
            new Shaker(taskField).shake();
            return;
        }

        Task task = new Task();
        task.userId = currentUser.userId;
        task.name = name;
        task.description = description;

        if (taskService.addTask(task)) {
            switchScene(saveTaskButton, "/view/addItem.fxml");
        } else {
            new Shaker(taskField).shake();
        }
    }

    private void switchScene(Node source, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            source.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
