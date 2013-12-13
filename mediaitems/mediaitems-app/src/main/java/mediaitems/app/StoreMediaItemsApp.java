package mediaitems.app;

import mediaitems.metadata.domain.mongo.ContentLocation;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.repository.mongo.MongoMediaItemRepository;
import mediaitems.sources.api.ContentDescription;
import mediaitems.sources.api.ContentHandle;
import mediaitems.sources.api.ContentSource;
import mediaitems.sources.filesystem.local.LocalFileSystemContentSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages={"mediaitems"})
@EnableAutoConfiguration
@EnableMongoRepositories
public class StoreMediaItemsApp implements CommandLineRunner {

	@Autowired
	private MongoMediaItemRepository repository;

	
	public static void main(String[] args) {
		SpringApplication.run(StoreMediaItemsApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ContentSource location=new LocalFileSystemContentSource(null, null, "/home/lm");
		
		repository.deleteAll();
		long count=0;
		
		for (ContentHandle contentHandle : location) {
			ContentDescription description = contentHandle.getDescription();
			repository.create(repository.createNewBuilder().setName(description.getName()).setMediaType(MediaType.forMimeType(description.getMimeType())).setContentLocation(new ContentLocation(contentHandle.getLocationIdentifier())));
			System.out.println(count);
			count++;
		}
		System.out.println("fertig");
	}

}