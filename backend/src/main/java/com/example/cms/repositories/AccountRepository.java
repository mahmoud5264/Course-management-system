package com.example.cms.repositories;

import com.example.cms.models.Account;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RegisterBeanMapper(Account.class)
public interface AccountRepository extends SqlObject {

    @SqlQuery("Select * from account")
    List<Account> getAllAccounts();
}
