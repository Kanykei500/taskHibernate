package peaksoft;

import peaksoft.config.DataBaseConnection;
import peaksoft.enums.OperationSystem;
import peaksoft.model.Laptop;
import peaksoft.service.LaptopsService;
import peaksoft.service.LaptopsServiceImpl;

import java.time.LocalDate;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        DataBaseConnection.createEntityManagerFactory();
        LaptopsService service = new LaptopsServiceImpl();
        while (true) {


            System.out.println("""
                    1.SAVE LAPTOP
                    2.SAVE ALL
                    3.DELETE BY ID
                    4.DELETE ALL
                    5.FIND ALL
                    6.UPDATE
                    7.GROUP BY
                    8.SORT
                                        
                    WRITE COMMAND
                    """);

            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    System.out.println(service.saveLaptop(new Laptop("Hp", OperationSystem.WINDOWS,
                            32, 50000, LocalDate.of(2019, 1, 1))));
                    break;
                case 2:
                    List<Laptop> laptops = new ArrayList<>(List.of(new Laptop("Acer", OperationSystem.MACOS,
                                    8, 45000, LocalDate.of(2020, 10, 25)),
                            new Laptop("Dell", OperationSystem.LINUX,
                                    128, 55000, LocalDate.of(2022, 12, 20)),
                            new Laptop("Asus", OperationSystem.WINDOWS,
                                    32, 70000, LocalDate.of(2016, 7, 27)),
                            new Laptop("Linux", OperationSystem.LINUX, 8,
                                    34000, LocalDate.of(2021, 8, 25))));
                    service.saveAll(laptops).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println(service.deleteById(10L));
                    break;
                case 4:
                    System.out.println(service.deleteAll());
                    break;
                case 5:
                    System.out.println(service.findAll());

                    break;
                case 6:
                    System.out.println(service.update(12L, new Laptop("Lenovo", OperationSystem.WINDOWS,
                            128, 70000, LocalDate.of(2022, 12, 12))));

                    break;
                case 7:
                    System.out.println(service.groupBy());
                    break;
                case 8:
                    System.out.println(service.sortByDifferentColumn("id", "asc"));
                    break;

            }


        }


    }
}

