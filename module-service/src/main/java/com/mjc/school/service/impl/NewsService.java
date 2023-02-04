package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.TagModel;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.NotEmpty;
import com.mjc.school.service.annotation.Valid;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private static final String NEWS_PARAM = "News";
    private static final String AUTHOR_PARAM = "Author";
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final BaseRepository<TagModel, Long> tagRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository,
                       BaseRepository<AuthorModel, Long> authorRepository,
                       BaseRepository<TagModel, Long> tagRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll()
                .stream()
                .map(NewsMapper.INSTANCE::newsToNewsDto)
                .toList();
    }

    @Override
    @NotEmpty
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = newsRepository.readById(id);
        if(newsModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, id));
        }
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel.get());
    }

    @Override
    @Valid
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        if(authorRepository.existById(createRequest.getAuthorId())) {
            NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDtoToNews(createRequest));
            return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
        } else {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), AUTHOR_PARAM, createRequest.getAuthorId()));
        }
    }

    @Override
    @Valid
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if(!newsRepository.existById(updateRequest.getId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, updateRequest.getId()));
        }
        if(!authorRepository.existById(updateRequest.getAuthorId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), AUTHOR_PARAM, updateRequest.getAuthorId()));
        }
        NewsModel newsModel = newsRepository
                .update(NewsMapper.INSTANCE.newsDtoToNews(updateRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @NotEmpty
    public boolean deleteById(Long id) {
        if(!newsRepository.existById(id)) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, id));
        }
        return newsRepository.deleteById(id);
    }

    @NotEmpty
    public AuthorDtoResponse readAuthorByNewsId(Long id) {
        AuthorModel authorModel = newsRepository.readById(id).get().getAuthorModel();
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel);
    }

    @NotEmpty
    public Set<TagDtoResponse> readTagsByNewsId(Long id) {
        if(!newsRepository.existById(id)) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, id));
        }
        return ((NewsRepository)newsRepository).getTagsByNewsId(id)
                .stream()
                .map(TagMapper.INSTANCE::tagToTagDto)
                .collect(Collectors.toSet());
    }

    public List<NewsDtoResponse> readNewsByParam(Set<String> names, Set<Long> ids,
                                                 String author, String title, String content) {
        return ((NewsRepository) newsRepository).readNewsByParam(names, ids, author, title, content)
                .stream()
                .map(NewsMapper.INSTANCE::newsToNewsDto)
                .collect(Collectors.toList());
    }
}
