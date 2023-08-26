package hh.Service;

import java.sql.SQLException;
import java.util.List;

public interface IGenericService<T,E>{
    List<T> findall()    ;
    void  save(T t)    ;
    T findById (E e)    ;
    void  deleteById(E e)    ;
}
