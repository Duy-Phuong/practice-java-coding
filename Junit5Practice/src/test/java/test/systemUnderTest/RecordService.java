package test.systemUnderTest;

public class RecordService {
    private final RecordDao dao;
    private final SequenceGenerator generator;

    public RecordService(SequenceGenerator generator, RecordDao dao) {
        this.generator = generator;
        this.dao = dao;
    }

    public Record saveRecord(Record record) {

        System.out.println("Saving Record in RecordService");
        record.setId(generator.getNext());
        dao.saveRecord(record);

        NotificationService.getInstance().sendNotification("New Record Added");
        return record;
    }

    public void saveTwoDuplicatedRecords(Record record) {
        System.out.println("Saving 2 Records in RecordService");
        record.setId(generator.getNext());
        dao.saveRecord(record);
        dao.saveRecord(record);

        NotificationService.getInstance().sendNotification("Two new Records Added");
    }

    public void updateExistingRecords(Record record) {
        try {
            dao.updateRecord(record);
            System.out.println("Update record successfully!");
        } catch (NullPointerException e) {
            System.out.println("Caught inside method().");
            throw e; // rethrowing the exception
        }
    }

    public void updateExistingRecordsTwoTimes(Record record) {
        try {
            dao.updateRecordToTestDoNothing(1, record);
        } catch (NullPointerException e) {
            System.out.println("Caught inside method().");
            // Retry
            dao.updateRecordToTestDoNothing(1, record);
            System.out.println("Update record successfully!");
        }
    }
}