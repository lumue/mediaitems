package mediaitems.app.http;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.server.config.EnableWebSocket;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "mediaitems.metadata.mongo", "mediaitems.http" })
@EnableMongoRepositories
@EnableWebMvc
@EnableWebSocket
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public PlatformTransactionManager getTransactionManager()
    {
    	return new AbstractPlatformTransactionManager(){
			private static final long serialVersionUID = 1L;

			@Override
			protected Object doGetTransaction() throws TransactionException {
				return new String("PseudoTransaction "+UUID.randomUUID().toString());
			}

			@Override
			protected void doBegin(Object transaction,
					TransactionDefinition definition)
					throws TransactionException {
				// do nothing, mongo does not support transactions				
			}

			@Override
			protected void doCommit(DefaultTransactionStatus status)
					throws TransactionException {
				// do nothing, mongo does not support transactions
			}

			@Override
			protected void doRollback(DefaultTransactionStatus status)
					throws TransactionException {
				// do nothing, mongo does not support transactions
				
			}
    		
    	};
    }

}