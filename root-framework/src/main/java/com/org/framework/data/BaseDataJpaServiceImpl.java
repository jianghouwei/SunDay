package com.org.framework.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseDataJpaServiceImpl<T, ID extends Serializable> implements BaseDataJpaService<T, ID> {

	@Autowired
	private BasePageRepository<T, ID> baseRepository;

	@Override
	public <S extends T> S save(S entity) {
		return (S) baseRepository.save(entity);
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return baseRepository.save(entities);
	}

	@Override
	public T findOne(ID id) {
		return (T) baseRepository.findOne(id);
	}

	/**
	 * 是否存在对应数据
	 */
	@Override
	public boolean exists(ID id) {
		return baseRepository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return baseRepository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return baseRepository.findAll(ids);
	}

	/**
	 * 查询总数
	 */
	@Override
	public long count() {
		return baseRepository.count();
	}

	@Override
	public void delete(ID id) {

		baseRepository.delete(id);
	}

	@Override
	public void delete(T entity) {
		baseRepository.delete(entity);

	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		baseRepository.delete(entities);

	}

	/**
	 * 批量删除 --依据Id
	 */
	public void delete(List<ID> ids) {
		for (ID id : ids) {
			baseRepository.delete(id);
		}
	}

	/**
	 * 删除所有
	 */
	@Override
	public void deleteAll() {
		baseRepository.deleteAll();
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return baseRepository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public <S extends T> S update(S entity) {
		return (S) baseRepository.save(entity);
	}

	/**
	 * 批量 更新
	 */
	@Override
	public <S extends T> Iterable<S> update(Iterable<S> entities) {
		return baseRepository.save(entities);
	}

}
