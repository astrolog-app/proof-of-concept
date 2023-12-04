package services.imagingSessions;

public class TableData {
    Object[][] tableData;
    private void generateTableData() {

    }

    public Object[][] getTableData() {
        generateTableData();
        return tableData;
    }
}
