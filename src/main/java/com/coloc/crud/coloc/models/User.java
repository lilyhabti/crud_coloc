package com.coloc.crud.coloc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long idUser;
    private String username;
    private String email;
    private String password;
    private String profilePic;

    @ManyToOne
    @JoinColumn(name="flatShareCol_id")
    private FlatShare flatShareColocs;

    @JsonIgnore
    public FlatShare getFlatShareColocs() {
        return flatShareColocs;
    }


}
