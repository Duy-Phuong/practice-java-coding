package test;

// Java Program to Illustrate TodoServiceImplMockTest File

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Importing required classes
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// MockitoJUnitRunner gives automatic validation
// of framework usage, as well as an automatic initMocks()
// Only works in Junit 4
//@RunWith(MockitoJUnitRunner.class)

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

    @InjectMocks
    TodoServiceImpl todoBusiness;

    // The Mockito.mock() method allows us to
    // create a mock object of a class or an interface
    @Mock TodoService todoServiceMock;

    // Methods annotated with the @Before
    // annotation are run before each test
//    We use @InjectMocks so we don't have to manually create the instance
//    @BeforeEach
//    public void setUp()
//    {
//        todoBusiness = new TodoServiceImpl(todoServiceMock);
//    }

    // @Test
    // Tells the JUnit that the public void method
    // in which it is used can run as a test case
    @Test
    public void testRetrieveTodosRelatedToSpring_usingMock()
    {
        List<String> todos
                = Arrays.asList("Learn Spring", "Lear Java",
                "Learn Spring Boot");
        when(todoServiceMock.retrieveTodos("User"))
                .thenReturn(todos);

        List<String> filteredTodos
                = todoBusiness.retrieveTodosRelatedToJava(
                "User");
        assertEquals(1, filteredTodos.size());
    }

    @Test
    public void
    testRetrieveTodosRelatedToSpring_withEmptyList_usingMock()
    {
        List<String> todos = Arrays.asList();
        when(todoServiceMock.retrieveTodos("Dummy"))
                .thenReturn(todos);

        List<String> filteredTodos
                = todoBusiness.retrieveTodosRelatedToJava(
                "Dummy");
        assertEquals(0, filteredTodos.size());
    }
}
