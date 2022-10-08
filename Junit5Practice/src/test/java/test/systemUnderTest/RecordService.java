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
}