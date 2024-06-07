package dev.jihogrammer.transaction.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends CrudRepository<Order, Long> {
}
