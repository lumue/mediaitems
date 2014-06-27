package mediaitems.sources.api.scan;

public interface ScanFailedEvent extends ScanEvent {
	public Throwable getCause();
}
