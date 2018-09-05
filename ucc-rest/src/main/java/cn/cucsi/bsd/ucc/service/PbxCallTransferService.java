package cn.cucsi.bsd.ucc.service;
import cn.cucsi.bsd.ucc.common.beans.PbxCallTransferCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import org.springframework.data.domain.Page;
/**
 * Created by Song on 2017/10/16.
 */
public interface PbxCallTransferService {
    Page<PbxCallTransfer> findAll(PbxCallTransferCriteria pbxCallTransferCriteria);
    PbxCallTransfer findOne(String extId);
    PbxCallTransfer save(PbxCallTransfer pbxCallTransfer);
    Boolean delete(String extId);
}
