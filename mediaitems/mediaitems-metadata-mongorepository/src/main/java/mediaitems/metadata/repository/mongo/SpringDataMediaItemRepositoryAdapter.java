package mediaitems.metadata.repository.mongo;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.mongo.MediaItem;
import mediaitems.metadata.domain.mongo.MediaItem.MongoMediaItemBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;


@Component
public class SpringDataMediaItemRepositoryAdapter implements mediaitems.metadata.repository.MediaItemRepository {

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
