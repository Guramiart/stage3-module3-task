package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.TagModel;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDtoResponse tagToTagDto(TagModel tagModel);

    TagModel tagDtoToTag(TagDtoRequest tagDtoRequest);
    
}
