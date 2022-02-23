package com.example.graphtuto;

import com.example.graphtuto.entities.PersonEntity;
import com.example.graphtuto.entities.TripEntity;
import com.example.graphtuto.repositories.PersonRepository;
import com.example.graphtuto.repositories.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class GraphtutoApplication {

	private final static Logger log = LoggerFactory.getLogger(GraphtutoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GraphtutoApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository, TripRepository tripRepository) {
		return args -> {

			log.info("Clean up database ...");
			personRepository.deleteAll();
			tripRepository.deleteAll();

			log.info("Saving people ...");
			PersonEntity personA = new PersonEntity("Person A");
			PersonEntity personB = new PersonEntity("Person B");
			PersonEntity personC = new PersonEntity("Person C");
			PersonEntity personD = new PersonEntity("Person D");
			personRepository.save(personA);
			personRepository.save(personB);
			personRepository.save(personC);
			personRepository.save(personD);

			log.info("Saving trip ...");
			TripEntity parisTrip = new TripEntity("Paris", "Pris trip");
			TripEntity londonTrip = new TripEntity("London", "London trip");
			List<TripEntity> destinations = Arrays.asList(parisTrip, londonTrip);
			tripRepository.save(parisTrip);
			tripRepository.save(londonTrip);


			log.info("Build relationship ...");
			londonTrip = tripRepository.findByTitle(londonTrip.getTitle());
			londonTrip.organizedBy(personA);
			londonTrip.participatedBy(personB);
			londonTrip.participatedBy(personC);
			tripRepository.save(londonTrip);

			parisTrip = tripRepository.findByTitle(parisTrip.getTitle());
			parisTrip.organizedBy(personD);
			parisTrip.participatedBy(personA);
			tripRepository.save(parisTrip);

			log.info("Lookup each trip by title...");
			destinations.forEach(trip -> log.info("\t Trip info: {}", tripRepository.findByTitle(trip.getTitle())));
		};
	}
}
