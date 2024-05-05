package kg.coffix.app.repository;


import kg.coffix.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "SELECT * FROM products p WHERE p.id = ?1 AND p.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Product> findPresentById(Long id);

    @Query(value = "SELECT * FROM products p WHERE p.naming = ?1 AND p.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Product> findPresentByNaming(String naming);

    @Query(value = "SELECT * FROM products p WHERE p.has_been_removed = false ORDER BY p.id",
            nativeQuery = true)
    List<Product> findAllPresent();

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET has_been_removed = true WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);

    @Query(value = "SELECT p.naming FROM products p WHERE p.has_been_removed = false ORDER BY p.id",
            nativeQuery = true)
    List<String> fetchAllPresentNames();
}
