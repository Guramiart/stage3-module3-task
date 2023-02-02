package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = NewsMapper.class)
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDtoResponse authorToAuthorDto(AuthorModel model);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "newsModelList", source = "newsList")
    AuthorModel authorDtoToAuthor(AuthorDtoRequest authorDtoRequest);

}
