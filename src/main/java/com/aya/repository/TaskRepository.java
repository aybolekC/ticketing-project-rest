package com.aya.repository;

import com.aya.entity.Project;
import com.aya.entity.Task;
import com.aya.entity.User;
import com.aya.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    //derive query will not work here because we need to use JOINS to pull data from other tables as well

    @Query("SELECT COUNT (t) FROM Task t WHERE t.project.projectCode=?1 AND t.taskStatus <> 'COMPLETE' ")
    int totalNonCompletedTasks(String projectCode);

    @Query(value = "SELECT COUNT (*) " +
            "FROM tasks t JOIN projects p on t.project_id =p.id " +
            "WHERE p.project_code=?1 AND t.task_status = 'COMPLETE' ", nativeQuery = true)
    int totalCompletedTasks(String projectCode);


    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User assignedEmployee);

    List<Task> findAllByTaskStatusIsAndAssignedEmployee(Status status, User assignedEmployee);

    List<Task> findAllByAssignedEmployee(User assignedEmployee);
}
