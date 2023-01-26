package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "authorId", source = "author.id")
    NewsDtoResponse newsToNewsDto(NewsModel newsModel);

    NewsModel newsDtoToNews(NewsDtoRequest newsDtoRequest);

}
