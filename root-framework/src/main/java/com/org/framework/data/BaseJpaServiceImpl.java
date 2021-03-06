package com.org.framework.data;

import com.org.framework.exception.database.NotFoundException;
import com.org.framework.validation.EntityValidator;
import com.org.framework.validation.Validity;

import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;

/**
 * BaseService implementation for basic access to service methods of crud
 * operation on entity
 *
 * @author: Y Kamesh Rao
 * @created: 12/11/11 4:13 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
public abstract class BaseJpaServiceImpl<T extends Entity, ID extends Serializable> implements BaseService<T, ID> {
	protected BaseJpaRepository<T, ID> baseJpaRepository;
	protected Class<T> entityClass;
	protected String nfEntityMsg;
	protected int nfEntityCode;

	public T insert(T object) throws Exception {
		return baseJpaRepository.insert(object);
	}

	public T update(T object) throws Exception {
		return baseJpaRepository.update(object);
	}

	public void delete(T object) throws Exception {
		baseJpaRepository.delete(object);
	}

	public T findById(ID id) throws Exception {
		T result = baseJpaRepository.findById(id);

		if (result != null)
			return result;
		else
			throw new NotFoundException(nfEntityMsg, nfEntityCode);
	}

	public Collection<T> findAllByPage(int pageNum, int countPerPage, Order order) throws Exception {
		
		return null; 
	}

	public Validity validate(T object) {
		EntityValidator<T> entityValidator = new EntityValidator<T>();
		return entityValidator.validate(object, entityClass);
	}
}
