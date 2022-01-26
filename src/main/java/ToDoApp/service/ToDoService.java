package ToDoApp.service;

import ToDoApp.entity.ToDo;
import ToDoApp.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public ToDo getTodo(Integer id) throws Throwable {
        return (ToDo) toDoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public ToDo addTodo(ToDo todo) {
        return (ToDo) toDoRepository.save(todo);
    }

    public ToDo updateToDo(ToDo todo) {
        return (ToDo) toDoRepository.save(todo);
    }

    public void deleteToDo(Integer id) {
        toDoRepository.deleteById(id);
    }

}
