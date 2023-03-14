package kodlama.io.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentacar.entities.concretes.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}