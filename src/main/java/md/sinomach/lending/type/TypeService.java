package md.sinomach.lending.type;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.subtypes.SubType;
import md.sinomach.lending.subtypes.SubTypeService;
import md.sinomach.lending.type.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    private final SubTypeService subTypeService;

    public List<Type> getAllTypes() {
        List<Type> all = typeRepository.findAll().stream().sorted(Comparator.comparing(Type::getOrder)).toList();
        return all;
    }

    public Type save(Type typeDao) {
        return typeRepository.save(typeDao);
    }

    public RemoveTypeResponse deleteById(Long id) {
        Optional<Type> typeById = typeRepository.findById(id);
        if (typeById.isEmpty()) {
            return RemoveTypeResponse.failed(RemoveTypeResponse.Error.notFound);
        }
        typeRepository.deleteById(id);
        return RemoveTypeResponse.success();
    }

    public AddTypeResponse addType(Type type) {
        Boolean exist = typeRepository.existsByName(type.getName());

        if (exist) {
            return AddTypeResponse.failed(AddTypeResponse.Error.alreadyExist);
        }

        Long sizeListOfType = Long.valueOf(getAllTypes().size());

        HashSet defaultSetSubType = new HashSet<SubType>();

        Type newType = typeRepository.save(new Type(null,
                type.getName(),
                type.getImg(),
                type.getImgId(),
                type.getImgBanner(),
                type.getShortDescription(),
                sizeListOfType + 1,
                defaultSetSubType));

        subTypeService.addSubType(newType, "default");

        return AddTypeResponse.success();
    }

    public EditTypeNameResponse editTypeName(Long id, Type editedType) {

        Boolean exist = typeRepository.existsByName(editedType.getName());
        if (exist) {
            return EditTypeNameResponse.failed(EditTypeNameResponse.Error.alreadyExist);
        }

        Optional<Type> typeById = typeRepository.findById(id);
        if (typeById.isEmpty()) {
            return EditTypeNameResponse.failed(EditTypeNameResponse.Error.type_not_found);
        }

        Type typeEditType = typeById.get();
        typeEditType.setName(editedType.getName());
        typeRepository.save(typeEditType);

        return EditTypeNameResponse.success();
    }

    public UpdateTypeOrderResponse updateOrderTypes(List<UpdateTypeOrder> updateTypeOrder) {
        try {
            for (UpdateTypeOrder typeOrder : updateTypeOrder) {
                Type referenceById = typeRepository.getReferenceById(typeOrder.getId());
                referenceById.setOrder(typeOrder.getOrder());
                typeRepository.save(referenceById);
            }
            return UpdateTypeOrderResponse.success();
        } catch (Exception e) {
            return UpdateTypeOrderResponse.failed(UpdateTypeOrderResponse.Error.failed);
        }
    }

    public Type getTypeById(Long id) {
        return typeRepository.getReferenceById(id);
    }
}


