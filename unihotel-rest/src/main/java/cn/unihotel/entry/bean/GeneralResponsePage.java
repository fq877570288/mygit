package cn.unihotel.entry.bean;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author zhang anbing
 * @date 2018/8/21
 */
public class GeneralResponsePage<T> implements Serializable {
    /**
     * 0表示正常，1表示异常
     */
    private String return_code;
    /**
     * 响应消息
     */
    private String return_msg;
    /**
     * 当前页数
     */
    private int page_num;
    /**
     * 每页条数
     */
    private int page_size;
    //总记录数
    private long total_elements;
    //总页数
    private int total_pages;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    /**
     * 返回给前端的数据
     */
    private List list;

//    public GeneralResponsePage(String code, String message, List<T> list) {
//        this.code = code;
//        this.message = message;
//        this.list = list;
//    }

//    public static GeneralResponsePage success(String message) {
//        return new GeneralResponsePage("0", message, null);
//    }
//
//    public static GeneralResponsePage fail(String message) {
//        return new GeneralResponsePage("1", message,  null);
//    }

    public GeneralResponsePage() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public GeneralResponsePage(String code,String message,List<T> list) {
        this.return_code = code;
        this.return_msg = message;
        if (list instanceof Page) {
            Page page = (Page) list;
            this.page_num = page.getPageNum();
            this.page_size = page.getPageSize();

            this.total_pages = page.getPages();
            this.list = page;
            this.total_elements = page.getTotal();
        } else if (list instanceof Collection) {
            this.page_num = 1;
            this.page_size = list.size();

            this.total_pages = 1;
            this.list = list;
            this.total_elements = list.size();
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = page_num == 1;
        isLastPage = page_num == total_pages;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public int getpage_num() {
        return page_num;
    }

    public void setpage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getpage_size() {
        return page_size;
    }

    public void setpage_size(int page_size) {
        this.page_size = page_size;
    }

    public long gettotal_elements() {
        return total_elements;
    }

    public void settotal_elements(long total_elements) {
        this.total_elements = total_elements;
    }

    public int gettotal_pages() {
        return total_pages;
    }

    public void settotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("page_num=").append(page_num);
        sb.append(", page_size=").append(page_size);
        sb.append(", total_elements=").append(total_elements);
        sb.append(", total_pages=").append(total_pages);
        sb.append(", list=").append(list);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", navigatepage_nums=");
        sb.append('}');
        return sb.toString();
    }
}