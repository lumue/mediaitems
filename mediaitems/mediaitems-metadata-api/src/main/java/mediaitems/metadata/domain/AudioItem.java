package mediaitems.metadata.domain;

import org.joda.time.Duration;

public interface AudioItem extends MediaItem {

	public interface AudioItemBuilder<T extends AudioItem> extends
			MediaItemBuilder<T> {
		
		public AudioItemBuilder<T> setAudioCodec(String codec);

		public AudioItemBuilder<T> setLength(Duration duration);
		
	}
	public String getAudioCodec();

	public Duration getLength();
}
