package controller;

import animation.Shaker;
import animation.TransitionProvider;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.TextField;

public class TaskController {
    // Add item page
    public Label noTaskLabel;
    public ImageView addItemAddButton;
    // Add task form page
    public Button saveTaskButton;
    public javafx.scene.control.TextField taskField;
    public javafx.scene.control.TextField descriptionField;

    public void handleMouseClickAddItemButton() {
        Shaker addBtnShaker = new Shaker(addItemAddButton);
        addBtnShaker.shake();
        TransitionProvider transitionProviderAddItemButton = new TransitionProvider(addItemAddButton);
        transitionProviderAddItemButton.provide();
        TransitionProvider transitionProviderNoTaskLabel = new TransitionProvider(noTaskLabel);
        transitionProviderNoTaskLabel.provide();
    }

    public void handleSaveTaskButtonClick() {

    }

    @FXML
    void initialize() {

    }
}
