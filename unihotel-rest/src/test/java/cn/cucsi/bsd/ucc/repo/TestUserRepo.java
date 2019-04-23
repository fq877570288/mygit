/*
package cn.cucsi.bsd.ucc.repo;

import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.data.repo.UccUserRepository;
import cn.cucsi.bsd.ucc.data.specs.UccUserSpecs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestUserRepo.class)
@EntityScan(basePackages = "cn.cucsi.bsd.ucc.data.domain")
@ActiveProfiles("dev")
public class TestUserRepo {

    @Autowired
    private UccUserRepository uccUserRepository;

    @Test
    public void findByUserName(){
        uccUserRepository.findByUserName("ABC");
    }

    @Test
    public void save(){
        UccUsers uccUsers = new UccUsers();
        uccUsers.setUserId("test1");
        uccUsers.setUserStatus(1);
        uccUsers.setUserName("test1");
        uccUsers.setPassword("test1");
        uccUsers.setDomainId("domain1");
        uccUsers.setLockTime(new java.util.Date());
        uccUserRepository.save(uccUsers);

//        uccUserRepository.save()
    }

    @Test
    public void findAll(){
        Sort sort = new Sort(Sort.Direction.DESC, "regTime");
        Pageable pageable = new PageRequest(0, 15, sort);
        Page<UccUsers> pages = uccUserRepository.findAll(UccUserSpecs.userNameLike("test2"), pageable);
        if(null != pages){
            for(UccUsers uccUser: pages.getContent()){
                System.out.println(uccUser.getUserName());
            }
        }
    }

}
*/
