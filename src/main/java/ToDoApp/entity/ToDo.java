package ToDoApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "todos")
@Data
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
