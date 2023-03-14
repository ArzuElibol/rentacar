package kodlama.io.rentacar.dataAccess.abstracts;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentacar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
   boolean existsByName(String name); //spring jpa keywords
   //Örnek; Brand findByName(String name);
   //Örnek; List<Brand> findByName(String name);
}
   
