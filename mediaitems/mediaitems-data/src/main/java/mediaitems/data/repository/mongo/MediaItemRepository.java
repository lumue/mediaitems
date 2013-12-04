package mediaitems.data.repository.mongo;

import mediaitems.data.domain.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends MongoRepository<MediaItem, String> {

	
		
}
