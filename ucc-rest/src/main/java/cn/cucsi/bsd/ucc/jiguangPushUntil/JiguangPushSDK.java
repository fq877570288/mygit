package cn.cucsi.bsd.ucc.jiguangPushUntil;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import java.util.Map;

/**
 * @Description: (消息推送工具类)
 * @author  wangxiaoyu
 * @date 2018-08-17
 */
public class JiguangPushSDK {

    protected static Logger LOG = LoggerFactory.getLogger(JiguangPushSDK.class);
    private static final String MASTER_SECRET = "d3934fca8279809452908db8";
    private static final String APP_KEY = "5a7ed3122853627e482a4415";
    private static PushPayload pushPayload;
    //  private static Builder builder;
    //  static {
    //  Builder builder = PushPayload.newBuilder();
    //  pushPayload = builder
    //  .setNotification(Notification.alert(""))
    //  .setAudience(Audience.all())
    //  .setPlatform(Platform.all())
    //  .setOptions(Options.newBuilder().setTimeToLive(0L).build())
    //  .build();
    //  }
    public static final String BEEN_CONCERNED_ALERT = "";

    public static final String SOUND = "";

    public static final int BADGE = 1;

    /**
     * @param @param  json
     * @param @return 设定文件
     * @return HttpResponse    返回类型
     * @throws APIRequestException
     * @throws APIConnectionException
     * @throws
     * @Title: pushMessage
     * @Description: (推送消息)
     */
    public static PushResult sendPush(PushPayload payload){
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            return jPushClient.sendPush(payload);
        } catch (cn.jpush.api.common.resp.APIConnectionException e) {
            e.printStackTrace();
            return null;
        } catch (cn.jpush.api.common.resp.APIRequestException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param @param  payload
     * @param @param  logger
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: sendPushTryCatch
     * @Description: try catch 推送
     */
    public static void sendPushTryCatch(PushPayload payload, Logger logger) {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            logger.info("返回结果" + pushResult);
        } catch (cn.jpush.api.common.resp.APIConnectionException e) {
            e.printStackTrace();
            logger.error("连接错误，稍后尝试" + e);
        } catch (cn.jpush.api.common.resp.APIRequestException e) {
            e.printStackTrace();
            logger.error("极光推送内部错误", e);
            logger.info("网络请求状态" + e.getStatus());
            logger.info("错误状态码" + e.getErrorCode());
            logger.info("错误信息" + e.getErrorMessage());
            logger.info("信息ID" + e.getMsgId());
            logger.info("极光推送错误信息:" + e.getErrorMessage());
        }
    }

    /**
     * @param @param  alert
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectAllAllAlert
     * @Description: (快捷地构建推送对象 ： 所有平台 ， 所有设备 ， 内容为 alert 的通知)
     */
    @SuppressWarnings("static-access")
    public static PushPayload buildPushObjectAllAllAlert(String alert) {
        return pushPayload.alertAll(alert);
    }

