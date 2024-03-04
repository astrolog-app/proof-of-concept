import services.AppActions;
import services.AppLogger;
import services.GitHubAPI;

import java.awt.desktop.SystemSleepEvent;

public class App {
    public static void main(String[] args) {
        GitHubAPI.downloadFile(null);
    }
}
