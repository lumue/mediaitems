package mediaitems.metadata.springdata.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.util.Assert;

@Component
public final class NullTransactionManager extends
		AbstractPlatformTransactionManager {
	private static final String TRANSACTION = new String("PseudoTransaction "+UUID.randomUUID().toString());
	private static final long serialVersionUID = 1L;

	@Override
	protected Object doGetTransaction() throws TransactionException {
		return TRANSACTION;
	}

	@Override
	protected void doBegin(Object transaction,
			TransactionDefinition definition)
			throws TransactionException {
		Assert.isTrue(TRANSACTION.equals(transaction));				
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status)
			throws TransactionException {
		Assert.notNull(status);
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status)
			throws TransactionException {
		// do nothing, mongo does not support transactions
		Assert.notNull(status);
	}
}