package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private static final String AUTHOR_PARAM = "Author";
    private final BaseRepository<AuthorModel, Long> repository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return repository.readAll()
                .stream()
                .map(AuthorMapper.INSTANCE::authorToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = repository.readById(id);
        if(authorModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), AUTHOR_PARAM, id));
        }
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel.get());
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = repository.create(AuthorMapper.INSTANCE.authorDtoToAuthor(createRequest));
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if(!repository.existById(updateRequest.getId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), AUTHOR_PARAM, updateRequest.getId()));
        }
        AuthorModel authorModel = repository
                .update(AuthorMapper.INSTANCE.authorDtoToAuthor(updateRequest));
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!repository.existById(id)) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), AUTHOR_PARAM, id));
        }
        return repository.deleteById(id);
    }

}
