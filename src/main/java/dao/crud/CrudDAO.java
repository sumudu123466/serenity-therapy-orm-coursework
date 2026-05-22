package dao.crud;

import dao.superdao.SuperDAO;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);

    T search(ID id);

    List<T> getAll();
}