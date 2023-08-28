package md.sinomach.lending.subtypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypeRepository extends JpaRepository <SubType, Long>{
    Boolean existsByNameAndTypeId(String name, Long typeId);
}

