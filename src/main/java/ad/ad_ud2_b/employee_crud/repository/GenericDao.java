package ad.ad_ud2_b.employee_crud.repository;

import java.util.List;

/**
 * Esta interfaz nos obliga a implementar todos los metodos habituales de un CRUD
 * @param <T>
 */
public interface GenericDao<T> {

		List<T> getAll() throws Exception;
		T getById (Long id) throws Exception;
		void create(T t) throws Exception;
		void update(T t) throws Exception;
		void delete(Long id) throws Exception;
		void deleteAll() throws Exception;
	
}
