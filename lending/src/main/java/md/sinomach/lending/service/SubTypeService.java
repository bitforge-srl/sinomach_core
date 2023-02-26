package md.sinomach.lending.service;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.SubType;
import md.sinomach.lending.dao.Type;
import md.sinomach.lending.repository.SubTypeRepository;
import md.sinomach.lending.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class SubTypeService {
    private final  SubTypeRepository subTypeRepository;

    public Set<SubType> getAllSubTypes() {
        List<SubType> subTypes = subTypeRepository.findAll();
        return new HashSet<>(subTypes);
    }
}
