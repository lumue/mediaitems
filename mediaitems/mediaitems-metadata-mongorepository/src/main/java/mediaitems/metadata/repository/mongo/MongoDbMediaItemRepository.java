package mediaitems.metadata.repository.mongo;

import mediaitems.metadata.domain.mongo.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDbMediaItemRepository extends
		MongoRepository<MediaItem, String> {

}
