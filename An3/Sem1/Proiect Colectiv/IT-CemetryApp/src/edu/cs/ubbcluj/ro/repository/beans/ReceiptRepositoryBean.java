package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Receipt;
import edu.cs.ubbcluj.ro.repository.ReceiptRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "ReceiptRepository", mappedName = "ReceiptRepository")
public class ReceiptRepositoryBean extends BaseRepositoryBean<Receipt, Integer>
		implements ReceiptRepository {

	public ReceiptRepositoryBean() {
		super(Receipt.class);
	}
	@Override
	public Receipt getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Receipt.class, id);

	}

}
