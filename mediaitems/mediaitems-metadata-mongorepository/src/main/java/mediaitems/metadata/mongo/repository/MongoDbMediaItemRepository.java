package mediaitems.metadata.mongo.repository;

import mediaitems.metadata.mongo.domain.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDbMediaItemRepository extends
		MongoRepository<MediaItem, String> {

}
