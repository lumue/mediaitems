package mediaitems.metadata.springdata.repository;

import mediaitems.common.domain.api.Builder;
import mediaitems.metadata.repository.TagRepository;
import mediaitems.metadata.springdata.domain.TagImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class SpringDataTagRepositoryAdapter implements TagRepository<TagImpl> {

	private final SpringDataTagRepository delegate;

	@Autowired(required = true)
	public SpringDataTagRepositoryAdapter(SpringDataTagRepository delegate) {
		this.delegate = delegate;
	}

	@Override
	public TagImpl create(Builder<TagImpl> builder) {
		return delegate.save(builder.build());
	}

	@Override
	public TagImpl get(String key) {
		return delegate.findOne(key);
	}

	@Override
	public Iterable<TagImpl> getAll() {
		return delegate.findAll();
	}

	@Override
	public Iterable<TagImpl> getAll(Integer from, Integer to) {
		final PageRequest pageRequest = RepositoryUtil.createPageable(from, to);
		Page<TagImpl> resultPage = delegate.findAll(pageRequest);
		return resultPage.getContent();
	}

	@Override
	public void deleteAll() {
		delegate.deleteAll();
	}

	@Override
	public mediaitems.metadata.repository.TagBuilder<TagImpl> createNewBuilder() {
		return new TagImpl.TagImplBuilder();
	}

}
