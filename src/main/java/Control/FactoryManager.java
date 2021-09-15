// this class holds a static entity manager factory, as far as i read, this should be instantiated once
// and closed when the application closes
package Control;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.
            createEntityManagerFactory("OOPFinalProject");
}
