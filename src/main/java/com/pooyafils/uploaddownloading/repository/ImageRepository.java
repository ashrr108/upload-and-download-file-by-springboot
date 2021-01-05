package com.pooyafils.uploaddownloading.repository;

import com.pooyafils.uploaddownloading.domain.Image;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findById(int id);

}
