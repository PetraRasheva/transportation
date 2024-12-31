package com.project.transportation;

import com.project.transportation.cache.QualificationCache;
import com.project.transportation.model.Qualification;
import com.project.transportation.repository.QualificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadQualifications(QualificationRepository qualificationRepository) {
		return args -> {
			List<Qualification> qualifications = qualificationRepository.findAll();
			QualificationCache.loadQualificationsIntoCache(qualifications);
		};
	}
}
