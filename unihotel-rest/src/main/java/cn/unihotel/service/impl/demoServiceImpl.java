package cn.unihotel.service.impl;

import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.entry_info;
import cn.unihotel.entry.request.entry_info_search;
import cn.unihotel.mapper.PbxExtsMapper;
import cn.unihotel.service.demoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "demoService")
public class demoServiceImpl implements demoService {
    @Autowired
    PbxExtsMapper pbxExtsMapper;

    @Override
    public GeneralResponsePage selectAll(entry_info_search entry_info_search) {
        PageHelper.startPage(entry_info_search);
        Page<entry_info> list = pbxExtsMapper.selectAll();
        return new GeneralResponsePage<>("0", "查询成功", list);
    }

}
