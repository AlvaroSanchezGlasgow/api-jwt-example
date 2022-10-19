package com.api.portal.dao;

import com.api.portal.dto.ClientDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientDao {


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "website", column = "website"),
            @Result(property = "registrationDate", column = "registration_date"),
            @Result(property = "clientManager", column = "client_manager")
    })
    @Select("SELECT * from T_CLIENTS order by T_CLIENTS.id asc")
    List<ClientDTO> findAll();


    @Delete("DELETE FROM T_CLIENTS WHERE id = #{id}")
    void delete(@Param("id") int id);

    @Insert("INSERT INTO T_CLIENTS(name, website, client_manager,registration_date) " +
            " VALUES (UPPER(TRIM(#{name})), TRIM(#{website}), UPPER(TRIM(#{clientManager})), #{registrationDate})")
    //@SelectKey(statement = "call identity()", keyProperty = "id", before = true, resultType = int.class)
    void save(ClientDTO clientDTO);

    @Update("UPDATE T_CLIENTS SET name=UPPER(TRIM(#{name})),website=TRIM(#{website}),client_manager=UPPER(TRIM(#{clientManager})) WHERE id =#{id}")
    void update(ClientDTO ClientDTO);
}
