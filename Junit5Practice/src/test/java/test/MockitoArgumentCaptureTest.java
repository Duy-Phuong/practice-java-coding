package test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import test.systemUnderTest.NotificationService;
import test.systemUnderTest.Record;
import test.systemUnderTest.RecordDao;
import test.systemUnderTest.RecordService;
import test.systemUnderTest.SequenceGenerator;

@ExtendWith(MockitoExtension.class)
public class MockitoArgumentCaptureTest {

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Mock
    RecordDao mockDao;

    @Mock
    NotificationService mockNotification;

    @Mock
    SequenceGenerator mockGenerator;

    @InjectMocks
    RecordService service;

    @Test
    public final void shouldContainCertainListItem(@Mock List<String> mockedList) {
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        List<String> capturedArgument = captor.getValue();
        //        assertThat(capturedArgument, hasItem("someElement"));
        assertEquals("someElement_test", capturedArgument.get(0));
        assertEquals("someElement", capturedArgument.get(1));
    }

    @Test
    public void testSaveRecord() {

        Record record = new Record();
        record.setName("Test Record");

        when(mockGenerator.getNext()).thenReturn(100L);
        when(mockDao.saveRecord(record)).thenReturn(record);

        Record savedRecord = service.saveRecord(record);

        ArgumentCaptor<Record> requestArgumentCaptor =
                ArgumentCaptor.forClass(Record.class);

        // Check that mockGenerator.getNext() and saveRecord only 1 time
        //        verify(mockGenerator, times(1)).getNext();
        //        verify(mockDao, times(1)).saveRecord(any(Record.class));
        //
        //        assertEquals("Test Record", savedRecord.getName());
        //        assertEquals(100L, savedRecord.getId());

        // New record
        verify(mockDao, times(1)).saveRecord(requestArgumentCaptor.capture());
        assertEquals("Test Record", savedRecord.getName());
        assertEquals("Test Record", requestArgumentCaptor.getAllValues().get(0).getName());

    }

    @Test
    public void testSaveTwoRecords() {

        Record record = new Record();
        record.setName("Test2 Record");

        when(mockGenerator.getNext()).thenReturn(100L);
        when(mockDao.saveRecord(record)).thenReturn(record);

        service.saveTwoDuplicatedRecords(record);

        ArgumentCaptor<Record> requestArgumentCaptor = ArgumentCaptor.forClass(Record.class);

        // New record
        verify(mockDao, times(2)).saveRecord(requestArgumentCaptor.capture());
        assertEquals("Test2 Record", requestArgumentCaptor.getAllValues().get(0).getName());
        assertEquals("Test2 Record", requestArgumentCaptor.getAllValues().get(1).getName());
    }

    @Test
    public void testExceptionRecords() {
        Record record = new Record();
        record.setName("exception");
        doThrow(new NullPointerException("demo")).when(mockDao).updateRecord(refEq(record));
        assertThrows(
                NullPointerException.class,
                () -> service.updateExistingRecords(record));
    }

    @Test
    public void testThrowExceptionInTheFirstTime() {
        Record record = new Record();
        record.setName("exception");
        // Only throw exception for the first time
        doThrow(new NullPointerException("demo"))
                .doNothing()
                .when(mockDao).updateRecordToTestDoNothing(eq(1), refEq(record));


        service.updateExistingRecordsTwoTimes(record);

        ArgumentCaptor<Record> requestArgumentCaptor = ArgumentCaptor.forClass(Record.class);
        ArgumentCaptor<Integer> requestIntegerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

//        verify(mockDao, times(2)).updateRecordToTestDoNothing(anyInt(), requestArgumentCaptor.capture());
        verify(mockDao, times(2)).updateRecordToTestDoNothing(anyInt(), requestArgumentCaptor.capture());
//        verify(mockDao, times(2)).updateRecordToTestDoNothing(any(Integer.class), requestArgumentCaptor.capture());
        // The same result will be returned
//        verify(mockDao, times(2)).updateRecordToTestDoNothing(requestIntegerArgumentCaptor.capture(), requestArgumentCaptor.capture());
        assertEquals("exception", requestArgumentCaptor.getAllValues().get(0).getName());
//        assertEquals(1, requestIntegerArgumentCaptor.getAllValues().get(1));
    }
}
