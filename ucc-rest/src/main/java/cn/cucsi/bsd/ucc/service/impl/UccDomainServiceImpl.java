package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import cn.cucsi.bsd.ucc.data.domain.UccPermissions;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import cn.cucsi.bsd.ucc.data.repo.UccDomainRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDomainSpecs;
import cn.cucsi.bsd.ucc.service.UccDomainService;
import cn.cucsi.bsd.ucc.service.UccPermissionsService;
import cn.cucsi.bsd.ucc.service.UccRolesService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Transactional
@Service
public class UccDomainServiceImpl implements UccDomainService {
    @Autowired
    UccDomainRepository uccDomainRepository;

    @Autowired
    UccPermissionsService uccPermissionsService;

    @Autowired
    UccRolesService uccRolesService;

    private Random r = new Random();

    @Override
    public Page<UccDomain> findAll(UccDomainCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "permissionId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
        return uccDomainRepository.findAll(UccDomainSpecs.createSpec(criteria), pageable);

    }

    @Override
    public UccDomain findOne(String domainId) {
        return this.uccDomainRepository.findOne(domainId);
    }

    @Override
    public UccDomain save(UccDomain uccDomain) {
        return this.uccDomainRepository.save(uccDomain);
    }

    @Override
    public Boolean delete(String domainId) {
        this.uccDomainRepository.delete(domainId);
        return true;
    }

    @Override
    public Boolean updateStatusById(String domainId, String status) {
        Integer result = this.uccDomainRepository.updateStatusById(domainId, status);
        if(result == 0) return false;
        return true;
    }

    @Override
    public List<UccDomain> findAllOfNoPage(UccDomainCriteria search) {
        return uccDomainRepository.findAll(UccDomainSpecs.createSpec(search));
    }

    @Override
    public ResultBean<Object> createDomin(UccDomain uccDomain){

//        uccDomain.setDomainId(ss() + "-" + ss() + "-" + ss());
        uccDomain.setDomainId(ss());
        uccDomain.setCreatedTime(new Date());
        UccDomain domain = save(uccDomain);
        if(domain != null){
            String uccDomainId = domain.getDomainId();
            //给新增的租户创建一个对应的管理员角色
            UccRoles roles = new UccRoles();
            roles.setDomainId(uccDomainId);
            roles.setRoleName("管理员");
            roles.setCreatedNickName("平台管理员");
            roles.setCreatedTime(new Date());
            roles.setCreatedUserId("uccAdmin");
            roles.setCreatedUserName("uccAdmin");

            List<UccPermissions> permissionsList = uccPermissionsService.findAll(null);
            if(permissionsList != null && permissionsList.size() > 0){

                Collection<UccPermissions> rolesPermissions = new ArrayList<>();
                rolesPermissions = permissionsList;
                roles.setUccPermissions(rolesPermissions);
            }
            uccRolesService.save(roles);
        }

        return new ResultBean<Object>();

    }

    private String ss(){
        String sb = "";
        for(int i = 0; i < 6; i++)  {
            String s = randomStringChar() + "";
            sb += s;
        }
        return sb;
    }

    // 随机生成一个字母
    private char randomStringChar () {
        String stringCodes  = "ABCDEFGHJKMNPQRSTUVWXYZ";

        int index = r.nextInt(stringCodes.length());
        return stringCodes.charAt(index);
    }

}
