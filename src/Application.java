import models.AppConfiguration;
import services.ApplicationActions;
import ui.MainUI;

public class Application {
    public static void main(String[] args) {
        ApplicationActions appActions = new ApplicationActions(new AppConfiguration());

        appActions.initialize();
    }
}
