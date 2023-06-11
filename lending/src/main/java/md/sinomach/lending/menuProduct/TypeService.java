package md.sinomach.lending.menuProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Set<Type> getAllTypes() {
        List<Type> all = typeRepository.findAll().stream().sorted(Comparator.comparing(Type::getOrder)).toList();
        return new HashSet<>(all);
    }

    public Type save(Type typeDao) {
      return  typeRepository.save(typeDao);
    }
}
