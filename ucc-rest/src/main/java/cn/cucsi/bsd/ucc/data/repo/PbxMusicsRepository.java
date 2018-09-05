package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.PbxMusics;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxMusicsRepository extends PagingAndSortingRepository<PbxMusics, String>, JpaSpecificationExecutor {

   // @Modifying
   /* @Query("update PbxMusics set description=?1 , updatedPerson=?2, updatedTime=?3 where musicId=?4")
    int updateById(String description, String updated_person, Date updated_time,String music_id);*/
    /*@Query("update PbxMusics set description=?1 , updatedTime=?3 where musicId=?4")
    int updateById(String description, Date updated_time,String music_id);*/
}
