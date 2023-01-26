package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private static final String TAG_PARAM = "Tag";
    private final BaseRepository<TagModel, Long> repository;

    @Autowired
    public TagService(BaseRepository<TagModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return repository.readAll()
                .stream()
                .map(TagMapper.INSTANCE::tagToTagDto)
                .toList();
    }

    @Override
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModel = repository.readById(id);
        if(tagModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), TAG_PARAM, id));
        }
        return TagMapper.INSTANCE.tagToTagDto(tagModel.get());
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        TagModel tagModel = repository.create(TagMapper.INSTANCE.tagDtoToTag(createRequest));
        return TagMapper.INSTANCE.tagToTagDto(tagModel);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if(!repository.existById(updateRequest.getId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), TAG_PARAM, updateRequest.getId()));
        }
        TagModel tagModel = repository
                .update(TagMapper.INSTANCE.tagDtoToTag(updateRequest));
        return TagMapper.INSTANCE.tagToTagDto(tagModel);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!repository.existById(id)) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), TAG_PARAM, id));
        }
        return repository.deleteById(id);
    }
}
