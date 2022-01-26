package ToDoApp.controller;

import ToDoApp.entity.ToDo;
import ToDoApp.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getToDos() {
        return new ResponseEntity<>(
                toDoService.getToDos(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTodo(@PathVariable Integer id) throws Throwable {
        return new ResponseEntity<>(
                toDoService.getTodo(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ToDo> addTodo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(
                toDoService.addTodo(todo),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo todo) {
        return new ResponseEntity<>(
                toDoService.updateToDo(todo),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public void deleteToDo(@RequestParam Integer id) {
        toDoService.deleteToDo(id);
    }
}
