package md.sinomach.lending.service;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Type;
import md.sinomach.lending.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Set<Type> getAllTypes() {
        List<Type> all = typeRepository.findAll();
        return new HashSet<>(all);
    }

    public Type save(Type typeDao) {
      return  typeRepository.save(typeDao);
    }
}
