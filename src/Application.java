import models.AppConfiguration;
import services.ApplicationActions;
import models.Path;

public class Application {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(Path.configurationPath);
        System.out.println();
        ApplicationActions appActions = new ApplicationActions(new AppConfiguration());

        appActions.initialize();
    }
}
