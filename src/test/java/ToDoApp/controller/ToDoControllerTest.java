package ToDoApp.controller;

import ToDoApp.entity.ToDo;
import ToDoApp.service.ToDoService;
import ToDoApp.util.JsonUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @SneakyThrows
    @Test
    void getToDos() {

        ToDo todo = new ToDo("title", "desc");
        List<ToDo> todos = List.of(todo);
        Mockito.when(toDoService.getToDos()).thenReturn(todos);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].description").value("desc"));

    }

    @Test
    void getTodo() throws Throwable {

        ToDo todo = new ToDo(1, "title", "desc");
        Mockito.when(toDoService.getTodo(1)).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/todos/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").value(todo));
    }

    @Test
    void addTodo() throws Exception {

        ToDo todo = new ToDo("title", "desc");
        ToDo savedToDo = new ToDo(1, "title", "desc");
        Mockito.when(toDoService.addTodo(todo)).thenReturn(savedToDo);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(JsonUtil.toJson(todo)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").value(savedToDo));


    }

    @Test
    void updateToDo() throws Exception {

        ToDo todo = new ToDo(1, "title", "desc");
        Mockito.when(toDoService.updateToDo(todo)).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(JsonUtil.toJson(todo)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").value(todo));

    }

    @Test
    void deleteToDo() throws Exception {

        ToDo todo = new ToDo(1, "title", "desc");
        Mockito.doNothing().when(toDoService).deleteToDo(todo.getId());
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/todos?id=" + todo.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
}