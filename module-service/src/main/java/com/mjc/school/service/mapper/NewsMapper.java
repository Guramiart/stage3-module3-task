package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;

import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper(uses = AuthorMapper.class)
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(source = "authorModel.id", target = "authorId")
    NewsDtoResponse newsToNewsDto(NewsModel newsModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(source = "authorId", target = "authorModel.id")
    @Mapping(source = "tagId", target = "tagModelSet")
    NewsModel newsDtoToNews(NewsDtoRequest newsDtoRequest);

    default Set<TagModel> map(Set<Long> value) {
        Set<TagModel> tagModels = new HashSet<>();
        if(value != null) {
            value.forEach(el -> {
                TagModel tagModel = new TagModel();
                tagModel.setId(el);
                tagModels.add(tagModel);
            });
        }
        return tagModels;
    }
}
