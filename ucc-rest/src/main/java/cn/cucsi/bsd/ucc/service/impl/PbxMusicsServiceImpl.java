package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxMusicsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxMusics;
import cn.cucsi.bsd.ucc.data.repo.PbxMusicsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxMusicsSpecs;
import cn.cucsi.bsd.ucc.service.PbxMusicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service(value = "PbxMusicsService")
@Transactional
public class PbxMusicsServiceImpl implements PbxMusicsService {
    @Autowired
    PbxMusicsRepository repository;
        @Override
        public Page<PbxMusics> findAll(PbxMusicsCriteria criteria) {
            Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
            Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
            return repository.findAll(PbxMusicsSpecs.createSpec(criteria),pageable);
    }

    @Override
    public PbxMusics findOne(String musicId) {
        return repository.findOne(musicId);
    }



    @Override
    public List<PbxMusics> findAllOfNoPage(PbxMusicsCriteria search) {
        return repository.findAll(PbxMusicsSpecs.createSpec(search));
    }

    @Override
    public PbxMusics save(byte[] fileBox,PbxMusics pbxMusics) {

        PbxMusics save= repository.save(pbxMusics);
        if(fileBox!=null && fileBox.length>0){
            String filePath = "musicTest\\";
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            try {
                FileOutputStream out = new FileOutputStream(filePath+save.getMusicId()+".mp3");
                out.write(fileBox);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return save;
    }

    @Override
    public PbxMusics updateById(PbxMusics PbxMusics) {

  //      return repository.updateById( PbxMusics.getDescription(),PbxMusics.getUpdatedPerson(),PbxMusics.getUpdatedTime(),PbxMusics.getMusicId());
        return repository.save(PbxMusics);
    }

    @Override
    public Boolean delete(String musicId) {
        String filePath = "musicTest\\";
        File file = new File(filePath+musicId+".mp3");
        if(file.exists()){
            file.delete();
        }
        this.repository.delete(musicId);
        return true;
    }
}
