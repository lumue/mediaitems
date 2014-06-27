package mediaitems.metadata.repository;

import mediaitems.common.repository.api.ObjectRepository;
import mediaitems.metadata.domain.Tag;

public interface TagRepository<T extends Tag> extends ObjectRepository<T> {
	@Override
	public TagBuilder<T> createNewBuilder();

}
