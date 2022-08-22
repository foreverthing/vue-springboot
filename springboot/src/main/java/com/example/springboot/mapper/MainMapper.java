package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MainMapper {
    @Select("SELECT * FROM account WHERE id = #{id} ")
    User getUserById(int id);

    @Select("select * from account where username = #{username}")
    User getPasswordByUsername(String username);

    @Select("select * from account ")
    List<User> getAllUserData();

//    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into account(username,password,address,sex,email) values(#{username},#{password},#{address},#{sex},#{email})")
    void addUser(User user);

    @Update("update account set username =#{username},sex =#{sex},address =#{address},email =#{email},password =#{password} where id =#{id}")
     void editUser(User user);

    @Delete("delete  from account where id =#{id}")
    void deleteUser(int id);
//    @Param("username") String username ,@Param("password") String password,@Param("id") int id
}
