import com.formdev.flatlaf.FlatLightLaf;
import ui.welcome.WelcomeDialogue;

public class App {
    public static void main(String[] args) {
//        AppActions appActions = new AppActions();

        FlatLightLaf.setup();
        new WelcomeDialogue();

//        appActions.initialize();
    }
}
