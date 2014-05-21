package mediaitems.metadata.springdata.repository;

import org.springframework.data.domain.PageRequest;

class RepositoryUtil {

	static PageRequest createPageable(Integer from, Integer to) {
		from = from == null ? 0 : from.intValue();
		to = to == null ? 0 : to.intValue();
		int size = (to - from);
		int page = from == 0 ? 1 : (from / size) + 1;
		final PageRequest pageRequest = new PageRequest(page, size);
		return pageRequest;
	}
}
