package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorMapper.class)
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(source = "authorModel.id", target = "authorId")
    NewsDtoResponse newsToNewsDto(NewsModel newsModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(source = "authorId", target = "authorModel.id")
    NewsModel newsDtoToNews(NewsDtoRequest newsDtoRequest);

}
