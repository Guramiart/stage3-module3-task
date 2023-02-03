package com.mjc.school;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.service.builder.NewsRequestBuilder;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.mapper.NewsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository repository;

    @InjectMocks
    private NewsService service;

    private NewsDtoRequest newsDtoRequest;

    @BeforeEach
    public void setup(){

        newsDtoRequest = new NewsRequestBuilder()
                .setId(1L)
                .setTitle("TestTitle")
                .setContent("TestContent")
                .setTagId(Stream.of(1L, 2L, 3L).collect(Collectors.toCollection(HashSet::new)))
                .build();
    }

    @DisplayName("JUnit test for readAllNews method")
    @Test
    public void getAllNewsTest(){
        NewsDtoRequest newsDtoRequest1 = new NewsRequestBuilder()
                .setTitle("TestTitle_1")
                .setContent("TestContent_1")
                .setTagId(Stream.of(1L, 2L, 3L).collect(Collectors.toCollection(HashSet::new)))
                .build();
        NewsModel newsModel = NewsMapper.INSTANCE.newsDtoToNews(newsDtoRequest);
        NewsModel newsModel1 = NewsMapper.INSTANCE.newsDtoToNews(newsDtoRequest1);
        given(repository.readAll()).willReturn(List.of(newsModel, newsModel1));

        List<NewsDtoResponse> newsList = service.readAll();

        assertThat(newsList).isNotNull();
        assertThat(newsList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getNewsById method")
    @Test
    public void getNewsByIdTest(){
        NewsModel newsModel = NewsMapper.INSTANCE.newsDtoToNews(newsDtoRequest);
        given(repository.readById(1L)).willReturn(Optional.of(newsModel));
        NewsDtoResponse savedNews = service.readById(newsModel.getId());
        assertThat(savedNews).isNotNull();
    }

    @DisplayName("JUnit test for updateNews method")
    @Test
    public void updateNewsTest(){
        newsDtoRequest.setTitle("UpdateTitle");
        newsDtoRequest.setContent("UpdateContent");
        NewsModel newsModel = NewsMapper.INSTANCE.newsDtoToNews(newsDtoRequest);
        given(repository.update(newsModel)).willReturn(newsModel);
        given(repository.existById(any())).willReturn(true);
        NewsDtoResponse updatedNews = service.update(newsDtoRequest);
        assertThat(updatedNews.getTitle()).isEqualTo("UpdateTitle");
        assertThat(updatedNews.getContent()).isEqualTo("UpdateContent");
    }

    @DisplayName("JUnit test for deleteNews method")
    @Test
    public void deleteNewsTest(){
        given(repository.deleteById(1L)).willReturn(true);
        given(repository.existById(any())).willReturn(true);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

}
