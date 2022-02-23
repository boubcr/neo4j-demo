package com.example.graphtuto.repositories;

import com.example.graphtuto.entities.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {
    PersonEntity findByName(String name);
}
