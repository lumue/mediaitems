package mediaitems.application.sources.controller;

import mediaitems.application.sources.integration.ContentDiscoveryEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("contentSourceController")
public class ContentSourceController {

	@Autowired(required = true)
	private ContentDiscoveryEndpoint contentDiscoveryEndpoint;

	@RequestMapping("/sources/scan/start")
	public void startScan() {
		contentDiscoveryEndpoint.startScan();
	}
}
