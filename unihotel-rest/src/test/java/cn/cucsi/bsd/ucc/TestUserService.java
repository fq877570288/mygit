/*
package cn.cucsi.bsd.ucc;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ComponentScan
@SpringBootTest(classes = TestUserService.class)
@EntityScan(basePackages = "cn.cucsi.bsd.ucc.data.domain")
@ActiveProfiles("dev")
public class TestUserService {
    @Autowired
    private UccUserService uccUserService;
    @Test
    public void findAll(){
//        Sort sort = new Sort(Sort.Direction.DESC, "regTime");
//        Pageable pageable = new PageRequest(0, 15, sort);

        UccUserCriteria criteria = new UccUserCriteria();
        criteria.setMobile("18603651384");
        criteria.setUserName("lanfh");

        Page<UccUsers> pages = uccUserService.findAll(criteria);

        if(null != pages){
            for(UccUsers uccUser: pages.getContent()){
                System.out.println(uccUser.getUserName());
            }
        }

    }
}
*/
