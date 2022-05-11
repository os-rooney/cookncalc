package com.example.cookncalc.supermarket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermarketRepository extends CrudRepository<Supermarket, Long> {
}
