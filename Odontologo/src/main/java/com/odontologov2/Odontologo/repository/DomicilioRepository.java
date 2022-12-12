package com.odontologov2.Odontologo.repository;

import com.odontologov2.Odontologo.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DomicilioRepository extends JpaRepository<Domicilio,Integer> {

    static void deleteById(Long id) {
    }

    @Query("SELECT d FROM Domicilio d WHERE d.calle like ?1 and d.numero like ?2")
    Domicilio findIdByStreet(String calle, String numero);

}

