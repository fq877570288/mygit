package cn.cucsi.bsd.ucc.common.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * ZooKeeper 工具类
 * @author DotQin
 * @date 2016年2月12日 上午1:32:51
 * QQ：235046801
 */
//@Component //如不须在配置文件中设置相关参数 可直接使用该注解
@Configuration
@ConfigurationProperties(prefix="ucc.zk")
public class ZooKeeperUtils {
	private String hostPort;
	private Integer sessionTimeout;

	//对外提供的方法
	public String getData(String node) throws Exception {
		return new String(client.getData(node, false, null));
	}
	
	public void setData(String node, String data) throws Exception {
		client.setData(node, data.getBytes(), -1);
	}
	
	public void setData(String node, byte[] data) throws Exception {
		client.setData(node, data, -1);
	}

	public void createNode(String node, String data) throws Exception {
		client.create(node, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public void deleteNode(String node) throws Exception {
		client.delete(node, -1);
	}
	
	public ZooKeeper getClient() {
		return client;
	}
	
	//可在SPRING配置文件中设置HOST_PORT以及SESSION_TIMEOUT
	private ZooKeeper client;

	private static final Log log = LogFactory.getLog(ZooKeeperUtils.class);

	@PostConstruct
	public void readConfigure(){
		connect(1);
	}
	//在构造方法中初始化ZK连接
	public ZooKeeperUtils() {
	}

	//启动一个线程去连接ZK 连接断开后会自动重连

	public void connect(final int n) {
		if (client != null) {
			try {
				client.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Thread.sleep(1000);//首次启动 等待1秒确保配置生效
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		client = null;
		log.info("ZooKeeper 第" + n + "次尝试连接 ");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					client = new ZooKeeper(hostPort, sessionTimeout, new Watcher() {
						@Override
						public void process(WatchedEvent event) {
							KeeperState keeperState = event.getState();
							EventType eventType = event.getType();
							if (KeeperState.SyncConnected == keeperState) {
								if (EventType.None == eventType) {
									log.info("ZooKeeper 连接成功!");
									System.out.println("ZooKeeper 连接成功!");
								}
							} else if (KeeperState.Disconnected == keeperState) {
								log.info("ZooKeeper 连接断开......");
								System.out.println("ZooKeeper 连接断开......");
								connect(n + 1);
							}

						}
					});
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		});
		thread.start();
		
	}
	public String getHostPort() {
		return hostPort;
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
}
