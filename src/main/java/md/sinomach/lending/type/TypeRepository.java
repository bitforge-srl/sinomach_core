package md.sinomach.lending.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Boolean existsByName(String name);
}
