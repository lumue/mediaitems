package mediaitems.app.http;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableMongoRepositories(basePackages = { "mediaitems.metadata.springdata.repository" })
@ComponentScan(basePackages = { "mediaitems.app.http",
		"mediaitems.app.integration", "mediaitems.http",
		"mediaitems.metadata.springdata" })
@EnableWebMvc
@EnableTransactionManagement
@EnableIntegration
@IntegrationComponentScan
public class Application extends WebMvcConfigurerAdapter {

}