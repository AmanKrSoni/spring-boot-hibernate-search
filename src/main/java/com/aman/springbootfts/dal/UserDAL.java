package com.aman.springbootfts.dal;

import com.aman.springbootfts.entity.User;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAL {

    private static final Logger logger = LoggerFactory.getLogger(UserDAL.class);
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUserByFirstName(String userFirstName) throws InterruptedException {
        Query query = getQueryBuilder()
                .keyword()
                .wildcard()
                .onField("userFirstName")
                .matching(userFirstName.toLowerCase() + "*")
                .createQuery();
        List<User> userList = getFullTextSearchQuery(query).setSort(new Sort((new SortField("userFirstName", SortField.Type.STRING, false)))).getResultList();
        logger.info("Size : {}", userList.size());
        userList.stream().forEach(u -> logger.info("UserName : {} " , u.getUserFirstName()));
        return userList;
    }


    private FullTextQuery getFullTextSearchQuery(Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        return fullTextEntityManager.createFullTextQuery(luceneQuery, User.class);
    }

    private QueryBuilder getQueryBuilder() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();
    }
}
