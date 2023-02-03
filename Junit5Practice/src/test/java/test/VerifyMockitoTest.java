package test;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VerifyMockitoTest {
    @Test
    void test() {
        List<String> mockList = mock(List.class);
        mockList.add("Pankaj");
        mockList.size();

        verify(mockList).add("Pankaj");
        verify(mockList, times(1)).size();

        verify(mockList).add(anyString());
        verify(mockList).add(any(String.class));
        verify(mockList).add(ArgumentMatchers.any(String.class));
    }
}
