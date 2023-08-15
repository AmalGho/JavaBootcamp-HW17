package com.example.usermanagementsoftware.Model;

import com.example.usermanagementsoftware.Repository.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Length(min = 5 , message = "name length must be more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username cannot be empty")
    @Length(min = 5)
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "enter strong password!!")
    @Column(columnDefinition = "varchar(30) not null unique")
    private  String email;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "\\W*((?i)user|admin(?-i))\\W*", message = "role should be admin or user only")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "age cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer age;


}
