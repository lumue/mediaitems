package mediaitems.metadata.repository.mongo;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.mongo.MediaItem;
import mediaitems.metadata.domain.mongo.MediaItem.MongoMediaItemBuilder;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class MongoMediaItemRepository implements MongoRepository<MediaItem, String>,mediaitems.metadata.repository.MediaItemRepository {

	
	

	@Override
	public MediaItem create(
			Builder<? extends mediaitems.metadata.domain.MediaItem> builder) {
		MongoMediaItemBuilder mybuilder=(MongoMediaItemBuilder) builder;
		return save(mybuilder.build());
	}

	@Override
	public MediaItem get(String key) {
		return findOne(key);
	}

	@Override
	public Iterable<MediaItem> getAll() {
		return findAll();
	}

	@Override
	public MediaItem.MongoMediaItemBuilder createNewBuilder() {
		return new MediaItem.MongoMediaItemBuilder();
	}

	
		
}
