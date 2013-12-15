package mediaitems.http.service.metadata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.repository.MediaItemRepository;

@RestController
public class ListMediaItemController {
	
	@Autowired(required=true)
	MediaItemRepository mediaItemRepository;
	
	@RequestMapping("/list")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<? extends MediaItem> list(int from,int to)
	{
		Iterable<? extends MediaItem> iterable = mediaItemRepository.getAll();
		return Lists.newArrayList(iterable).subList(from, to);
	}
	
}
