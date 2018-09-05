package cn.cucsi.bsd.ucc.repo;

import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.BaseDictPK;
import cn.cucsi.bsd.ucc.data.repo.BaseDictRepository;
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
public class TestBaseDictRepo {

    @Autowired
    private BaseDictRepository baseDictRepository;

    @Test
    public void save(){
        BaseDict baseDict = new BaseDict();
        baseDict.setDictKey(101);
        baseDict.setDictValue("value1");
        baseDict.setDictType("type1");

        this.baseDictRepository.save(baseDict);
    }

    @Test
    public void findById(){
        BaseDictPK pk = new BaseDictPK();
        pk.setDictKey(101);
        pk.setDictType("type1");
//        Assert.assertTrue(this.baseDictRepository.findById(pk).get().getDictValue().equals("value1"));
    }
}
