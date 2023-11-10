import models.equipment.Equipment;
import services.AppActions;
import services.fileHandler.EquipmentStore;

public class App {
    public static void main(String[] args) {
        AppActions appActions = new AppActions();

        Equipment equipment = new Equipment();
        EquipmentStore equipmentStore = new EquipmentStore(equipment);
        equipmentStore.load();

        System.out.println(equipment.getTelescopes());

        appActions.initialize();
    }
}
