package com.example.demo.repository;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query
    List<Image> findAllByProduct_id(UUID product_id);

    @Query
    List<Image> findByProduct_id(UUID id);
    //buscar imagen donde usuario id donde where type=1
    @Query
    Image findByProduct_idAndTypeIs1(UUID id);
}
