package SE.project.persistance;

import java.util.ArrayList;

public interface DAOInterface<T> {

    /**
     * Inserts a new entity into the table.
     * 
     * @param entity the entity to be inserted.
     * @return true if the insertion was successful, false otherwise.
     */
    boolean insert(T entity);

    /**
     * Retrieves all entities from the table as a list.
     * 
     * @return a list of all entities.
     */
    ArrayList<T> getAll();
}
