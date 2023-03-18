package md.sinomach.lending.menuProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubTypeService {
    private final  SubTypeRepository subTypeRepository;


    public SubType save(SubType subTypeDao) {
        return subTypeRepository.save(subTypeDao);
    }
}
