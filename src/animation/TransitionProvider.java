package animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionProvider {
    private FadeTransition fadeTransition;

    public TransitionProvider(Node node) {
        fadeTransition = new FadeTransition(Duration.millis(2000), node);

        node.relocate(0, 20);
//        noTaskLabel.relocate(0, 45);
        node.setOpacity(0);
//        noTaskLabel.setOpacity(0);

        fadeTransition.setFromValue(1f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

    }

    public void provide() {
        fadeTransition.play();
    }
}
