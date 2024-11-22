package com.jobPortal.jobPortal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "admin")
public class Admin {
    @Id
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    List<Job> jobs;
}
