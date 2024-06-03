package dev.jihogrammer.item.adaptor.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ItemSpringDataJpaRepository extends CrudRepository<ItemSpringDataJpaEntity, Long> {

    Collection<ItemSpringDataJpaEntity> findByNameLikeAndPriceBetween(String input, Integer minPrice, Integer maxPrice);

    @Query("SELECT i FROM ItemSpringDataJpaEntity i WHERE name LIKE CONCAT('%', :input, '%') AND price BETWEEN :minPrice AND :maxPrice")
    Collection<ItemSpringDataJpaEntity> findAllItems(
            @Param("input") String input,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice);

}
