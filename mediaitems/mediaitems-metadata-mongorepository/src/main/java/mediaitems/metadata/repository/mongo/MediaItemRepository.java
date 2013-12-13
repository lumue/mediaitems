package mediaitems.metadata.repository.mongo;

import mediaitems.metadata.domain.mongo.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends MongoRepository<MediaItem, String> {

	
		
}
