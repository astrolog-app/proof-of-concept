package controllers;

import models.equipment.Equipment;
import ui.popUps.ImagingSessionInfo;

public class ImagingSessionController {
    public void addImagingSessionManually(Equipment equipment) {
        new ui.popUps.NewImagingSessionManually(equipment);
    }

    public void addImagingSessionAutomatically() {

    }

    public void removeImagingSession() {

    }

    public void editImagingSession() {

    }

    public void showImagingSessionDetails() {
        new ImagingSessionInfo();
    }
}
