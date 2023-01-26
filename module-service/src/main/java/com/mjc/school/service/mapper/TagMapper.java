package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDtoResponse tagToTagDto(TagModel tagModel);

    TagModel tagDtoToTag(TagDtoRequest tagDtoRequest);
    
}
