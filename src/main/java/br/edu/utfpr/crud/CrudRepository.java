package br.edu.utfpr.crud;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public abstract class CrudRepository<T> implements PanacheRepository<T> {
}
