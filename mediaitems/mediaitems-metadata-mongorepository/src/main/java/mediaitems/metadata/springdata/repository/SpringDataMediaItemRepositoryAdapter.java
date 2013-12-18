package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.repository.MediaItemRepository;
import mediaitems.metadata.springdata.domain.MediaItem;
import mediaitems.metadata.springdata.domain.MediaItem.MongoMediaItemBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
class SpringDataMediaItemRepositoryAdapter implements MediaItemRepository {

	@Autowired(required = true)
	private SpringDataMediaItemRepository delegate;

	@Override
	public MediaItem create(
			Builder<? extends mediaitems.metadata.domain.MediaItem> builder) {
		MongoMediaItemBuilder mybuilder = (MongoMediaItemBuilder) builder;
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

	@Override
	public Iterable<? extends mediaitems.metadata.domain.MediaItem> getAll(
			Integer from, Integer to) {

		final PageRequest pageRequest = RepositoryUtil.createPageable(from, to);
		Page<MediaItem> resultPage = delegate.findAll(pageRequest);
		return resultPage.getContent();
	}

	@Override
	public Iterable<? extends mediaitems.metadata.domain.MediaItem> getByMediaType(
			MediaType mediaType, Integer from, Integer to) {
		return delegate.findByMediaType(mediaType,
				RepositoryUtil.createPageable(from, to)).getContent();
	}
}
