package ru.clevertec.statkevich.giftcertificatesservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagVo;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.TagMapper;
import ru.clevertec.statkevich.giftcertificatesservice.service.TagService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/tags")
public class TagsController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody TagCreateUpdateDto tagCreateUpdateDto) {
        Tag tag = tagMapper.toEntity(tagCreateUpdateDto);
        return tagService.create(tag);
    }


    @GetMapping(path = "/{id}")
    public TagVo findById(@PathVariable Long id) {
        Tag tag = tagService.findById(id);
        return tagMapper.toDto(tag);
    }

    @GetMapping
    public List<TagVo> findAll() {
        List<Tag> tagList = tagService.findAll();
        return tagMapper.toDtoList(tagList);
    }


    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody TagCreateUpdateDto tagCreateUpdateDto) {
        tagService.update(id, tagCreateUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}
