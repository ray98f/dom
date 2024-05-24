package com.wzmtr.dom.utils.task;

import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.service.common.MdmSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务-基础数据同步
 * @author  Ray
 * @version 1.0
 * @date 2023/11/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrgDataSyncTask {

    @Autowired
    private MdmSyncService mdmSyncService;

    @Value("${local.base-url}")
    private String localUrl;

    @Value("${local.data-sync}")
    private String dataSync;

    @Scheduled(cron = "0 30 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncPerson() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncPersonUrl = "/mdm/sync/person";
        log.info("人员信息同步：" + localUrl + syncPersonUrl);
        mdmSyncService.syncAllPerson();
    }

    @Scheduled(cron = "0 50 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncAllSuppContact() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncSuppContactUrl = "/mdm/sync/contact/supp";
        log.info("人员信息同步：" + localUrl + syncSuppContactUrl);
        mdmSyncService.syncAllSuppContact();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncPersonPlus() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncPersonPlusUrl = "/mdm/sync/person/plus";
        log.info("人员附加信息同步：" + localUrl + syncPersonPlusUrl);
        mdmSyncService.syncPersonPlus();
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncOrg() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncOrgUrl = "/mdm/sync/org";
        log.info("部门信息同步：" + localUrl + syncOrgUrl);
        mdmSyncService.syncAllOrg();
    }

    @Scheduled(cron = "0 10 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncSuppOrg() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncSuppOrgUrl = "/mdm/sync/org/supp";
        log.info("供应商信息同步：" + localUrl + syncSuppOrgUrl);
        mdmSyncService.syncSuppOrg();
    }

    @Scheduled(cron = "0 20 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncExtraOrg() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncExtraOrgUrl = "/mdm/sync/org/extra";
        log.info("外部部门信息同步：" + localUrl + syncExtraOrgUrl);
        mdmSyncService.syncAllExtraOrg();
    }

    @Scheduled(cron = "0 30 2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void syncEmpJobInfo() {
        if (CommonConstants.OFF.equals(dataSync)) {
            return;
        }
        String syncEmpJobUrl = "/mdm/sync/job/emp";
        log.info("人员岗位信息同步：" + localUrl + syncEmpJobUrl);
        mdmSyncService.syncAllEmpJob();
    }

}
