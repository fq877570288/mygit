package cn.unihotel.service;

import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.request.entry_info_search;

public interface demoService {

     GeneralResponsePage selectAll(entry_info_search entry_info_search);
}
