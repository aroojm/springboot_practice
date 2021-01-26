package com.practice.todotasks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private static final String TASK_DESCRIPTION_INCOMPLETE_MESSAGE = "Task 4 description is incomplete.";

    public TaskController() {
        tasks.add(new Task(1,"Doctor","call mr Doctor",true));
        tasks.add(new Task(2, "Yoga class","pick up friend",false));
        tasks.add(new Task(3,"post the letter", "buy stamps",false));
        tasks.add(new Task(4,"","",false));
        tasks.add(new Task(5,"shopping", "pick up grocery",false));
    }
    @PostMapping(path = "/tasks")
    public void addTask(@RequestBody Task task){
        tasks.add(task);
    }
    @GetMapping(path = "/tasks/{id}")
    public Task getTask(@PathVariable int id){
        if (Objects.equals(tasks.get(id - 1).getDescription(),"")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, TASK_DESCRIPTION_INCOMPLETE_MESSAGE);
        }
        return tasks.get(id - 1);
    }

    // moving handler to Exception handler class

//    private static final String NOT_FOUND_MESSAGE = "Task not found for the number.";
//
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(code = HttpStatus.NOT_FOUND)
//    public HashMap<String, String> handleIndexOutOfBoundsException (Exception e) {
//        HashMap<String, String> response = new HashMap<>();
//        response.put("message", NOT_FOUND_MESSAGE);
//        response.put("error", e.getClass().getSimpleName());
//        return response;
//    }

}

