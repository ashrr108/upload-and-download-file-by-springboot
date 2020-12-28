package com.pooyafils.uploaddownloading.repository;

import com.pooyafils.uploaddownloading.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
