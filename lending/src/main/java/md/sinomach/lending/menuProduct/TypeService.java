package md.sinomach.lending.menuProduct;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Set<Type> getAllTypes() {
        List<Type> all = typeRepository.findAll().stream().sorted(Comparator.comparing(Type::getOrder)).toList();
        return new HashSet<>(all);
    }

    public Type save(Type typeDao) {
        return typeRepository.save(typeDao);
    }

    public TypeDeleteResponse deleteById(Long id) {
        Optional<Type> typeById = typeRepository.findById(id);
        if (typeById.isEmpty()) {
            return TypeDeleteResponse.failed("Type is not found");
        }
        typeRepository.deleteById(id);
        return TypeDeleteResponse.success();
    }
}
