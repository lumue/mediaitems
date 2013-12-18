package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.springdata.domain.MediaItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDbMediaItemRepository extends
		MongoRepository<MediaItem, String> {

}
