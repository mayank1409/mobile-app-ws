package com.mayankmadhav.demo.app.mobileappws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayankmadhav.demo.app.mobileappws.service.base.IGenericService;

public abstract class AbstractService<T, ID> implements IGenericService<T, ID> {

	@Override
	public T save(T t) {
		t = repository().save(t);
		return t;
	}

	@Override
	public T update(T t, ID id) {
		t = get(id);
		return repository().save(t);
	}

	@Override
	public T get(ID id) {
		Optional<T> t = repository().findById(id);
		if (t.isEmpty()) {

		}
		return t.get();
	}

	@Override
	public List<T> list() {
		return repository().findAll();
	}

	@Override
	public void delete(ID id) {
		T t = get(id);
		repository().delete(t);
	}

	@Override
	public long count() {
		return repository().count();
	}
	
	protected abstract  JpaRepository<T, ID> repository();

}
