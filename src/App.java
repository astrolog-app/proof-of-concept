import services.AppActions;
import services.fileHandler.ConfigurationStore;

public class App {
    public static void main(String[] args) {
//        AppActions appActions = new AppActions();
//
//        appActions.initialize();

        ConfigurationStore.load(null);
    }
}
