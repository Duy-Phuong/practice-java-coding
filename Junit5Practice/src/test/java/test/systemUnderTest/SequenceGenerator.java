package test.systemUnderTest;

public class SequenceGenerator {
    private long value = 1;

    public long getNext() {
        System.out.println("Get Next Id in SequenceGenerator");
        return value++;
    }
}
