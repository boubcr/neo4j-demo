package com.example.graphtuto.entities;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Data
@Node("Trip")
public class TripEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Property("tagline")
    private String description;

    @Relationship(type = "PARTICIPATED_BY", direction = INCOMING)
    private Set<PersonEntity> participants = new HashSet<>();

    @Relationship(type = "ORGANIZED_BY", direction = INCOMING)
    private Set<PersonEntity> organizers = new HashSet<>();

    public TripEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void participatedBy(PersonEntity person) {
        if (participants == null) {
            participants = new HashSet<>();
        }
        participants.add(person);
    }

    public void organizedBy(PersonEntity person) {
        if (organizers == null) {
            organizers = new HashSet<>();
        }
        organizers.add(person);
    }
}