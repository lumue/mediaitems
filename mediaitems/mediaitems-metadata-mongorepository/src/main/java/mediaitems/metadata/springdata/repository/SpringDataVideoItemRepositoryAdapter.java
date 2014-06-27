package mediaitems.metadata.springdata.repository;

import mediaitems.common.domain.api.Builder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;
import mediaitems.metadata.repository.VideoItemRepository;
import mediaitems.metadata.springdata.domain.TagImpl;
import mediaitems.metadata.springdata.domain.VideoItemImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
class SpringDataVideoItemRepositoryAdapter implements
 VideoItemRepository<VideoItemImpl> {

	@Autowired(required = true)
	private SpringDataVideoItemRepository delegate;

	

	@Override
	public VideoItemImpl get(String key) {
		return delegate.findOne(key);
	}

	@Override
	public Iterable<VideoItemImpl> getAll() {
		return delegate.findAll();
	}

	@Override
	public void deleteAll() {
		delegate.deleteAll();
	}

	@Override
	public Iterable<VideoItemImpl> getAll(
			Integer from, Integer to) {

		final PageRequest pageRequest = RepositoryUtil.createPageable(from, to);
		Page<VideoItemImpl> resultPage = delegate.findAll(pageRequest);
		return resultPage.getContent();
	}

	@Override
	public Iterable<VideoItemImpl> getByMediaType(
			MediaType mediaType, Integer from, Integer to) {
		return this.getAll(from, to);
	}

	@Override
	public Iterable<VideoItemImpl> getByTag(
			Tag tag, Integer from, Integer to) {
		return delegate.findByTags((TagImpl) tag,
				RepositoryUtil.createPageable(from, to)).getContent();
	}

	@Override
	public VideoItemImpl.VideoItemBuilderImpl createNewBuilder() {
		return new VideoItemImpl.VideoItemBuilderImpl();
	}





	@Override
	public VideoItemImpl create(Builder<VideoItemImpl> builder) {
		return delegate.save(builder.build());
	}


}
