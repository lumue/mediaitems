package mediaitems.metadata.springdata.repository;

import mediaitems.metadata.springdata.domain.TagImpl;

import org.springframework.data.repository.PagingAndSortingRepository;

interface SpringDataTagRepository extends
		PagingAndSortingRepository<TagImpl, String> {

}
