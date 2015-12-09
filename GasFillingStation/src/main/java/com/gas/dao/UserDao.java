package com.gas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gas.model.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);

    @Query(value = "select * from User where roleid <> 3 order by :sort limit :offset,:size ", nativeQuery = true)
    List<User> findEmployeeByPagenation(@Param("offset") int offset, @Param("size") int size,
            @Param("sort") String sort);

    @Query(value = "select * from User where roleid = 3 order by :sort limit :offset,:size ", nativeQuery = true)
    List<User> findCustomerByPagenation(@Param("offset") int offset, @Param("size") int size,
            @Param("sort") String sort);

    @Query(value = "select count(1) from User where roleid = 3 ", nativeQuery = true)
    int customerCount();

    @Query(value = "select count(1) from User where roleid <> 3", nativeQuery = true)
    int employeeCoun();

    List<User> findAll();

    User save(User user);

    User findByCardid(String id);
    @Query(value = "select name from User where id = :id ", nativeQuery = true)
    String findNameById(@Param("id") Long id);

    @Query(value = "select phone from User where id = :id ", nativeQuery = true)
    String findPhoneById(@Param("id") Long id);
    void delete(User user);
}
