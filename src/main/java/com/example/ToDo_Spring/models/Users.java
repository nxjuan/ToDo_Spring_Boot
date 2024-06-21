package com.example.ToDo_Spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {
    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", length = 100)
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    @Size(min = 6, max = 100)
    private String email;


    @Column(name = "password", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Tasks> tasks = new ArrayList<>();

}
