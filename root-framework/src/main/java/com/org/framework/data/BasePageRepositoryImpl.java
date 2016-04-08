package com.org.framework.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public class BasePageRepositoryImpl<T, ID extends Serializable> implements BasePageRepository<T, ID> {

	@Autowired
	private PagingAndSortingRepository<T, ID> repository;
	
	@Override
	public <S extends T> S save(S entity) {
		return (S) repository.save(entity);
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return repository.save(entities);
	}

	@Override
	public T findOne(ID id) {
		return (T) repository.findOne(id);
	}

	/**
	 * 是否存在对应数据
	 */
	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return repository.findAll(ids);
	}

	/**
	 * 查询总数
	 */
	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {

		repository.delete(id);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);

	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		repository.delete(entities);

	}
	/**
	 * 批量删除  --依据Id
	 */
	public void delete(List<ID > ids) {
		for(ID id : ids){
			repository.delete(id);
		}
	}

	/**
	 * 删除所有
	 */
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	@Override
	public <S extends T> S update(S entity) {
		return (S) repository.save(entity);
	}

	/**
	 * 批量 更新
	 */
	@Override
	public <S extends T> Iterable<S> update(Iterable<S> entities) {
		return repository.save(entities);
	}

	

}
