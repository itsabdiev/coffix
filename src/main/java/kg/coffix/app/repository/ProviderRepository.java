package kg.coffix.app.repository;

import kg.coffix.app.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider,Long> {

    @Query(value = "SELECT * FROM providers p WHERE p.has_been_removed = false ORDER BY p.id",
            nativeQuery = true)
    List<Provider> findAllPresent();

    @Query(value = "SELECT * FROM providers p WHERE p.full_name = ?1 AND p.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Provider> findByFullName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE providers SET has_been_removed = true WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);

    @Query(value = "SELECT * FROM providers p WHERE p.id = ?1 AND p.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Provider> findPresentById(Long id);

    @Query(value = "SELECT p.full_name FROM providers p WHERE p.has_been_removed = false ORDER BY p.id",
            nativeQuery = true)
    List<String> fetchAllPresentNames();
}
