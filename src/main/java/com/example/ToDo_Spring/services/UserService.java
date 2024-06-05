package com.example.ToDo_Spring.services;

import com.example.ToDo_Spring.models.Users;
import com.example.ToDo_Spring.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users findById(Long id){
        Optional<Users> user = this.userRepository.findById(id);
        return user.orElseThrow((() -> new RuntimeException(
                "Usuario não encontrado! id: " + id + ", tipo: " + Users.class.getName()
        )));
    }

    @Transactional
    public Users create(Users obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public Users update(Users obj){
        Users newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas!");
        }
    }
}
