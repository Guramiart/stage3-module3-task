package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.NotEmpty;
import com.mjc.school.service.annotation.Valid;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private static final String NEWS_PARAM = "News";
    private static final String AUTHOR_PARAM = "Author";
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository,
                       BaseRepository<AuthorModel, Long> authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
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
}
