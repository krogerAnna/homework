package oma_projekti.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import oma_projekti.homework.domain.Course;
import oma_projekti.homework.domain.CourseRepository;
import oma_projekti.homework.domain.Homework;
import oma_projekti.homework.domain.HomeworkRepository;
import oma_projekti.homework.domain.Student;
import oma_projekti.homework.domain.StudentRepository;

@SpringBootApplication
public class HomeworkApplication {
	
	private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}
	
	//testidatan luonti
	@Bean
	public CommandLineRunner homeworkDemo(HomeworkRepository homeRepo, CourseRepository couRepo, StudentRepository studentRepo) {
		return (args) -> {
			log.info("SAVE A COUPLE OF COURSES");
			
			Course kurssi1 = new Course("Palvelinohjelmointi");
			Course kurssi2 = new Course("Frontti");
			Course kurssi3 = new Course("Matikka");
			
			couRepo.save(kurssi1);
			couRepo.save(kurssi2);
			couRepo.save(kurssi3);
			
			log.info("SAVE A LITTLE HOMEWORK");
			homeRepo.save(new Homework("Tehtävä 1", "26.12.", kurssi1));
			homeRepo.save(new Homework("Tehtävä 2", "10.11", kurssi2));
			homeRepo.save(new Homework("Tehtävä 3", "31.12", kurssi3));
			
			//Creating students --> password always same as username
			Student student1 = new Student("student1", "$2a$10$o.UUfisnRlr5fk0Oe6ARbOrkLlsqPXLFq46KQw9prpBFOLe.hDsbC","USER");
			Student student2 = new Student("student2","$2a$10$0Yg87yDoC955PyFDOJKFi.1zLoHz7T4VeguX/fZWOk6NrBo44yo2K", "USER");
			Student student3 = new Student("studentadmin", "$2a$10$8iK7UJEXZoG4Yga9hTj4j.HZjgwVqI7IuOhmMB9J22BO18P97iWcS", "ADMIN");
			
			studentRepo.save(student1);
			studentRepo.save(student2);
			studentRepo.save(student3);
			
			
			log.info("FETCH EVERYTHING");
			for (Homework homework: homeRepo.findAll()) {
				log.info(homework.toString());
			}
			
		};
	}

}
