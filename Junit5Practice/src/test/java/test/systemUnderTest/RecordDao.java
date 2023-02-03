package test.systemUnderTest;

public class RecordDao {

    public Record saveRecord(Record record) {
        System.out.println("Saving Record in RecordDao");
        return record;
    }

    public Record updateRecord(Record record) {
        System.out.println("Update Record in RecordDao");
        return record;
    }

    // Using void to test doNothing()
    public void updateRecordToTestDoNothing(int i, Record record) {
        System.out.println("Update Record in RecordDao");
    }
}
