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
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long idCat;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses = new ArrayList<>();;
    @ManyToOne
    @JoinColumn(name="flatShare_id")
    private FlatShare flatShareCate;

    @JsonIgnore
    public FlatShare getFlatShareCate() {
        return flatShareCate;
    }
}
