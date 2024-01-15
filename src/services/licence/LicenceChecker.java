package services.licence;

import models.license.Licence;
import models.license.LicenceType;
import ui.popUps.LicenceRequest;
import utils.Enums;

import javax.swing.*;

public class LicenceChecker {
    private final Licence licence;
    private final JFrame parentFrame;

    public LicenceChecker(Licence licence, JFrame parentFrame) {
        this.licence = licence;
        this.parentFrame = parentFrame;
    }

    public void check() {
        checkLicenceFile();
        checkApiRequest();
    }

    private void checkLicenceFile() {
        if (licence == null || (licence.getLicenceKey() == null && licence.getLicenceType() != LicenceType.LITE)) {
            Timer timer = new Timer(750, e -> new LicenceRequest(parentFrame));

            timer.setRepeats(false);
            timer.start();
        } else {
            parentFrame.setTitle("AstroLog " + Enums.enumToString(licence.getLicenceType()));
        }
    }

    private void checkApiRequest() {

    }
}