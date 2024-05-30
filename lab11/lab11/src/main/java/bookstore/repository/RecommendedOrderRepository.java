package bookstore.repository;

import bookstore.model.business.Book;
import bookstore.model.business.RecommendedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedOrderRepository extends JpaRepository<RecommendedOrder, Long> {
    List<RecommendedOrder> findByBookA(Book bookA);
    List<RecommendedOrder> findByBookB(Book bookB);
}
