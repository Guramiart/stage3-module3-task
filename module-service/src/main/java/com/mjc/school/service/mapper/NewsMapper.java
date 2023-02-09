package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;

import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper(uses = AuthorMapper.class)
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(source = "authorModel.id", target = "authorId")
    @Mapping(source = "tagModelSet", target = "tagId", qualifiedByName = "setToId")
    NewsDtoResponse newsToNewsDto(NewsModel newsModel);

    @Named("setToId")
    default Set<Long> setToId(Set<TagModel> value) {
        Set<Long> tagIdList = new HashSet<>();
        if(value != null) {
            value.forEach(el -> {
                tagIdList.add(el.getId());
            });
        }
        return tagIdList;
    }

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
