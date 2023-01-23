package peaksoft.service;

import peaksoft.dao.LaptopsDao;
import peaksoft.dao.LaptopsDaoImpl;
import peaksoft.enums.OperationSystem;
import peaksoft.model.Laptop;

import java.util.List;
import java.util.Map;

public class LaptopsServiceImpl implements LaptopsService{
    LaptopsDao laptopsDao=new LaptopsDaoImpl();
    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopsDao.saveLaptop(laptop);

    }

    @Override
    public List<Laptop> saveAll(List<Laptop> laptops) {
        return laptopsDao.saveAll(laptops);
    }

    @Override
    public Laptop deleteById(Long id) {
        return laptopsDao.deleteById(id);
    }

    @Override
    public String deleteAll() {
        laptopsDao.deleteAll();
        return "Successfully deleted";
    }

    @Override
    public List<Laptop> findAll() {
        return laptopsDao.findAll();
    }

    @Override
    public Laptop update(Long id, Laptop laptop) {
        return laptopsDao.update(id,laptop);
    }

    @Override
    public Map<OperationSystem, List<Laptop>> groupBy() {

        return laptopsDao.groupBy();
    }

    @Override
    public List<Laptop> sortByDifferentColumn(String column, String ascOrDesc) {

        return laptopsDao.sortByDifferentColumn(column,ascOrDesc);
    }
}
