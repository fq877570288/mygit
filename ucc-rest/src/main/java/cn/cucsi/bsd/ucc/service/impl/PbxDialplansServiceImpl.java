package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxDialplansCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.data.repo.*;
import cn.cucsi.bsd.ucc.data.specs.PbxDialplansSpecs;
import cn.cucsi.bsd.ucc.service.PbxDialplansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@Service(value = "PbxDialplansService")
public class PbxDialplansServiceImpl implements PbxDialplansService {

    @Autowired
    private PbxDialplansRepository PbxDialplansRepository;
    @Autowired
    PbxGatewaysRepository pbxGatewaysRepository;
    @Autowired
    PbxExtsRepository pbxExtsRepository;
    @Autowired
    PbxIvrsRepository pbxIvrsRepository;
    @Autowired
    PbxQueuesRepository pbxQueuesRepository;
    @Override
    public Page<PbxDialplans> findAll(PbxDialplansCriteria PbxDialplansCriteria) {
        Pageable pageable = new PageRequest(PbxDialplansCriteria.getPage(), PbxDialplansCriteria.getSize());
        Page<PbxDialplans> page =  PbxDialplansRepository.findAll(PbxDialplansSpecs.createSpec(PbxDialplansCriteria), pageable);
        List<PbxDialplans> PbxDialplanslist = page.getContent();

        for (PbxDialplans PbxDialplans:PbxDialplanslist){
            Integer type = PbxDialplans.getType();
            if(type == 1 || type ==3){
                if(PbxDialplans.getDestination()!=null && !("").equals(PbxDialplans.getDestination())){
                    PbxGateways gateways =  pbxGatewaysRepository.findOne(PbxDialplans.getDestination());
                    if(gateways!=null && gateways.getGwName()!=null && !gateways.getGwName().equals("")){
                        PbxDialplans.setDestinationForMode("通过网关呼出->"+gateways.getGwName());
                    }
                }
            }else if(type == 2 || type == 4 || type == 6){
                String ModeStr =  getDestinationForMode(PbxDialplans.getMode(),PbxDialplans.getDestination());
                if(type == 6){//自定义呼入
                    String ModeStr2 =  getDestinationForMode(PbxDialplans.getMode2(),PbxDialplans.getDestination2());
                    String timeSection = "";
                    DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    timeSection = sdf.format(PbxDialplans.getTime1()) + "--" + sdf.format(PbxDialplans.getTime2());
                    ModeStr = timeSection + ModeStr +"其他时间"+ModeStr2;
                }
                PbxDialplans.setDestinationForMode(ModeStr);
            }
        }
        return page;
    }

    @Override
    public PbxDialplans findOne(String dialplanId) {
        return this.PbxDialplansRepository.findOne(dialplanId);
    }

    @Override
    public PbxDialplans save(PbxDialplans PbxDialplans) {

        PbxDialplans.setCreatedTime(new Date());

        return this.PbxDialplansRepository.save(PbxDialplans);
    }

    @Override
    public Boolean delete(String dialplanId) {
        this.PbxDialplansRepository.delete(dialplanId);
        return true;
    }


    public String getDestinationForMode(Integer mode,String destination){
        String ModeStr = "";
        switch (mode){
            case 2://转至分机
                if(destination!=null && !("").equals(destination)){
                    PbxExts PbxExts =  pbxExtsRepository.findOne(destination);
                    if(PbxExts!=null && PbxExts.getExtNum()!=null && !PbxExts.getExtNum().equals("")){
                        ModeStr = "转至分机->"+PbxExts.getExtNum();
                    }
                }
                break;
            case 3://转至IVR
                if(destination!=null && !("").equals(destination)){
                    PbxIvrs ivrs =  pbxIvrsRepository.findOne(destination);
                    if(ivrs!=null && ivrs.getIvrName()!=null && !ivrs.getIvrName().equals("")){
                        ModeStr = "转至IVR->"+ivrs.getIvrName();
                    }
                }
                break;
            case 4://转至队列
                if(destination!=null && !("").equals(destination)){
                    PbxQueues queues =  pbxQueuesRepository.findOne(destination);
                    if(queues!=null && queues.getQueueName()!=null && !queues.getQueueName().equals("")){
                        ModeStr = "转至队列->"+queues.getQueueName();
                    }
                }
                break;
        }
        return ModeStr;
    }

}
