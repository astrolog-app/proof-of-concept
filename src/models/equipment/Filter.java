package models.equipment;

import java.util.UUID;

public class Filter extends EquipmentItem {
    private String filterType;

    public Filter() {}

    public Filter(UUID id, boolean used, String brand, String name, String filterType) {
        super(id, used, brand, name);
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    @Override
    public String[] getProperties() {
        return new String[]{getFilterType()};
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"Band Type"};
    }
}
