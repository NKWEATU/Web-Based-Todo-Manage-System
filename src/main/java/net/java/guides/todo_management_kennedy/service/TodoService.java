package net.java.guides.todo_management_kennedy.service;

import net.java.guides.todo_management_kennedy.dto.TodoDto;
import net.java.guides.todo_management_kennedy.entity.Todo;

import java.util.List;

public interface TodoService {


    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto,Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);

}
