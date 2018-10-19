package cn.cucsi.bsd.ucc.service.impl;

import java.util.List;
import cn.cucsi.bsd.ucc.common.mapper.FsRecordMapper;
import cn.cucsi.bsd.ucc.data.domain.FsRecord;
import cn.cucsi.bsd.ucc.service.FsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FsRecordServiceImpl implements FsRecordService {

	@Autowired
	private FsRecordMapper fsRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(String recordId,String domainId) throws Exception {
		return fsRecordMapper.deleteByPrimaryKey(recordId,domainId);
	}

	@Override
	public FsRecord selectByPrimaryKey(String recordId,String domainId) throws Exception {
		return fsRecordMapper.selectByPrimaryKey(recordId,domainId);
	}

	@Override
	public List<FsRecord> selectAll(String domainId) throws Exception {
		return fsRecordMapper.selectAll(domainId);
	}

	@Override
	public int deleteByTime(String recordTime,String domainId) throws Exception {
		return fsRecordMapper.deleteByTime(recordTime,domainId);
	}

	@Override
	public List<FsRecord> selectByTime(String recordTime,String domainId) throws Exception {
		return fsRecordMapper.selectByTime(recordTime,domainId);
	}

	@Override
	public int updateContentByRecordsId(String recordsId,String domainId) throws Exception {
		return fsRecordMapper.updateContentByRecordsId(recordsId,domainId);
	}

	@Override
	public List<String> selectRecordsIdByTime(String recordTime,String domainId) throws Exception {
		return fsRecordMapper.selectRecordsIdByTime(recordTime,domainId);
	}
	
}
