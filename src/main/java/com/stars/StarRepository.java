package com.stars;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
}
