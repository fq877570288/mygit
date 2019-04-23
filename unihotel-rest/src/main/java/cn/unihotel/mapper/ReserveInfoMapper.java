package cn.unihotel.mapper;

import cn.unihotel.entry.ReserveInfo;
import cn.unihotel.entry.request.Reserve_info_search;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

/**
 * 预定系统Mapper接口
 * create by 付琦 2019-04-20
 */
@Repository
public interface ReserveInfoMapper {
    /**
     * 删除
     * @param reserveId
     * @return
     */
    int deleteByPrimaryKey(String reserveId);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(ReserveInfo record);

    ReserveInfo selectByPrimaryKey(Integer reserveId);

    /**
     * 全查
     * @param reserve_info_search
     * @return
     */
    Page<ReserveInfo> selectAll(Reserve_info_search reserve_info_search);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(ReserveInfo record);
}