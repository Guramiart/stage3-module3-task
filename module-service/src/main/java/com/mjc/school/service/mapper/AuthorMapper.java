package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDtoResponse authorToAuthorDto(AuthorModel model);

    AuthorModel authorDtoToAuthor(AuthorDtoRequest authorDtoRequest);

}
