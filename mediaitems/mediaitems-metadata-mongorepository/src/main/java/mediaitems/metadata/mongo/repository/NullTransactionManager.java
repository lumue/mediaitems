package mediaitems.metadata.mongo.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Component
public final class NullTransactionManager extends
		AbstractPlatformTransactionManager {
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
}