package com.example.domain;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO usuario (userName, password, age) VALUES (#{userName}, #{password}, #{age})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(User entity);

    @Update("UPDATE usuario SET userName = #{userName}, password = #{password}, age = #{age} WHERE ID = #{id}")
    void update(User entity);

    @Delete("DELETE FROM usuario WHERE id = #{id}")
    void deleteById(long id);

    @Select("SELECT * FROM usuario WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "password", column = "password"),
            @Result(property = "age", column = "age")
    })
    User findById(long id);

    @Select("SELECT * FROM usuario")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "password", column = "password"),
            @Result(property = "age", column = "age")
    })
    List<User> findAll();
}