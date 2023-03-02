package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Type;
import md.sinomach.lending.dto.TypeEndImageResponse;
import md.sinomach.lending.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/type_image")
@RequiredArgsConstructor

public class TypeEndImageController {
    private final TypeService typeService;

    @GetMapping("")
    private Set<TypeEndImageResponse> getTypeEndImage() {

        Set<Type> allTypes = typeService.getAllTypes();

        return allTypes.stream()
                .map(type -> TypeEndImageResponse.builder()
                        .type(type.getName())
                        .imageSource(type.getImg())
                        .build()).collect(Collectors.toSet());
    }

}
