package mediaitems.app.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.config.EnableWebSocket;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "mediaitems.app", "mediaitems.metadata.mongo",
		"mediaitems.http" })
@EnableMongoRepositories
@EnableWebMvc
@EnableWebSocket
@EnableTransactionManagement
public class Application extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}