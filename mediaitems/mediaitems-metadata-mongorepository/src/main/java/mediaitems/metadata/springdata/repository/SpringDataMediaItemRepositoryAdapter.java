package mediaitems.metadata.springdata.repository;

import mediaitems.common.domain.api.Builder;
import mediaitems.metadata.domain.MediaItem.MediaItemBuilder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;
import mediaitems.metadata.repository.MediaItemRepository;
import mediaitems.metadata.springdata.domain.MediaItemImpl;
import mediaitems.metadata.springdata.domain.TagImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository("mediaItemRepository")
class SpringDataMediaItemRepositoryAdapter implements MediaItemRepository<MediaItemImpl> {

	@Autowired(required = true)
	private SpringDataMediaItemRepository delegate;



	@Override
	public MediaItemImpl get(String key) {
		return delegate.findOne(key);
	}

	@Override
	public Iterable<MediaItemImpl> getAll() {
		return delegate.findAll();
	}
	@Override
	public void deleteAll() {
		delegate.deleteAll();
	}

	@Override
	public Iterable<MediaItemImpl> getAll(
			Integer from, Integer to) {

		final PageRequest pageRequest = RepositoryUtil.createPageable(from, to);
		Page<MediaItemImpl> resultPage = delegate.findAll(pageRequest);
		return resultPage.getContent();
	}

	@Override
	public Iterable<MediaItemImpl> getByMediaType(
			MediaType mediaType, Integer from, Integer to) {
		return delegate.findByMediaType(mediaType,
				RepositoryUtil.createPageable(from, to)).getContent();
	}

	@Override
	public Iterable<MediaItemImpl> getByTag(
			Tag tag, Integer from, Integer to) {
		return delegate.findByTags((TagImpl) tag,
				RepositoryUtil.createPageable(from, to)).getContent();
	}

	@Override
	public MediaItemBuilder<MediaItemImpl> createNewBuilder() {
		return (new MediaItemImpl.MongoMediaItemBuilder());
	}


	@Override
	public MediaItemImpl create(Builder<MediaItemImpl> builder) {
		return delegate.save(builder.build());
	}


}
