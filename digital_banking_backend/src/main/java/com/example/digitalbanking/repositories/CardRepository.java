package com.example.digitalbanking.repositories;

import com.example.digitalbanking.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
