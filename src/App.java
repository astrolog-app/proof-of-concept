import services.AppActions;
import services.AppLogger;

public class App {
    public static void main(String[] args) {
        AppActions appActions = new AppActions();

        AppLogger.save();
        appActions.initialize();
    }
}
