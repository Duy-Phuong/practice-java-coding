package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MockitoTest
{
    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        Mockito.verify(mockList).add("one");
        System.out.println(mockList.size());
        assertEquals(0, mockList.size());

        Mockito.when(mockList.size()).thenReturn(100);
        System.out.println(mockList.size());
        assertEquals(100, mockList.size());
    }
}