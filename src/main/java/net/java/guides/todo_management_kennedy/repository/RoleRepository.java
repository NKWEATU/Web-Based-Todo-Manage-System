package net.java.guides.todo_management_kennedy.repository;

import net.java.guides.todo_management_kennedy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

