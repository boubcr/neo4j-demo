package com.example.graphtuto.entities;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("Person")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public PersonEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
