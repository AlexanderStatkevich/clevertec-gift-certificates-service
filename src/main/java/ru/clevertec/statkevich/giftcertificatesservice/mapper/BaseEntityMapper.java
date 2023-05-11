package ru.clevertec.statkevich.giftcertificatesservice.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.Mapper;
import org.mapstruct.TargetType;

@Mapper
public abstract class BaseEntityMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> T toEntity(Long id, @TargetType Class<T> clazz) {
        return entityManager.find(clazz, id);
    }
}
