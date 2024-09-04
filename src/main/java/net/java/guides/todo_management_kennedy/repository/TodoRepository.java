package net.java.guides.todo_management_kennedy.repository;

import net.java.guides.todo_management_kennedy.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
