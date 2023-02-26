package md.sinomach.lending.repository;

import md.sinomach.lending.dao.SubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypeRepository extends JpaRepository <SubType, Long>{
}
