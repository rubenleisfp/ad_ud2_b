package ad.ad_ud2_b.ejemplo_dto.repository;

import java.util.Set;

/**
 * Esta interfaz nos obliga a implementar todos los metodos habituales de un
 * CRUD
 * 
 * @param <T>
 */
public interface GenericDao<T, ID extends Number> {

	Set<T> getAll() throws Exception;

	T getById(ID id) throws Exception;

	void create(T t) throws Exception;

	void update(T t) throws Exception;

	void delete(ID id) throws Exception;

}
