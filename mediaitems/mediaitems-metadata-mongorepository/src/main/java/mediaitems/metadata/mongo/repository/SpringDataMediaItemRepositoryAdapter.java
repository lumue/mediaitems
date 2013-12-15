package mediaitems.metadata.mongo.repository;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.mongo.domain.MediaItem;
import mediaitems.metadata.mongo.domain.MediaItem.MongoMediaItemBuilder;
import mediaitems.metadata.repository.MediaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
class SpringDataMediaItemRepositoryAdapter implements MediaItemRepository {

	@Autowired(required = true)
	private MediaItemPagingAndSortingRepository delegate;

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

		final PageRequest pageRequest = createPageable(from, to);
		Page<MediaItem> resultPage = delegate.findAll(pageRequest);
		return resultPage.getContent();
	}

	private static PageRequest createPageable(Integer from, Integer to) {
		from = from == null ? 0 : from.intValue();
		to = to == null ? 0 : to.intValue();
		int size = (to - from);
		int page = from == 0 ? 1 : (from / size) + 1;
		final PageRequest pageRequest = new PageRequest(page, size);
		return pageRequest;
	}

	@Override
	public Iterable<? extends mediaitems.metadata.domain.MediaItem> getByMediaType(
			MediaType mediaType, Integer from, Integer to) {
		return delegate.findByMediaType(mediaType, createPageable(from, to))
				.getContent();
	}
}
