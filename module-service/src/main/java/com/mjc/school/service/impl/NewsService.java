package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
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
    private final BaseRepository<NewsModel, Long> repository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return repository.readAll()
                .stream()
                .map(NewsMapper.INSTANCE::newsToNewsDto)
                .toList();
    }

    @Override
    @NotEmpty
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = repository.readById(id);
        if(newsModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, id));
        }
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel.get());
    }

    @Override
    @Valid
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = repository.create(NewsMapper.INSTANCE.newsDtoToNews(createRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @Valid
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if(!repository.existById(updateRequest.getId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, updateRequest.getId()));
        }
        NewsModel newsModel = repository
                .update(NewsMapper.INSTANCE.newsDtoToNews(updateRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @NotEmpty
    public boolean deleteById(Long id) {
        if(!repository.existById(id)) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), NEWS_PARAM, id));
        }
        return repository.deleteById(id);
    }
}
