package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.untils.PbxReload;
import cn.cucsi.bsd.ucc.common.untils.ZooKeeperUtils;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.PbxQueueNumbersService;
import cn.cucsi.bsd.ucc.service.PbxQueuesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/pbxQueues")
public class PbxQueuesController {

    @Autowired
    private PbxQueuesService PbxQueuesService;

    @Autowired
    private PbxQueueNumbersService pbxQueueNumbersService;

    @Autowired
    private ZooKeeperUtils zk;

    @ApiOperation(value="根据查询条件获取队列列表", notes="根据查询条件获取队列列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<PbxQueues>> findAll(@RequestBody PbxQueuesCriteria search){
        return new PageResultBean(this.PbxQueuesService.findAll(search));
    }
    @ApiOperation(value="根据查询条件获取队列列表", notes="根据查询条件获取队列列表", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method= RequestMethod.POST)
    public ResultBean<List<PbxQueues>> findAllOfNoPage(@RequestBody PbxQueuesCriteria search){
        return new ResultBean(this.PbxQueuesService.findAllOfNoPage(search));
    }
    @ApiOperation(value = "根据queueId查询PbxQueues", notes = "根据queueId查询PbxQueues")
    @RequestMapping(value = "/{queueId}", method= RequestMethod.GET)
    public ResultBean<PbxQueues> findOne(@PathVariable String queueId){
        return new ResultBean<>(this.PbxQueuesService.findOne(queueId));
    }
    @ApiOperation(value = "根据queueId删除PbxQueues", notes = "根据queueId删除PbxQueues")
    @RequestMapping(value = "/{queueId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String queueId){
        PbxQueueNumbersCriteria criteria = new PbxQueueNumbersCriteria();

        criteria.setQueueId(queueId);
        List<PbxQueueNumbers> pbxQueueNumbersList=  this.pbxQueueNumbersService.findAll(criteria);

        for (PbxQueueNumbers pbxQueueNumbers:pbxQueueNumbersList) {
            PbxQueueNumbersPK pk = new PbxQueueNumbersPK();
            pk.setQueueId(pbxQueueNumbers.getQueueId());
            pk.setExtId(pbxQueueNumbers.getExtId());
            this.pbxQueueNumbersService.delete(pk);
        }
        boolean result = this.PbxQueuesService.delete(queueId);
        if(result){
            PbxQueues pbxQueues = new PbxQueues();
            pbxQueues.setQueueId(queueId);
            PbxReload.reloadQueueAsync(pbxQueues, "delete", zk);
        }
        return new ResultBean<>(result);

    }
    @ApiOperation(value = "创建PbxQueues", notes = "创建PbxQueues")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxQueues PbxQueues) {
        String[] extGroupExts=PbxQueues.getExtGroupExts();
        PbxQueues queues = this.PbxQueuesService.save(PbxQueues);

        boolean result =queues != null;

        if(result){

            result = false ;
            for (String extId:extGroupExts
                    ) {
                PbxQueueNumbers pbxQueueNumbers = new PbxQueueNumbers();
                pbxQueueNumbers.setExtId(extId);
                pbxQueueNumbers.setQueueId(queues.getQueueId());
                result =   this.pbxQueueNumbersService.save(pbxQueueNumbers)!=null;
            }
            if(result){
                PbxReload.reloadQueueAsync(queues, "create", zk);
            }
            return new ResultBean<>(result);
        }else{
            return new ResultBean<>(result);
        }

    }
    @ApiOperation(value = "修改PbxQueues", notes = "修改PbxQueues")
    @RequestMapping(value = "/{queueId}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String queueId,@RequestBody PbxQueues PbxQueues){
        String[] extGroupExts = PbxQueues.getExtGroupExts();
        PbxQueues queues = this.PbxQueuesService.save(PbxQueues);//修改队列表
        boolean result = queues != null;
        if(result){

            PbxQueueNumbersCriteria criteria = new PbxQueueNumbersCriteria();
            criteria.setQueueId(queues.getQueueId());
            List<PbxQueueNumbers> pbxQueueNumbersList=  this.pbxQueueNumbersService.findAll(criteria);//查询队列分机号表
            for (PbxQueueNumbers pbxQueueNumbers:pbxQueueNumbersList) {
                PbxQueueNumbersPK pk = new PbxQueueNumbersPK();
                pk.setQueueId(pbxQueueNumbers.getQueueId());
                pk.setExtId(pbxQueueNumbers.getExtId());
                this.pbxQueueNumbersService.delete(pk);
            }
            for (String extId:extGroupExts
                    ) {
                PbxQueueNumbers pbxQueueNumbers = new PbxQueueNumbers();
                pbxQueueNumbers.setExtId(extId);
                pbxQueueNumbers.setQueueId(queues.getQueueId());
                result = this.pbxQueueNumbersService.save(pbxQueueNumbers)!=null;

            }
            if(result){
                PbxReload.reloadQueueAsync(PbxQueues,"update", zk);
            }
            return new ResultBean<>(result);
        }else{
            return new ResultBean<>(result);
        }
    }

    // 该方法用于 添加分布式队列时列出所有的分机组的分机列表
    @ApiOperation(value="无任何条件查询分机组", notes="无任何条件查询分机组", httpMethod = "POST")
    @RequestMapping(value = "/findNoCheckPbxExts", method= RequestMethod.POST)
    public ResultBean<Map<String,List<PbxExts>>> findNoCheckPbxExts(@RequestBody PbxQueuesCriteria search){
        return new ResultBean(this.PbxQueuesService.findAllPbxExtGroups(search));
    }
}
