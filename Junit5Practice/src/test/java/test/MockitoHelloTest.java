package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import test.systemUnderTest.NotificationService;
import test.systemUnderTest.Record;
import test.systemUnderTest.RecordDao;
import test.systemUnderTest.RecordService;
import test.systemUnderTest.SequenceGenerator;

@ExtendWith(MockitoExtension.class)
public class MockitoHelloTest {

    @Mock
    RecordDao mockDao;

    @Mock
    NotificationService mockNotification;

    @Mock
    SequenceGenerator mockGenerator;

    @InjectMocks
    RecordService service;

    @Test
    public void testSaveRecord() {

        Record record = new Record();
        record.setName("Test Record");

        when(mockGenerator.getNext()).thenReturn(100L);
        when(mockDao.saveRecord(record)).thenReturn(record);

        Record savedRecord = service.saveRecord(record);

        verify(mockGenerator, times(1)).getNext();
        verify(mockDao, times(1)).saveRecord(any(Record.class));

        assertEquals("Test Record", savedRecord.getName());
        assertEquals(100L, savedRecord.getId());
    }
}