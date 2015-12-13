package com.gas.dao;

import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gas.model.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);

    @Where(clause = "roleid <> 3")
    Page<User> findAll(Pageable pageable);


    @Query(value = "select * from User where roleid = 3 order by :sort limit :offset,:size ", nativeQuery = true)
    List<User> findCustomerByPagenation(@Param("offset") int offset, @Param("size") int size,
            @Param("sort") String sort);

    List<User> findAll();

    User save(User user);

    User findByCardid(String id);
    @Query(value = "select name from User where id = :id ", nativeQuery = true)
    String findNameById(@Param("id") Long id);

    @Query(value = "select phone from User where id = :id ", nativeQuery = true)
    String findPhoneById(@Param("id") Long id);
    void delete(User user);

    User findByIdcard(String id);

    User findByPhone(String phone);

    List<Long> findAllIdByCompanyId(Long companyid);
}
