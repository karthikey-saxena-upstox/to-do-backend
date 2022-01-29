package ToDoApp.service;

import ToDoApp.entity.ToDo;
import ToDoApp.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest extends BaseServiceTest {

    @Autowired
    private ToDoService toDoService;

    @MockBean
    private ToDoRepository toDoRepository;

    private ToDo todo;

    @Test
    void getToDos() {
        todo = new ToDo("title", "desc");
        Mockito.when(toDoRepository.findAll()).thenReturn(List.of(todo));
        List<ToDo> todos = toDoService.getToDos();
        assertEquals(1, todos.size());
    }

    @Test
    void getTodo() throws Throwable {
        todo = new ToDo(1, "title", "desc");
        Mockito.when(toDoRepository.findById(1)).thenReturn(Optional.ofNullable(todo));
        ToDo fetchedToDo = toDoService.getTodo(1);
        assertNotNull(fetchedToDo);
        assertEquals(fetchedToDo, todo);
    }

    @Test
    void addTodo() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = new ToDo(1, "title", "desc");
        Mockito.when(toDoRepository.save(todo)).thenReturn(savedToDo);
        ToDo fetchedToDo = toDoService.addTodo(todo);
        Mockito.verify(toDoRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ToDo.class));
        assertEquals(fetchedToDo, savedToDo);
    }

    @Test
    void deleteToDo() {
        ToDo todo = new ToDo(1, "title", "desc");
        Mockito.doNothing().when(toDoRepository).deleteById(todo.getId());
        toDoService.deleteToDo(todo.getId());
        Mockito.verify(toDoRepository).deleteById(ArgumentMatchers.any(Integer.class));
        Mockito.verify(toDoRepository, Mockito.times(1))
                .deleteById(ArgumentMatchers.any(Integer.class));
    }
}