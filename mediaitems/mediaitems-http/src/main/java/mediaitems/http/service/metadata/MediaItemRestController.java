package mediaitems.http.service.metadata;

import java.util.List;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.repository.MediaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class MediaItemRestController {

	@Autowired(required = true)
	MediaItemRepository mediaItemRepository;

	@RequestMapping("/metadata/{mediaTypeKey}/list")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<? extends MediaItem> listMediaType(
			@PathVariable String mediaTypeKey, Integer from, Integer to) {
		MediaType mediaType = StringUtils.isEmpty(mediaTypeKey) ? null
				: MediaType.forKey(mediaTypeKey);
		final Iterable<? extends MediaItem> result;
		result = mediaItemRepository.getByMediaType(mediaType, from, to);
		return Lists.newArrayList(result);
	}

	@RequestMapping("/metadata/list")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<? extends MediaItem> list(Integer from, Integer to) {
		final Iterable<? extends MediaItem> result;
		result = mediaItemRepository.getAll(from, to);
		return Lists.newArrayList(result);
	}

	@RequestMapping("/metadata/get/{key}")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MediaItem get(@PathVariable String key) {
		MediaItem result;
		result = mediaItemRepository.get(key);
		return result;
	}

}
