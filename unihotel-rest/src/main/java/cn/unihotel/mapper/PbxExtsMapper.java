package cn.unihotel.mapper;

import cn.unihotel.entry.entry_info;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PbxExtsMapper {
    Page<entry_info> selectAll();
}
