package br.edu.ifpb.teste.de.entidades;

import edu.ifpb.geosertao.geosertao.core.entities.CatalogService;
import edu.ifpb.geosertao.geosertao.core.entities.GeometryColumns;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
public class Loader {

    public static void main(String[] args) {
        /*CatalogService catalog = new CatalogService();
        catalog.setProvider("Globo");
        catalog.setUrl("http://www.globo.com");
        catalog.setRecordByIdUrl("http://www.globo.com");
        persist(catalog);
                
        GeometryColumns col = new GeometryColumns();
        col.setCoordDimension(1);
        col.setSrid(10);
        col.setType("teste");
        col.setfTableCatalog("teste");
        col.setfTableColumn("teste");
        col.setfTableName("teste");
        col.setfTableSchema("teste");
        persist(col);*/
        
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("br.edu.ifpb_geosertao_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
