package eci.ieti.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Todos")
public class Todo {

    @Id
    private String id;

    private String description;

    private int priority;

    private String dueDate;

    private String responsible;

    private String status;

    public Todo(String description, int priority, String dueDate, String responsible, String status){
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.responsible = responsible;
        this.status = status;
    }
}
