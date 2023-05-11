package ru.clevertec.statkevich.giftcertificatesservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagVo;

import java.util.List;

@Mapper
public interface TagMapper {

    Tag toEntity(TagCreateUpdateDto source);

    TagVo toDto(Tag source);

    void map(TagCreateUpdateDto source, @MappingTarget Tag target);

    List<TagVo> toDtoList(List<Tag> source);
}
