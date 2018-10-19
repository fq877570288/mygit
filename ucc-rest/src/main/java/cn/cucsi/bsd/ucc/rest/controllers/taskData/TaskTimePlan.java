package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.FsRecord;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.*;
import cn.cucsi.bsd.ucc.service.common.CommonService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags={"任务定时操作接口"})
@RestController
@RequestMapping(value = "/timePlanTaskControl")
public class TaskTimePlan {

    private static Logger logger = Logger.getLogger(TaskTimePlan.class);

    @Autowired
    private CommonService commonService;
    @Autowired
    private TaskRecordService taskRecordService;
    @Autowired
    private FsRecordService fsRecordService;
    @Autowired
    private CompleteTaskService completeTaskService;
    /*@Autowired
    private TaskReportService taskReportService;*/
    @Autowired
    private ImportBatchService importBatchService;

    //每晚半夜1点执行
    @Transactional
    //@Scheduled(cron = "0 0 1 * * ?")
    public void execute(HttpSession session) throws Exception {
        logger.info("Start to execute delete Task and Record By Time...");
        try {
            UccUsers uccUsers = (UccUsers)session.getAttribute("uccUsers");
            if(MyUtils.isBlank(uccUsers)){
                logger.error("当前会话状态失效，请重新登录！");
                return;
            }
            String domainId = uccUsers.getDomainId()==null?"":uccUsers.getDomainId();
            SystemConfig systemConfig = null;
            try {
                systemConfig = commonService.selectSystemConfigAll();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("查询系统配置表发生异常！");
                return;
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
            SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyyMMdd");// 格式化对象

            if (systemConfig.getDelTask() != null && !"".equals(systemConfig.getDelTask()) && !"0".equals(systemConfig.getDelTask())) {
                Calendar taskCalendar = Calendar.getInstance();// 日历对象
                taskCalendar.setTime(date);// 设置当前日期
                taskCalendar.add(Calendar.MONTH, 0 - Integer.valueOf(systemConfig.getDelTask()));
                String taskTime = sdf.format(taskCalendar.getTime());

                // 删除归档明细表
                try {
                    taskRecordService.deleteByTime(taskTime);
                    logger.info("删除归档明细表taskRecord完毕!");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("删除归档明细表taskRecord失败！");
                    logger.error("删除归档明细表taskRecord失败！");
                    return;
                }
                // 删除归档流转表
                try {
                    taskRecordService.deleteTaskRecordTransferByTime(taskTime);
                    logger.info("删除归档流转表RecordTransfer完毕!");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("删除归档流转表RecordTransfer失败！");
                    logger.error("删除归档流转表RecordTransfer失败！");
                    return;
                }
            }
            if (systemConfig.getDelMusic() != null && !"".equals(systemConfig.getDelMusic()) && !"0".equals(systemConfig.getDelMusic())) {

                Calendar musicCalendar = Calendar.getInstance();// 日历对象
                musicCalendar.setTime(date);// 设置当前日期
                musicCalendar.add(Calendar.MONTH, 0 - Integer.valueOf(systemConfig.getDelMusic()));
                String musicTime = sdf.format(musicCalendar.getTime());

                // 删除磁盘存放录音文件
                if (systemConfig.getRecordPath() != null && !"".equals(systemConfig.getRecordPath())) {
                    List<FsRecord> musicList = null;
                    try {
                        musicList = fsRecordService.selectByTime(musicTime,domainId);
                        logger.info("删除磁盘存放录音文件完毕!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("删除磁盘存放录音文件失败！");
                        return;
                    }
                    if(musicList==null || musicList.isEmpty()){
                        logger.info("截止到 "+musicTime+", 没有要删除的录音文件！");
                    }else{
                        logger.info("截止到 "+musicTime+", "+musicList.size()+" 条录音文件将被删除！");
                        String[] recordPaths = systemConfig.getRecordPath().split(",");
                        for (String recordPath : recordPaths) {
                            for (FsRecord fsRecord : musicList) {
                                String path = recordPath + File.separator + fsRecord.getRecordName();
                                File myFilePath = new File(path);
                                if (myFilePath.exists()) {
                                    Runtime.getRuntime().exec("chmod 777 " + myFilePath);
                                    Runtime.getRuntime().exec("rm -rf " + myFilePath);
                                    //myFilePath.delete();
                                }
                                if (fsRecord.getCreatedTime() != null) {
                                    String datePath = recordPath + File.separator + sdfYYYYMMDD.format(fsRecord.getCreatedTime()) + File.separator + fsRecord.getRecordName();
                                    File myDateFilePath = new File(datePath);
                                    if (myDateFilePath.exists()) {
                                        Runtime.getRuntime().exec("chmod 777 " + myDateFilePath);
                                        Runtime.getRuntime().exec("rm -rf " + myDateFilePath);
                                        //myFilePath.delete();
                                    }
                                }
                            }
                        }
                        //删除PBX_RECORD表中过期数据
                        try {
                            fsRecordService.deleteByTime(musicTime,domainId);
                            logger.info("删除磁盘存放录音文件完毕！");
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("删除磁盘存放录音文件失败！");
                            System.out.println("删除磁盘存放录音文件失败！");
                            return;
                        }
                    }
                }
                // 删除数据库存放录音文件
                /*List<String> recordsIdList = fsRecordService.selectRecordsIdByTime(musicTime);
                if(recordsIdList==null || recordsIdList.isEmpty()){
                    logger.info("By the end of "+musicTime+", No record blob values in pbx_records to be deleted.");
                }else{
                    logger.info("By the end of "+musicTime+", "+recordsIdList.size()+" record blob values in pbx_records will be deleted.");
                    for (String recordsId : recordsIdList) {
                        fsRecordService.updateContentByRecordsId(recordsId);
                    }
                    logger.info("删除数据库存放录音文件完毕.");
                }*/
            }
            logger.info("End to execute delete Task and Record By Time.");
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            logger.error("End to execute delete Task and Record By Time with error.");
        }
    }

    /***
     * 更新任务完成状态
     * 备注：超过任务截止日期的任务状态更改为已办结
     * add by wangxiaoyu
     * 2018-10-19
     */
    //每晚凌晨2点执行
    //@Scheduled(cron = "0 0 2 * * ?")
    public void executeTaskEndDate() throws Exception {

        logger.info("Start to execute taskDetail to taskRecord by endType...");
        try {
            int retcode = completeTaskService.updateTaskStatusByEndDate();
            System.out.println("更新任务完成状态执行返回结果:::" + retcode);
            logger.info("End to execute taskDetail to taskRecord by endType.");
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            logger.error("End to execute taskDetail to taskRecord by endType with error.");
        }
    }

    //每晚凌晨3点执行
    /*public void executeTaskReport() throws Exception {
        try {
            //List<TaskType> taskTypes = this.businessService.selectAll(taskTypeSearch);
            logger.info("Start to execute task report...");

            List<String> importBatchList = importBatchService.selectRecentImportBatch();
            if(importBatchList==null || importBatchList.isEmpty()){
                logger.info("No task report to run.");
                return;
            }
            logger.info("Task [BatchId: "+importBatchList+"] is runing...");

            *//*String yestoday = DateFormatUtils.format(DateUtil.getAfterDay(-1), "yyyy-MM-dd");
            String yestodayStart = yestoday+" 00:00:00";
            String yestodayEnd = yestoday+" 23:59:59";
            String theDayBeforeYestoday = DateFormatUtils.format(DateUtil.getAfterDay(-2), "yyyy-MM-dd");
            String theDayBeforeYestodayStart = theDayBeforeYestoday+" 00:00:00";
            String theDayBeforeYestodayEnd = theDayBeforeYestoday+" 23:59:59";
            search.setYestodayStart(yestodayStart);
            search.setYestodayEnd(yestodayEnd);
            search.setTheDayBeforeYestodayStart(theDayBeforeYestodayStart);
            search.setTheDayBeforeYestodayEnd(theDayBeforeYestodayEnd);
            logger.info("yestodayStart is "+yestodayStart);
            logger.info("yestodayEnd is "+yestodayEnd);
            logger.info("theDayBeforeYestodayStart is "+theDayBeforeYestodayStart);
            logger.info("theDayBeforeYestodayEnd is "+theDayBeforeYestodayEnd);
            //报表数据
            if(taskTypes!=null && !taskTypes.isEmpty()){
                for(TaskType taskType: taskTypes) {
                    search.setTaskTypeId(taskType.getTaskTypeId());
                    search.setTaskTypeName(taskType.getTaskTypeName());*//*

            List<TaskReportDetail> trlist = taskReportService.taskReportList(importBatchList);

            taskReportService.deleteOCR(importBatchList);
            logger.info("delete oc_task_mesh_report end.");
            taskReportService.insertOCR(trlist);
            logger.info("insert oc_task_mesh_report end.");
            taskReportService.insertOCRLog(trlist);
            logger.info("insert oc_task_mesh_report_log end.");
                //}
            //}
            logger.info("End to execute task report.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            logger.error("End to execute task report with error.");
        }
    }*/

}
