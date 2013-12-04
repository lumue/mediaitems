package mediaitems.app;

import mediaitems.data.domain.ContentLocation;
import mediaitems.data.domain.MediaItem;
import mediaitems.data.domain.MediaType;
import mediaitems.data.repository.mongo.MediaItemRepository;

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

		
		MediaItem mediaItem = new MediaItem("Test",MediaType.DOCUMENT,new ContentLocation("file://home/lm/testfile.txt"));
		repository.save(mediaItem);

		for (MediaItem item : repository.findAll()) {
			System.out.println(item.getKey()+" "+item.getName()+" "+item.getContentLocation().getUrl());
		}
		System.out.println();

		
	}

}