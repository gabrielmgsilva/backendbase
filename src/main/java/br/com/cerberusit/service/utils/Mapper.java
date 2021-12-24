package br.com.cerberusit.service.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    private Mapper(){}

    public static <D,T> Page<D> mapEntityToPagedDto(Page<T> entity, Class<D> dto){
        log.info("INVOKING MAPPER");
        return entity.map(obj -> modelMapper.map(obj, dto));
    }

    public static <S, T> List<T> mapEntityToListedDto(List<S> source, Class<T> targetClass) {
        log.info("INVOKING MAPPER");
        return source
                .stream()
                .map(obj -> modelMapper.map(obj, targetClass))
                .collect(Collectors.toList());
    }
}
