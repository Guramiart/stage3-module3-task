package com.mjc.school;

import com.mjc.school.repository.model.TagModel;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import com.mjc.school.service.impl.TagService;
import com.mjc.school.service.mapper.TagMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private TagRepository repository;

    @InjectMocks
    private TagService service;

    private TagDtoRequest tagDtoRequest;

    @BeforeEach
    public void setup(){
        tagDtoRequest = new TagDtoRequest
                .TagDtoRequestBuilder("TestTag")
                .id(1L)
                .build();
    }

    @DisplayName("JUnit test for createTag method")
    @Test
    public void createTagTest(){
        TagModel tagModel = TagMapper.INSTANCE.tagDtoToTag(tagDtoRequest);
        given(repository.create(tagModel)).willReturn(tagModel);
        TagDtoResponse savedTag = service.create(tagDtoRequest);
        assertThat(savedTag).isNotNull();
    }

    @DisplayName("JUnit test for readAllTag method")
    @Test
    public void getAllTagsTest(){
        TagDtoRequest tagDtoRequest1 = new TagDtoRequest
                .TagDtoRequestBuilder("TestTag_1")
                .build();
        TagModel tagModel = TagMapper.INSTANCE.tagDtoToTag(tagDtoRequest);
        TagModel tagModel1 = TagMapper.INSTANCE.tagDtoToTag(tagDtoRequest1);
        given(repository.readAll()).willReturn(List.of(tagModel, tagModel1));

        List<TagDtoResponse> tagList = service.readAll();

        assertThat(tagList).isNotNull();
        assertThat(tagList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getTagById method")
    @Test
    public void getTagByIdTest(){
        TagModel tagModel = TagMapper.INSTANCE.tagDtoToTag(tagDtoRequest);
        given(repository.readById(1L)).willReturn(Optional.of(tagModel));
        TagDtoResponse savedTag = service.readById(tagModel.getId());
        assertThat(savedTag).isNotNull();
    }

    @DisplayName("JUnit test for updateTag method")
    @Test
    public void updateTagTest(){
        tagDtoRequest.setName("UpdateTag");
        TagModel tagModel = TagMapper.INSTANCE.tagDtoToTag(tagDtoRequest);
        given(repository.update(tagModel)).willReturn(tagModel);
        given(repository.existById(any())).willReturn(true);
        TagDtoResponse updatedTag = service.update(tagDtoRequest);
        assertThat(updatedTag.getName()).isEqualTo("UpdateTag");
    }

    @DisplayName("JUnit test for deleteTag method")
    @Test
    public void deleteTagTest(){
        given(repository.deleteById(1L)).willReturn(true);
        given(repository.existById(any())).willReturn(true);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
