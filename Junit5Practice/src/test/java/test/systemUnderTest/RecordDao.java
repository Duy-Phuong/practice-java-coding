package test.systemUnderTest;

public class RecordDao {

    public Record saveRecord(Record record) {
        System.out.println("Saving Record in RecordDao");
        return record;
    }
}
