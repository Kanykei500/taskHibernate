package peaksoft.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DataBaseConnection;
import peaksoft.enums.OperationSystem;
import peaksoft.model.Laptop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LaptopsDaoImpl implements LaptopsDao,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory= DataBaseConnection.createEntityManagerFactory();
    @Override
    public Laptop saveLaptop(Laptop laptop) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(laptop);
        System.out.println("Successfully saved");
        entityManager.getTransaction().commit();
        entityManager.close();


        return laptop;
    }

    @Override
    public List<Laptop> saveAll(List<Laptop> laptops) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Laptop laptop : laptops) {
            entityManager.persist(laptop);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptops;
    }

    @Override
    public Laptop deleteById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop laptop= entityManager.createQuery("select l from Laptop l  where l.id=:id",
                        Laptop.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.remove(laptop);
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptop;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Laptop ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<Laptop> findAll() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Laptop>laptopList=entityManager.createQuery("select l from Laptop l",Laptop.class)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return laptopList ;
    }

    @Override
    public Laptop update(Long id, Laptop laptop) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop laptop1=entityManager.getReference(Laptop.class,id);
        laptop1.setBrand(laptop.getBrand());
        laptop1.setMemory(laptop.getMemory());
        laptop1.setPrice(laptop.getPrice());
        laptop1.setOperationSystem(laptop.getOperationSystem());
        laptop1.setDateOfIssue(laptop.getDateOfIssue());
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptop1;
    }

    @Override
    public Map<OperationSystem, List<Laptop>> groupBy() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Map<OperationSystem,List<Laptop>> listMap=entityManager.createQuery("select l from Laptop l",Laptop.class).
                getResultStream().collect(Collectors.groupingBy(Laptop::getOperationSystem));
        entityManager.getTransaction().commit();
        entityManager.close();
        return listMap;
    }

    @Override
    public List<Laptop> sortByDifferentColumn(String column, String ascOrDesc) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("sort by id->asc");

        List<Laptop>list= entityManager.createQuery("select l from Laptop l order  by id ").getResultList();

        System.out.println("sort by id-> desc");
         list=entityManager.createQuery("select l from Laptop l order by id desc ").getResultList();
        System.out.println("sort by brand");
       list= entityManager.createQuery("select l from Laptop l order by brand  ").getResultList();
        System.out.println("sort by brand desc");
       list=  entityManager.createQuery("select l from Laptop l order by brand desc ").getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        return list;
    }

    @Override
    public void close() throws Exception {

    }
}
