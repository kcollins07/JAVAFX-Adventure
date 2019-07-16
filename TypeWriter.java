package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TypeWriter {
    public static void Type(String intro, Label dialog) {
        final Animation ani = new Transition() {
            {
                setCycleDuration(Duration.millis(1));
            }

            protected void interpolate(double v) {
                final int length = intro.length();
                final int n = Math.round(length * (float) v);
                dialog.setText(intro.substring(0, n));
            }
        };

        ani.play();
    }
}
