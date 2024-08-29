package com.example.library_management_system.Repositories;

import com.example.library_management_system.Entities.LibraryCard;
import org.apache.tomcat.jni.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Integer> {
}
