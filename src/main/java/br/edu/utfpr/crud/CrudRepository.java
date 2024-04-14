package br.edu.utfpr.crud;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import java.util.List;

public abstract class CrudRepository<T> implements PanacheRepository<T> {

    public List<T> pageListSort(int page, int size, String atributo, Sort.Direction direction){
        return findAll(Sort.by(atributo, direction)).page(page, size).list();
    }

    public int pageCount(int page, int size){
        return findAll().page(page, size).pageCount();
    }

    public long pageTotal(int page, int size){
        return findAll().page(page, size).count();
    }

}
