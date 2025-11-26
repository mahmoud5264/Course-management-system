package com.example.cms.repositories;

import com.example.cms.models.User;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RegisterBeanMapper(User.class)
public interface UserRepository extends SqlObject {

    @SqlQuery("Select * from users")
    List<User> getAllUsers();

    @SqlQuery("SELECT EXISTS (select  1 FROM users WHERE username = :username)")
    Boolean checkUserExistByUsername(@Bind String username);

    @SqlQuery("SELECT EXISTS (select  1 FROM users WHERE email = :email)")
    Boolean checkUserExistByEmail(@Bind String email);

    @SqlQuery("SELECT * FROM users where username = :username")
    User getUserByUsername(@Bind String username);

    @SqlQuery("SELECT * FROM users where email = :email")
    User getUserByEmail(@Bind String email);

    @SqlQuery("SELECT * FROM users where id = :id")
    User getUserById(@Bind UUID id);


    @SqlUpdate("insert into users (id, username, email, password, usertype) values (gen_random_uuid(), :username, :email, :password, 'Student')")
    int addUser(@Bind String username, @Bind String email, @Bind String password);


}
