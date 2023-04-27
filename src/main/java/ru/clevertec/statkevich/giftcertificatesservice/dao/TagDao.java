package ru.clevertec.statkevich.giftcertificatesservice.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;

@Repository
public class TagDao extends BaseDao<Tag> implements ITagDao {

    public TagDao(SessionFactory sessionFactory, Class<Tag> clazz) {
        super(sessionFactory, clazz);
    }
}
