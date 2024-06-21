package com.example.ToDo_Spring.services;

import com.example.ToDo_Spring.models.Tasks;
import com.example.ToDo_Spring.models.Users;
import com.example.ToDo_Spring.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Tasks findById(Long id){
        Optional<Tasks> task =  this.taskRepository.findById(id);
        return task.orElseThrow(
                () -> new RuntimeException(
                        "Tarefa não Encontrada! id: " + id + ", tipo: " + Tasks.class.getName()
                )
        );
    }

    public List<Tasks> findAllByUserId(Long userId){
        List<Tasks> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Tasks create(Tasks task){
        Users user = this.userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);
        return task;
    }

    @Transactional
    public Tasks update(Tasks obj){
        Tasks newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(
                    "Não é possivel excluir pois há entidades relacionadas!"
            );
        }
    }
}
