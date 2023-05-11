package ru.clevertec.statkevich.giftcertificatesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = """
            WITH cert AS (SELECT customer_id, SUM(price) AS price_sum, ARRAY_AGG(certificate_id) AS certificate_ids
                          FROM orders
                          GROUP BY customer_id
                          ORDER BY price_sum DESC
                          LIMIT 1),
                 cert_unnest AS (SELECT UNNEST(certificate_ids)
                                 FROM cert)
            SELECT t.*
            FROM tags t
                     INNER JOIN certificate_tags ct ON t.id = ct.tag_id
                     INNER JOIN certificates c ON c.id = ct.certificate_id
            WHERE c.id IN (SELECT * FROM cert_unnest);
            """, nativeQuery = true)
    Tag findMostUsedTag();
}
