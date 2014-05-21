package mediaitems.metadata.repository;

import mediaitems.common.repository.api.ObjectRepository;
import mediaitems.metadata.domain.Tag;

public interface TagRepository extends ObjectRepository<Tag> {
	@Override
	public TagBuilder<? extends Tag> createNewBuilder();

}
