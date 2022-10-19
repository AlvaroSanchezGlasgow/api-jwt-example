package com.api.portal.dao;

import com.api.portal.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserDao {


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "company", column = "company"),
            @Result(property = "registrationDate", column = "registration_date"),
            @Result(property = "isActive", column = "isActive")
    })
    @Select("SELECT * FROM T_USERS WHERE UPPER(username) = UPPER(#{username}) and isActive = 'Y'")
    User findByUsernameLogin(@Param("username") String username);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "registrationDate", column = "registration_date"),
            @Result(property = "isActive", column = "isActive")
    })
    @Select("SELECT * FROM T_USERS WHERE UPPER(username) = UPPER(#{username})")
    User findUserByUsername(@Param("username") String username);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "registrationDate", column = "registration_date"),
            @Result(property = "isActive", column = "isActive")
    })
    @Select("SELECT * FROM T_USERS WHERE id = #{id}")
    User fidUserById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "registrationDate", column = "registration_date"),
            @Result(property = "isActive", column = "isActive")
    })
    @Select("SELECT * FROM T_USERS")
    List<User> findAll();

    @Insert("INSERT INTO T_USERS(username, password, firstName, lastName, email, comment, isActive, registration_date) " +
            " VALUES (UPPER(#{username}), #{password}, UPPER(#{firstName}),UPPER(#{lastName}), #{email}, UPPER(#{comment}), UPPER(#{isActive}),#{registrationDate})")
    //@SelectKey(statement = "call identity()", keyProperty = "id", before = true, resultType = int.class)
    int save(User user);

    @Update("UPDATE T_USERS SET username=#{username},firstName=#{firstName},lastName=#{lastName},email=#{email},comment=#{comment},isActive=#{isActive} WHERE id =#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM T_USERS WHERE id = #{id}")
    void deleteUser(@Param("id") int id);


}
