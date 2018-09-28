package cn.cucsi.bsd.ucc.common.untils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.google.common.collect.Lists;
import jxl.write.*;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;

/**
 * jxl导出excel 
 * add by wangxiaoyu
 * 2018-09-28
 */  
public class JxlExcelUtils {  

	private static Logger logger = Logger.getLogger(JxlExcelUtils.class);

    /**
     * 导出工作表的名称
     * add by wangxiaoyu
     * 2018-09-28
     */  
    public static int exportToExcel(HttpServletResponse response, List<?> objDataAll, String sheetName,Object[] key,Class clazz, Class[] contantsClass,String tmarray) {

        int flag = 0;

        //声明工作簿jxl.write.WritableWorkbook  
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {  
            //根据传进来的file对象创建可写入的Excel工作薄  
            os = response.getOutputStream();
            wwb = Workbook.createWorkbook(os);

            if(objDataAll!=null && !objDataAll.isEmpty()) {
                logger.info("list is not empty, size: "+objDataAll.size()+".");
                List partitions = Lists.partition(objDataAll, 65535);

                if (tmarray != null) {
                    tmarray = tmarray.trim();
                    if (tmarray.startsWith(",")) {
                        tmarray = tmarray.replaceFirst(",", "");
                    }
                }

                String[] tmarrayArray = tmarray == null ? null : tmarray.split(",", -1);
                List<String> tmarrayList = new ArrayList<String>();
                if (tmarrayArray != null) {
                    for (String str : tmarrayArray) {
                        tmarrayList.add(str.trim());
                    }
                }

            /* 
             * 创建一个工作表、sheetName为工作表的名称、"0"为第一个工作表 
             * 打开Excel的时候会看到左下角默认有3个sheet、"sheet1、sheet2、sheet3"这样 
             * 代码中的"0"就是sheet1、其它的一一对应。 
             * createSheet(sheetName, 0)一个是工作表的名称，另一个是工作表在工作薄中的位置
             */
                for (int x = 0; x < partitions.size(); x++) {
                    WritableSheet ws = wwb.createSheet(sheetName + x, x);
                    flag = pageSheet(ws, (List) partitions.get(x), key, clazz, contantsClass, tmarrayList);
                }
            }else{
                logger.info("list is empty.");
            }

            //写入Exel工作表
            wwb.write();
            //关闭流
            os.flush();

            return flag;
        }
        catch (Exception e) {
            e.printStackTrace();  
			logger.error(e.getMessage(), e);
			return 0;
        }finally{
            //关闭Excel工作薄对象
            if(wwb!=null) {
                try {
                    wwb.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            if(os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os = null;
            }
        }

    }

    private static int pageSheet(WritableSheet ws, List<?> objData,Object[] key,Class clazz, Class[] contantsClass,List<String> tmarrayList) throws Exception{
        int flag = 0;
        SheetSettings ss = ws.getSettings();
        ss.setVerticalFreeze(1);//冻结表头

        WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 10 ,WritableFont.BOLD);
        WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 9 ,WritableFont.NO_BOLD);
        WritableCellFormat wcf = new WritableCellFormat(font1);
        WritableCellFormat wcf2 = new WritableCellFormat(font2);
        WritableCellFormat wcf3 = new WritableCellFormat(font2);//设置样式，字体

        //创建单元格样式
        //WritableCellFormat wcf = new WritableCellFormat();

        //背景颜色
        wcf.setBackground(Colour.YELLOW);
        wcf.setAlignment(Alignment.CENTRE);  //平行居中
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        wcf3.setAlignment(Alignment.CENTRE);  //平行居中
        wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        wcf3.setBackground(Colour.LIGHT_ORANGE);
        wcf2.setAlignment(Alignment.CENTRE);  //平行居中
        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中

            /*
             * 这个是单元格内容居中显示
             * 还有很多很多样式
             */
        wcf.setAlignment(Alignment.CENTRE);

        //判断一下表头数组是否有数据
        if (key != null && key.length > 0) {
            int tmp=0;
            //循环写入表头
            for (int i = 0; i < key.length; i++) {

                    /*
                     * 添加单元格(Cell)内容addCell()
                     * 添加Label对象Label()
                     * 数据的类型有很多种、在这里你需要什么类型就导入什么类型
                     * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label
                     * Label(i, 0, columns[i], wcf)
                     * 其中i为列、0为行、columns[i]为数据、wcf为样式
                     * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中
                     */

                String str=key[i].toString();
                if(!str.equals("key")){
                    if(tmp==1){
                        ws.addCell(new Label(i-1, 0, str, wcf));
                    }else{
                        ws.addCell(new Label(i, 0, str, wcf));
                    }

                }else{
                    tmp=1;
                }
            }

            //判断表中是否有数据
            if (objData != null && objData.size() > 0) {
                if (clazz!=null) {
                    //处理list<实体>的导出
                    try {
                        Field[] obj=clazz.getDeclaredFields();
                        //循环写入表中数据
                        for (int i = 0; i < objData.size(); i++) {
                            //循环插值
                            int cellcount=0;
                            for (int j = 0; j < obj.length; j++) {

                                if (contantsClass!=null) {
                                    int flag2=0;
                                    //如果实体里包含实体
                                    for (int j2 = 0; j2 < contantsClass.length; j2++) {
                                        if (contantsClass[j2].getName().toLowerCase().equals(obj[j].getName().toLowerCase())) {
                                            //java反射调用get方法获取对象里的值
                                            String tmpname="get"+(obj[j].getName().substring(0, 1)).toUpperCase()+obj[j].getName().substring(1,obj[j].getName().length());
                                            Object object= clazz.newInstance();
                                            object = objData.get(i);
                                            Method m1 = clazz.getDeclaredMethod(tmpname);
                                            //内部实体
                                            Object inside= contantsClass[j2].newInstance();
                                            inside= m1.invoke(object);
                                            Field[] insideField=contantsClass[j2].getDeclaredFields();
                                            //写入内部实体的字段
                                            for (int k = 0; k < insideField.length; k++) {
                                                if (tmarrayList==null||tmarrayList.contains(insideField[k].getName())) {
                                                    String tmpname2="get"+(insideField[k].getName().substring(0, 1)).toUpperCase()+insideField[k].getName().substring(1,obj[j].getName().length());
                                                    Method m2 = contantsClass[j2].getDeclaredMethod(tmpname2);
                                                    Object result2= m2.invoke(inside);

                                                    if(result2 instanceof Date){
                                                        result2 = DateFormatUtils.format((Date)result2, "yyyy-MM-dd HH:mm:ss");
                                                    }

                                                    ws.addCell(new Label(cellcount,i+1,String.valueOf(result2 == null ? "" : result2)));
                                                    cellcount++;
                                                }
                                            }

                                            flag2=1;
                                        }
                                    }
                                    //处理不是实体的情况
                                    if (flag2==0) {
                                        if (tmarrayList==null||tmarrayList.contains(obj[j].getName())) {
                                            //java反射调用get方法获取对象里的值
                                            String tmpname="get"+(obj[j].getName().substring(0, 1)).toUpperCase()+obj[j].getName().substring(1,obj[j].getName().length());
                                            Object object= clazz.newInstance();
                                            object = objData.get(i);
                                            Method m1 = clazz.getDeclaredMethod(tmpname);
                                            Object result= m1.invoke(object);

                                            if(result instanceof Date){
                                                result = DateFormatUtils.format((Date)result, "yyyy-MM-dd HH:mm:ss");
                                            }

                                            if(tmarrayList!=null){
                                                ws.addCell(new Label(tmarrayList.indexOf(obj[j].getName()),i+1,String.valueOf(result == null ? "" : result)));
                                            }else{
                                                ws.addCell(new Label(cellcount,i+1,String.valueOf(result == null ? "" : result)));
                                            }
                                            cellcount++;
                                        }
                                    }

                                }else {
                                    if (tmarrayList==null||tmarrayList.contains(obj[j].getName())) {
                                        //java反射调用get方法获取对象里的值
                                        String tmpname="get"+(obj[j].getName().substring(0, 1)).toUpperCase()+obj[j].getName().substring(1,obj[j].getName().length());
                                        Object object= clazz.newInstance();
                                        object = objData.get(i);
                                        Method m1 = clazz.getDeclaredMethod(tmpname);
                                        Object result= m1.invoke(object);

                                        if(result instanceof Date){
                                            result = DateFormatUtils.format((Date)result, "yyyy-MM-dd HH:mm:ss");
                                        }

                                        //                                        ws.addCell(new Label(cellcount,i+1,String.valueOf(result)));
                                        if(tmarrayList!=null){
                                            ws.addCell(new Label(tmarrayList.indexOf(obj[j].getName()),i+1,String.valueOf(result == null ? "" : result)));
                                        }else{
                                            ws.addCell(new Label(cellcount,i+1,String.valueOf(result == null ? "" : result)));
                                            cellcount++;
                                        }


                                    }
                                }

                            }
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    //处理list<LinkedHashMap>
                    //循环写入表中数据
                    for (int i = 0; i < objData.size(); i++) {

                        //转换成map集合{activyName:测试功能,count:2}
                        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)objData.get(i);

                        //循环输出map中的子集：既列值
                        int j=0;
                        for(Object o:map.keySet()){
                            //ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)
                            ws.addCell(new Label(j,i+1,String.valueOf(map.get(o))));
                            j++;
                        }
                    }
                }

            }else{
                flag = -1;
            }

        }
        return flag;
    }
  
  
    /** 
     * 下载excel 
     * @author  
     * @param response 
     * @param filename 文件名 ,如:20110808.xls 
     * @param listData 数据源 
     * @param sheetName 表头名称 
     * @param key 列名称集合,如：{物品名称，数量，单价} 
     * @param clazz 传入实体的class (如果list里是linkedmap则传null)
     * @param contantsClass 用于实体内包含实体的数组 (如果list里是linkedmap则传null,如果实体里没有包含的实体也传null)
     * @param tmarray 以逗号间隔要包含导出的字段 
     */  
    public static void exportexcle(HttpServletResponse response,String filename,List<?> listData,String sheetName,String[] key,Class clazz,Class[] contantsClass,String tmarray)  
    {  
        //调用上面的方法、生成Excel文件  
        response.setContentType("application/vnd.ms-excel");  
        //response.setHeader("Content-Disposition", "attachment;filename="+filename);  
        try {  
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");  
            exportToExcel(response, listData, sheetName, key ,clazz,contantsClass,tmarray);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
			logger.error(e.getMessage(), e);
        }   
  
  
    }  
    
    public static String getEncoding(String str) {      
        String encode = "GB2312";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s = encode;      
               return s;      
            }      
        } catch (Exception e) {      
			logger.error(e.getMessage(), e);
        }      
        encode = "ISO-8859-1";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s1 = encode;      
               return s1;      
            }      
        } catch (Exception e) {     
			logger.error(e.getMessage(), e); 
        }      
        encode = "UTF-8";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s2 = encode;      
               return s2;      
            }      
        } catch (Exception e) {      
			logger.error(e.getMessage(), e);
        }      
        encode = "GBK";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s3 = encode;      
               return s3;      
            }      
        } catch (Exception e) {   
			logger.error(e.getMessage(), e);   
        }      
       return "";      
    }     
}  