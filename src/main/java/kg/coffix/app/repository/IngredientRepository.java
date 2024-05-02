package kg.coffix.app.repository;

import kg.coffix.app.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query(value = "SELECT * FROM ingredients i WHERE i.has_been_removed = false ORDER BY i.id",
            nativeQuery = true)
    List<Ingredient> findAllPresent();


    @Query(value = "SELECT * FROM ingredients i WHERE i.id = ?1 AND i.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Ingredient> findPresentById(Long id);

    @Query(value = "SELECT * FROM ingredients i WHERE i.naming = ?1 AND i.has_been_removed = false LIMIT 1",
            nativeQuery = true)
    Optional<Ingredient> findByNaming(String naming);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients SET has_been_removed = true WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);

    @Query(value = "SELECT i.naming FROM ingredients i WHERE i.has_been_removed = false ORDER BY i.id",
            nativeQuery = true)
    List<String> fetchAllPresentNames();

}
