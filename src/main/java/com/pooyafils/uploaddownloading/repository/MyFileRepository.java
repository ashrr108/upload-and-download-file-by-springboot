package com.pooyafils.uploaddownloading.repository;

import com.pooyafils.uploaddownloading.domain.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile, Long> {
    MyFile findById(int id);

}
