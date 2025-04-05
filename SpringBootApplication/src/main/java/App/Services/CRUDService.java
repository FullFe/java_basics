package App.Services;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(long id);
    Collection<T> getAll();
    void create(T t);
    void update( T t);
    void deleteById(long id);
}
