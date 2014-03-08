package mediaitems.app.http;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = { "mediaitems.app",
		"mediaitems.metadata.springdata", "mediaitems.http" })
@EnableMongoRepositories(basePackages = { "mediaitems.metadata.springdata.repository" })
@EnableWebMvc
@EnableTransactionManagement
public class Application extends WebMvcConfigurerAdapter {

}