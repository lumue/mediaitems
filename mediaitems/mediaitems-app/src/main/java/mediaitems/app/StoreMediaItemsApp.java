package mediaitems.app;

import mediaitems.data.repository.mongo.MediaItemRepository;
import mediaitems.metadata.domain.ContentLocation;
import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaType;
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
	private MediaItemRepository repository;

	
	public static void main(String[] args) {
		SpringApplication.run(StoreMediaItemsApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ContentSource location=new LocalFileSystemContentSource(null, null, "/home/lm/downloads");
		
		repository.deleteAll();
		
		for (ContentHandle contentHandle : location) {
			ContentDescription description = contentHandle.getDescription();
			repository.save(new MediaItem(description.getName(), MediaType.forMimeType(description.getMimeType()), new ContentLocation(contentHandle.getLocationIdentifier())));
		}
	}

}