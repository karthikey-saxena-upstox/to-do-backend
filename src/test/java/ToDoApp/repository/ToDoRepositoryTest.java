package ToDoApp.repository;

import ToDoApp.entity.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ToDoRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ToDoRepository toDoRepository;

    private ToDo todo;

    @Test
    @Transactional
    public void testFindAll() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = testEntityManager.persist(todo);
        List<ToDo> todos = toDoRepository.findAll();
        assertEquals(1, todos.size());
    }

    @Test
    @Transactional
    public void testFindById() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = testEntityManager.persist(todo);
        ToDo fetchedToDo = toDoRepository.findById(savedToDo.getId()).orElseThrow();
        assertEquals(savedToDo, fetchedToDo);
    }

    @Test
    @Transactional
    public void testSave() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = toDoRepository.save(todo);
        ToDo fetchedToDo = testEntityManager.find(ToDo.class, savedToDo.getId());
        assertNotNull(fetchedToDo);
        assertEquals(fetchedToDo, savedToDo);
    }

    @Test
    @Transactional
    public void testUpdate() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = testEntityManager.persist(todo);
        savedToDo.setDescription("desc1");
        ToDo updatedToDo = toDoRepository.save(savedToDo);
        ToDo fetchedToDo = testEntityManager.find(ToDo.class, savedToDo.getId());
        assertEquals(updatedToDo, fetchedToDo);
    }

    @Test
    @Transactional
    public void testDelete() {
        todo = new ToDo("title", "desc");
        ToDo savedToDo = testEntityManager.persist(todo);
        toDoRepository.deleteById(savedToDo.getId());
        ToDo fetchedToDo = testEntityManager.find(ToDo.class, savedToDo.getId());
        assertNull(fetchedToDo);
    }




}