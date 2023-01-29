package com.mjc.school.service.mapper;

import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDtoResponse newsToNewsDto(NewsModel newsModel);

    NewsModel newsDtoToNews(NewsDtoRequest newsDtoRequest);

}
