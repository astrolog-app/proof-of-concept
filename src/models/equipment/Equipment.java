package models.equipment;

import java.util.HashSet;
import java.util.List;

public class Equipment {
    private HashSet<Telescope> telescopes = new HashSet<>();

    public HashSet<Telescope> getTelescopes() {
        return telescopes;
    }
    public void addTelescope(Telescope telescope) {
        telescopes.add(telescope);
    }
    public void addTelescopes(List<Telescope> telescopes) {
        this.telescopes.addAll(telescopes);
    }
    public void removeTelescope(Telescope telescope) {
        telescopes.remove(telescope);
    }
}
