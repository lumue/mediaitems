package mediaitems.data.repository.mongo;

import mediaitems.data.domain.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaItemRepository extends MongoRepository<MediaItem, String> {

	
		
}
