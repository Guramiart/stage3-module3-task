package com.mjc.school;


import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.impl.AuthorRepository;

import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.mapper.AuthorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    private AuthorDtoRequest authorDtoRequest;

    @BeforeEach
    void setup(){
        authorDtoRequest = new AuthorDtoRequest
                .AuthorDtoRequestBuilder("TestAuthor")
                .id(1L)
                .build();
    }

    @DisplayName("JUnit test for createAuthor method")
    @Test
    void createAuthorTest(){
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDtoRequest);
        given(repository.create(authorModel)).willReturn(authorModel);
        AuthorDtoResponse savedAuthor = service.create(authorDtoRequest);
        assertThat(savedAuthor).isNotNull();
    }

    @DisplayName("JUnit test for readAllAuthor method")
    @Test
    void getAllAuthorsTest(){
        AuthorDtoRequest authorDtoRequest1 = new AuthorDtoRequest
                .AuthorDtoRequestBuilder("TestAuthor_2")
                .build();
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDtoRequest);
        AuthorModel authorModel1 = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDtoRequest1);
        given(repository.readAll()).willReturn(List.of(authorModel, authorModel1));

        List<AuthorDtoResponse> authorList = service.readAll();

        assertThat(authorList).isNotNull();
        assertThat(authorList).hasSize(2);
    }

    @DisplayName("JUnit test for getAuthorById method")
    @Test
    void getAuthorByIdTest(){
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDtoRequest);
        given(repository.readById(1L)).willReturn(Optional.of(authorModel));
        AuthorDtoResponse savedAuthor = service.readById(authorModel.getId());
        assertThat(savedAuthor).isNotNull();
    }

    @DisplayName("JUnit test for updateAuthor method")
    @Test
    void updateAuthorTest(){
        authorDtoRequest.setName("UpdateName");
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDtoRequest);
        given(repository.update(authorModel)).willReturn(authorModel);
        given(repository.existById(any())).willReturn(true);
        AuthorDtoResponse updatedAuthor = service.update(authorDtoRequest);
        assertThat(updatedAuthor.getName()).isEqualTo("UpdateName");
    }

    @DisplayName("JUnit test for deleteAuthor method")
    @Test
    void deleteAuthorTest(){
        given(repository.deleteById(1L)).willReturn(true);
        given(repository.existById(any())).willReturn(true);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