    /**
     * @param @param  alert
     * @param @param  alias
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectAliasAlert
     * @Description: (所有平台 ， 推送目标是别名为 alias ， 通知内容为 alert)
     */
    public static PushPayload buildPushObjectAliasAlert(String days, String alert, String... alias) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .addExtra("sign", "5")
                                        .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("sign", "5")
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  alias
     * @param @param  alert
     * @param @param  badge
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectIos
     * @Description: (iOS推送 badge sound)
     */
    public static PushPayload buildPushObjectIosAndroid(String days, Map<String, String> params,
                                                        String[] alias, String alert, int badge, String sound, String msgContent) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .addExtras(params)
                                .setSound(sound)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtras(params)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  params
     * @param @param  alias
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectAllAliasAlert
     * @Description: (所有平台 ， 推送目标是别名为 alias ， 通知标题为 title ， 通知内容为 alert)
     */
    public static PushPayload buildPushObjectAllAliasAlert(String days, Map<String, String> params, String alert, String title, String... alias) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title)
                                .addExtras(params)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtras(params)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }


    /**
     * @param @param  tag
     * @param @param  alert
     * @param @param  title
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectAndroidTagAlertWithTitle
     * @Description: (平台是 Android ， 目标是 tag 为 tag 的设备 ， 内容是 Android 通知 alert ， 并且标题为 title)
     */
    public static PushPayload buildPushObjectAndroidTagAlertWithTitle(String days, String tag, String alert, String title) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, title, null))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  tag
     * @param @param  tagAll
     * @param @param  number
     * @param @param  alert
     * @param @param  msgContent
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectIosTagAndAlertWithExtrasAndMessage
     * @Description: (
     *构建推送对象 ： 平台是 iOS ， 推送目标是 tag, tagAll 的交集 ，
     *推送内容同时包括通知与消息 - 通知信息是 alert ， 角标数字为 number ，
     *消息内容是 msgContent 。
     *通知是 APNs 推送通道的 ， 消息是 JPush 应用内消息通道的 。
     *APNs 的推送环境是 “ 开发 ” （ 如果不显式设置的话 ， Library 会默认指定为开发 ）
     *true 表示推送生产环境 ， true 表示要推送开发环境
     *)
     */
    public static PushPayload buildPushObjectIosTagAndAlertWithExtrasAndMessage(String days,
                                                                                String tag, String tagAll, int number, String alert, String msgContent) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tag, tagAll))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(number)
                                .build())
                        .build())
                .setMessage(Message.content(msgContent))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * 构建推送对象：平台是 Andorid 与 iOS，
     * 推送目标是 （tag1 与 tag2 的并集），
     * 推送内容是 - 内容为 msgContent 的消息
     *
     * @param @param  tag1
     * @param @param  tag2
     * @param @param  msgContent
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: buildPushObjectIosAudienceMoreMessageWithExtras
     */
    public static PushPayload buildPushObjectIosAudienceMoreMessageWithExtras(String days,
                                                                              String tag1, String tag2, String msgContent) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag(tag1, tag2))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  extras
     * @param @param  title
     * @param @param  msgContent
     * @param @param  alias
     * @param @return
     * @param @throws APIConnectionException
     * @param @throws APIRequestException    设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: sendAndroidMessageWithAliasAndExtras
     * @Description: 用户自定义消息类型, 以及传递附属信息, 发送给安卓和IOS特定用户
     */
    public static PushPayload sendAndroidAndIosMessageWithAliasAndExtras(String days, Map<String, String> extras, String title, String msgContent, String... alias)
            throws APIConnectionException, APIRequestException {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setTitle(title)
                        .setMsgContent(msgContent)
                        .addExtras(extras)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  title
     * @param @param  msgContent
     * @param @param  alias
     * @param @return
     * @param @throws APIConnectionException
     * @param @throws APIRequestException    设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: sendAndroidMessageWithAlias
     * @Description: 用户自定义消息类型，无附加属性信息，发送给安卓和IOS特定用户
     */
    public static PushPayload sendAndroidAndIosMessageWithAlias(String days, Map<String, String> extras, String title, String msgContent, String... alias)
            throws APIConnectionException, APIRequestException {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setTitle(title)
                        .setMsgContent(msgContent)
                        .addExtras(extras)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

    /**
     * @param @param  title
     * @param @param  alert
     * @param @return 设定文件
     * @return PushPayload    返回类型
     * @throws
     * @Title: sendAndroidAndIosMessageAndNotification
     * @Description: 推送消息和通知，发送给所有安卓和IOS用户
     */
    public static PushPayload sendAndroidAndIosMessageAndNotification(String days, String content, String alert) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        return builder
                .setPlatform(Platform.android_ios())
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .build())
                .setMessage(Message.newBuilder()
                        .setTitle("")
                        .setMsgContent(content)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(Long.valueOf(Integer.valueOf(days) * 86400))
                        .build())
                .build();
    }

      public static void main(String[] args) {
          try {
              sendPush(sendAndroidAndIosMessageAndNotification("1", "大熊测试-02","超神了哦"));
          } catch (Exception e) {
              e.printStackTrace();
              LOG.error("Connection error. Should retry later. ", e);
              System.out.println(e.toString());
          }
      }
}

