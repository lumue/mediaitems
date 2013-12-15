package mediaitems.metadata.mongo.repository;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.mongo.domain.MediaItem;
import mediaitems.metadata.mongo.domain.MediaItem.MongoMediaItemBuilder;
import mediaitems.metadata.repository.MediaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataMediaItemRepositoryAdapter implements MediaItemRepository {

	@Autowired(required=true)
	private PagingAndSortingRepository<MediaItem, String> delegate;
	

	@Override
	public MediaItem create(
			Builder<? extends mediaitems.metadata.domain.MediaItem> builder) {
		MongoMediaItemBuilder mybuilder=(MongoMediaItemBuilder) builder;
		return delegate.save(mybuilder.build());
	}

	@Override
	public MediaItem get(String key) {
		return delegate.findOne(key);
	}

	@Override
	public Iterable<MediaItem> getAll() {
		return delegate.findAll();
	}

	@Override
	public MediaItem.MongoMediaItemBuilder createNewBuilder() {
		return new MediaItem.MongoMediaItemBuilder();
	}

	@Override
	public void deleteAll() {
		delegate.deleteAll();
	}

	
		
}
