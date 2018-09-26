package cn.cucsi.bsd.ucc.common.untils;

import cn.cucsi.bsd.ucc.data.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.List;

public class PbxReload {

	public static boolean reloadExt(PbxExts ext, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "ext");
			map.put("method", method);
			map.put("content", ext);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			System.out.println("reloadExt中Zookeeper添加节点异常-----"+e);
		}
		return false;
	}
	
	public static boolean reloadExt(List<PbxExts> exts, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "ext");
			map.put("method", method);
			map.put("content", exts);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	public static void reloadExtAsync(final PbxExts ext, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadExt(ext, method, zk);
			}
		});
		thread.start();
	}
	
	public static void reloadExtAsync(final List<PbxExts> exts, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadExt(exts, method, zk);
			}
		});
		thread.start();
	}
	
	public static boolean reloadQueue(PbxQueues queue, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "queue");
			map.put("method", method);
			map.put("content", queue);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	public static void reloadQueueAsync(final PbxQueues queue, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadQueue(queue, method, zk);
			}
		});
		thread.start();
	}
	public static boolean reloadIvr(PbxIvrs ivr, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "ivr");
			map.put("method", method);
			map.put("content", ivr);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	public static void reloadIvrAsync(final PbxIvrs ivr, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadIvr(ivr, method, zk);
			}
		});
		thread.start();
	}
	public static boolean reloadDl(PbxDialplans rule, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "dialplan");
			map.put("method", method);
			map.put("content", rule);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	public static void reloadDlAsync(final PbxDialplans rule, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadDl(rule, method, zk);
			}
		});
		thread.start();
	}
	public static boolean reloadGw(PbxGateways gw, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "gw");
			map.put("method", method);
			map.put("content", gw);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	public static void reloadGwAsync(final PbxGateways gw, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadGw(gw, method, zk);
			}
		});
		thread.start();
	}
	public static boolean reloadCallTransfer(PbxCallTransfer record, String method, ZooKeeperUtils zk) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("type", "reload");
			map.put("subType", "callTransfer");
			map.put("method", method);
			map.put("content", record);
			ObjectMapper mapper = new ObjectMapper();
			zk.setData("/pbxreload", mapper.writeValueAsBytes(map));
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	public static void reloadCallTransferAsync(final PbxCallTransfer record, final String method, final ZooKeeperUtils zk) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				reloadCallTransfer(record, method, zk);
			}
		});
		thread.start();
	}
}
