package com.practice.todolist;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1,"Dentist","call mr Anderson",true));
        tasks.add(new Task(2, "Dance class","pick up Simon",false));
        tasks.add(new Task(3,"post the letter", "buy stamps",false));
    }
    @PostMapping(path = "/tasks")
    public void addTask(@RequestBody Task task){
        tasks.add(task);
    }
    @GetMapping(path = "/tasks/{id}")
    public Task getTask(@PathVariable int id){
        return tasks.get(id - 1);
    }

    private static final String NOT_FOUND_MESSAGE = "Task not found for the number.";

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleIndexOutOfBoundsException (Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

}
