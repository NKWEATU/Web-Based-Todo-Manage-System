package net.java.guides.todo_management_kennedy.controller;

import lombok.AllArgsConstructor;
import net.java.guides.todo_management_kennedy.dto.TodoDto;
import net.java.guides.todo_management_kennedy.entity.Todo;
import net.java.guides.todo_management_kennedy.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Build add todo REST API
    @PreAuthorize("hasRole('ADMIN')")//this annotation provides method level security for the ADMIN
    //which simply means only the admin can add to Todo management system the REST API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

       TodoDto savedTodo = todoService.addTodo(todoDto);

       return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build get todo REST API
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")//Gives access to both ADMIN and USER
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){

      TodoDto todoDto =  todoService.getTodo(todoId);

      return  new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")//Gives access to both ADMIN and USER
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
      List<TodoDto> todos =  todoService.getAllTodos();
     // return new ResponseEntity<>(todos, HttpStatus.OK);
        return ResponseEntity.ok(todos);
    }

    //Build UpdateTodo REST API
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")//it means only the ADMIN can be able to update the REST API
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")//it means only the ADMIN can be able to delete the REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    //Build complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")//Gives access to both ADMIN and USER
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
       TodoDto updatedTodo =  todoService.completeTodo(todoId);
       return ResponseEntity.ok(updatedTodo);
    }

    //Build incomplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")//Gives access to both ADMIN and USER
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){
       TodoDto updatedTodo = todoService.incompleteTodo(todoId);
       return ResponseEntity.ok(updatedTodo);
    }
}
