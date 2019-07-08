package com.restful.spring.boot.restful_spring_boot.repository;

import com.restful.spring.boot.restful_spring_boot.model.Toko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokoRepository extends JpaRepository<Toko, Long> {
}
