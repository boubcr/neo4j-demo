package com.example.graphtuto.repositories;

import com.example.graphtuto.entities.TripEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface TripRepository extends Neo4jRepository<TripEntity, Long> {
    TripEntity findByTitle(String title);
    List<TripEntity> findByParticipantsName(String name);
    List<TripEntity> findByOrganizersName(String name);
}
