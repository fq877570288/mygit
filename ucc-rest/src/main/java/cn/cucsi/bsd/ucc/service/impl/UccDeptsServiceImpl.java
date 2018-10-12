package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.NgtDeptSearch;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.common.mapper.UccDeptsMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.repo.UccDeptsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDeptsSpecs;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccDeptsServiceImpl implements UccDeptsService {
    @Autowired
    private UccDeptsService uccDeptsService;
    @Autowired
    private UccDeptsRepository uccDeptsRepository;
    @Autowired
    private UccDeptsMapper uccDeptsMapper;

    public static final Map<String, UccDepts> deptNameMap = new HashMap<String, UccDepts>();//组织机构缓存表
    public static final Map<String, UccDepts> deptMap = new HashMap<String, UccDepts>();//组织机构缓存表

    @Override
    public Page<UccDepts> findAll(UccDeptsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "deptCreateTime");
        Pageable pageable = new PageRequest(0, 999999, sort);
        return uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccDepts findOne(String deptId) {
        return this.uccDeptsRepository.findOne(deptId);
    }

    @Override
    public UccDepts save(UccDepts uccCustomers) {
        return this.uccDeptsRepository.save(uccCustomers);
    }

    @Override
    public Boolean delete(String deptId) {
        this.uccDeptsRepository.delete(deptId);
        return true;
    }

    @Override
    public Page<UccDepts> findAllTree(UccDeptsCriteria criteria) {

        Pageable pageable = new PageRequest(0, 999999);
        Sort sort = new Sort(Sort.Direction.ASC, "deptLevel");
        Page<UccDepts> pages = this.uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);

        return pages;
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(String deptId) throws Exception {
        uccDeptsMapper.clearUsersDeptsByDeptId(deptId);
        return uccDeptsMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public int insert(UccDepts record) throws Exception {
        return uccDeptsMapper.insert(record);
    }

    @Override
    public UccDepts selectByPrimaryKey(String deptId) throws Exception {
        return uccDeptsMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<UccDepts> selectAll() throws Exception {
        return uccDeptsMapper.selectAll();
    }

    @Override
    public UccDepts selectByNameInCache(String deptName) throws Exception {

        if(deptName==null){
            return null;
        }
        if(deptNameMap.isEmpty()){
            this.putAllToMap();
        }
        if(deptNameMap.containsKey(deptName)){
            return deptNameMap.get(deptName);
        }
        return null;
    }

    private void putAllToMap() throws Exception {
        List<UccDepts> deptList = uccDeptsMapper.selectAll();
        if(deptList==null || deptList.isEmpty()) return;

        deptNameMap.clear();
        deptMap.clear();

        for(UccDepts dept: deptList){
            deptNameMap.put(dept.getDeptName(), dept);
            deptMap.put(dept.getDeptId(), dept);
        }
    }

    @Override
    public UccDepts selectByIdInCache(String deptId) throws Exception {

        if(deptId==null){
            return null;
        }
        if(deptMap.isEmpty()){
            this.putAllToMap();
        }
        if(deptMap.containsKey(deptId)){
            return deptMap.get(deptId);
        }else{
            UccDepts _dept = this.selectByPrimaryKey(deptId);
            if(_dept != null){
                deptMap.put(deptId, _dept);
                deptNameMap.put(_dept.getDeptName(), _dept);
                return _dept;
            }
        }
        return null;
    }

    @Override
    public void putDeptToCache(UccDepts UccDepts) throws Exception {

        if(UccDepts==null){
            return;
        }
        deptNameMap.put(UccDepts.getDeptName(), UccDepts);
        deptMap.put(UccDepts.getDeptId(), UccDepts);
    }

    @Override
    public List<UccDepts> selectByUserId(String userId) throws Exception {
        return uccDeptsMapper.selectByUserId(userId);
    }

    @Override
    public List<UccDepts> selectDeptsByUserId(String userId) {
        // TODO Auto-generated method stub
        return uccDeptsMapper.selectDeptsByUserId(userId);
    }

    @Override
    public int updateByPrimaryKey(UccDepts record) throws Exception {
        int i = uccDeptsMapper.updateByPrimaryKey(record);
        this.putAllToMap();
        return i;
    }

    @Override
    public int updateSimpleByPrimaryKey(UccDepts record) throws Exception {
        int i = uccDeptsMapper.updateSimpleByPrimaryKey(record);
        this.putAllToMap();
        return i;
    }

    @Override
    public int selectSubCountsByPrimaryKey(String deptId) throws Exception {
        return uccDeptsMapper.selectSubCountsByPrimaryKey(deptId);
    }

    @Override
    public List<UccDepts> selectDidsByUserId(String userId,String domainId) throws Exception {
        return uccDeptsMapper.selectDidsByUserId(userId,domainId);
    }

    @Override
    public int clearUsersDeptsByDeptId(String deptId) throws Exception {
        return uccDeptsMapper.clearUsersDeptsByDeptId(deptId);
    }

    @Override
    public int clearUsersDeptsByUserId(String userId) throws Exception {
        return uccDeptsMapper.clearUsersDeptsByUserId(userId);
    }

    @Override
    @Transactional
    public int insertUserDepts(String userId, String[] depts) throws Exception {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("userId", userId);
        map.put("list", depts);
        uccDeptsMapper.clearUsersDeptsByUserId(userId);
        int i = uccDeptsMapper.insertUserDepts(map);
        this.putAllToMap();
        return i;
    }

    @Override
    public List<UccDepts> formatDept(List<UccDepts> list){

        if(list==null|| list.isEmpty()){
            return null;
        }
        int maxLevel = -1;
        int minLevel = 999999;
        LinkedHashMap<String, UccDepts> map1 = new LinkedHashMap<String, UccDepts>();
        HashMap<Integer, List<UccDepts>> map2 = new HashMap<Integer, List<UccDepts>>();
        for (UccDepts dp : list) {
            map1.put(dp.getDeptId(), dp);
            maxLevel = maxLevel < dp.getDeptLevel() ? dp.getDeptLevel() : maxLevel;
            minLevel = minLevel > dp.getDeptLevel() ? dp.getDeptLevel() : minLevel;
            if (!map2.containsKey(dp.getDeptLevel())) {
                map2.put(dp.getDeptLevel(), new ArrayList<UccDepts>());
            }
            map2.get(dp.getDeptLevel()).add(dp);
        }
        for (int i = maxLevel; i != 0; i--) {
            for (UccDepts dp : map2.get(i)) {
                UccDepts p = map1.get(dp.getDeptPid());
                if(p == null){
                    continue;
                }
                if (p.getSubDepts() == null) {
                    p.setSubDepts(new ArrayList<UccDepts>());
                }
                p.getSubDepts().add(dp);
            }
        }
        return map2.get(minLevel);
    }

    @Override
    public String selectMaxDeptID() throws Exception {
        return uccDeptsMapper.selectMaxDeptID();
    }

    @Override
    public void insertGroup(Map<String, Object> UccDeptsNewmap) throws Exception {
        uccDeptsMapper.insertGroup(UccDeptsNewmap);
        this.putAllToMap();
    }

    private void selectChildren(List<UccDepts> UccDeptsAndChildList, List<UccDepts> parentList){
        if(parentList==null || parentList.isEmpty()) return;

        String deptIds = Joiner.on(",").join(com.google.common.collect.Collections2.transform(parentList, new Function<UccDepts, String>() {
            @Override
            public String apply(UccDepts UccDepts) {
                return UccDepts.getDeptId();
            }
        }));
        List children = this.uccDeptsMapper.selectChildren(deptIds);
        if(children!=null && !children.isEmpty()){
            UccDeptsAndChildList.addAll(children);
            this.selectChildren(UccDeptsAndChildList, children);
        }
    }

    @Override
    public String selectDeptIdAndChildIds(String userId) throws Exception {

        List<UccDepts> UccDeptsList = uccDeptsMapper.selectByUserId(userId);
        String deptId = "0l";
        List<UccDepts> UccDeptsAndChildList = new ArrayList<>();
        if(UccDeptsList != null && !UccDeptsList.isEmpty()){
            if(UccDeptsList.get(0) != null){
                deptId = UccDeptsList.get(0).getDeptId();
            }
        }
        UccDepts dept = new UccDepts();
        dept.setDeptId(deptId);
        UccDeptsAndChildList.add(dept);

        List children = this.uccDeptsMapper.selectChildren(deptId+"");
        if(children!=null && !children.isEmpty()){
            UccDeptsAndChildList.addAll(children);
            selectChildren(UccDeptsAndChildList, children);
        }

        if(UccDeptsAndChildList.isEmpty()) return "";

        return Joiner.on(",").join(com.google.common.collect.Collections2.transform(UccDeptsAndChildList, new Function<UccDepts, String>() {
                    @Override
                    public String apply(UccDepts UccDepts) {
                        return UccDepts.getDeptId();
                    }
                }
        ));
    }
    @Override
    public List<UccDepts> selectMesh(UccDepts dept) {
        // TODO Auto-generated method stub
        return uccDeptsMapper.selectMesh(dept);
    }

    @Override
    public List<UccDepts> selectAllInCache(UccDeptsCriteria search) throws Exception{
        List<UccDepts> UccDepts = new ArrayList<>();
        if(deptMap == null || deptMap.isEmpty()){
            putAllToMap();
        }
        if(search!=null){
            if(search.getDeptLevel() != null && search.getDeptLevel() != 0
                    && search.getDeptIdAndChildIds() != null
                    && !search.getDeptIdAndChildIds().isEmpty()){
                List<String> ids = Arrays.asList(search.getDeptIdAndChildIds().split(","));
                for(Map.Entry<String, UccDepts> entry: deptMap.entrySet()){
                    if(entry.getValue().getDeptLevel() == search.getDeptLevel()
                            && ids.contains(entry.getValue().getDeptId().toString())){
                        UccDepts.add(entry.getValue());
                    }
                }
            }
        }
        return UccDepts;
    }

    /**
     * select 获取网格名称 id
     * add by wangxiaoyu
     * 2018-10-12
     * 备注：入参：centerID 、userId 、domainId
     */
    @Override
    public String queryMesh(NgtDeptSearch search) throws Exception {
        //String centerID = search.getCenterID()== null?"":search.getCenterID();
        try {
            search.setDeptLevel(1);
            //search.setDeptPid(centerID);
            List<UccDepts> deptCenters = uccDeptsMapper.selectByNgtDeptSearch(search);
            if (deptCenters == null || deptCenters.isEmpty()) {
                search.setDeptLevel(2);
                //search.setDeptPid(centerID);
                List<UccDepts> depts = uccDeptsMapper.selectByNgtDeptSearch(search);
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(depts);
                return json;
            } else {
                List<UccDepts> depts = new ArrayList<>();
                for (UccDepts _tmpCenterID : deptCenters) {
                    search.setDeptLevel(2);
                    //search.setDeptPid(_tmpCenterID.getDeptId());
                    List<UccDepts> deptTmps = uccDeptsMapper.selectByNgtDeptSearch(search);
                    if (deptTmps != null) {
                        depts.addAll(deptTmps);
                    }
                }
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(depts);
                return json;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * select 获取包区名称 id
     * add by wangxiaoyu
     * 2018-10-12
     * 备注：入参：meshID、userId 、domainId
     */
    @Override
    public String queryArea(NgtDeptSearch search) throws Exception {
        String meshID = search.getMeshID()==null?"":search.getMeshID();
        try {
            search.setDeptLevel(3);
            search.setDeptPid(meshID);
            List<UccDepts> depts = uccDeptsMapper.selectByNgtDeptSearch(search);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(depts);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * select 获取发展部门名称 id
     * add by wangxiaoyu
     * 2018-10-12
     * 备注：入参：areaID、userId 、domainId
     */
    @Override
    public String queryDevelopment(NgtDeptSearch search) throws Exception {
        String areaID = search.getAreaID()==null?"":search.getAreaID();
        try {
            search.setDeptLevel(4);
            search.setDeptPid(areaID);
            List<UccDepts> depts = uccDeptsMapper.selectByNgtDeptSearch(search);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(depts);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void test(UccDepts rootDepts, List<UccDepts> source, List<UccDepts> all){
        List<UccDepts> ownDepts = source.stream().filter(row-> row.getDeptPid().equals(rootDepts.getDeptId())).collect(Collectors.toList());
        if(MyUtils.isBlank(ownDepts)){
            return;
        }
        all.addAll(ownDepts);
        for(UccDepts o: ownDepts){
            test(o, source, all);
        }

    }


    /**
     * 查询用户关联的部门及其子部门
     */
    public List<UccDepts> queryChildrenByDepts(String domainId,List<UccDepts> list){
        UccDeptsCriteria search = new UccDeptsCriteria();
        search.setDomainId(domainId);
        ResultBean<List<UccDepts>> bean = new PageResultBean(this.uccDeptsService.findAllTree(search));
        List<UccDepts> source = bean.getData();
        List<UccDepts> all = new ArrayList<>();
        try{
            for(UccDepts filter: list){
                UccDepts rootDept = source.stream().filter(row-> row.getDeptId().equals(filter.getDeptId())).findFirst().get();
                if(rootDept != null){
                    all.add(rootDept);
                    this.test(rootDept, source, all);
                }
            }
            for(UccDepts uccDepts:all){
                System.out.println(uccDepts.getDeptId()+" "+uccDepts.getDeptPid()+" "+list.size());
            }
        }catch (Exception e){
            System.out.println("询用户关联的部门及其子部门失败！");
            e.printStackTrace();
        }
        return all;
    }
}
