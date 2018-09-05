package cn.cucsi.bsd.ucc.repo;

import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import cn.cucsi.bsd.ucc.data.repo.UccDomainRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestUserRepo.class)
@EntityScan(basePackages = "cn.cucsi.bsd.ucc.data.domain")
@ActiveProfiles("dev")
public class TestDomain {

    @Autowired
    private UccDomainRepository uccDomainRepository;

    @Test
    public void save(){
        UccDomain uccDomain = new UccDomain();
//        uccDomain.setDomainId("domain1");
        uccDomain.setDomainName("domain2");
        this.uccDomainRepository.save(uccDomain);
    }
}
