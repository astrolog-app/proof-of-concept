import models.AppConfiguration;
import services.AppActions;

public class App {
    public static void main(String[] args) {
        AppActions appActions = new AppActions();

        appActions.initialize();
    }
}
