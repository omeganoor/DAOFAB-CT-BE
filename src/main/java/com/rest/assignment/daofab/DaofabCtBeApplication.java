package com.rest.assignment.daofab;

import com.rest.assignment.daofab.entity.Child;
import com.rest.assignment.daofab.migration.ChildMigration;
import com.rest.assignment.daofab.migration.ParentMigration;
import com.rest.assignment.daofab.repository.ChildRepository;
import com.rest.assignment.daofab.repository.ParentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DaofabCtBeApplication {

	private static ParentRepository parentRepository;
	private static ChildRepository childRepository;

	public DaofabCtBeApplication(ParentRepository parentRepository, ChildRepository childRepository) {
		this.parentRepository = parentRepository;
		this.childRepository = childRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DaofabCtBeApplication.class, args);
		ParentMigration parentMigration = new ParentMigration(parentRepository);
		parentMigration.migration();
		ChildMigration childMigration = new ChildMigration(childRepository);
		childMigration.migration();
	}

}
