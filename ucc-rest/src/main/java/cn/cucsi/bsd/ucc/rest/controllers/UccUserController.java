package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.service.UccUserService;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/uccUser")
public class UccUserController  {

    @Autowired
    private UccUserService uccUserService;

    @ApiOperation(value="根据查询条件获取用户列表", notes="根据查询条件获取用户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public PageResultBean<List<UccUsers>> findAll(@RequestBody UccUserCriteria criteria){
        PageResultBean<List<UccUsers>> list = new PageResultBean(this.uccUserService.findAll(criteria));
        return list;
    }

    @ApiOperation(value="根据查询条件获取用户", notes="根据查询条件获取用户", httpMethod = "POST")
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public List<UccUsers> login(@RequestBody UccUserCriteria criteria, HttpServletRequest request, HttpServletResponse response){
        List<UccUsers> list =this.uccUserService.findAllList(criteria);


        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy年MM月dd日");
        String sessionValue ="";
        for (int i=0;i<list.size();i++){
            sessionValue=  list.get(i).getUserName()+"#"+ list.get(i).getPassword();
        }
        Cookie cookie = new Cookie("login",sessionValue);
        cookie.setMaxAge(900000);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie.setDomain("localhost:8000");

        Cookie[] cookies = request.getCookies();

/*

            HttpSession session = request.getSession();
             //将数据存储到session中
                session.setAttribute("login", sessionValue);*/
        return list;
    }



    @ApiOperation(value = "根据userId查询UccUsers", notes = "根据userId查询UccUsers")
    @RequestMapping(value = "/{userId}", method= RequestMethod.POST)
    public ResultBean<UccUsers> findOne(@RequestBody String userId){
        return new ResultBean<>(this.uccUserService.findOne(userId));
    }

    @ApiOperation(value = "根据userId删除UccUsers", notes = "根据userId删除UccUsers")
    @RequestMapping(value = "/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.delete(userId));
    }

    @ApiOperation(value = "根据userId批量删除UccUsers", notes = "根据userId批量删除UccUsers")
    @RequestMapping(value = "multiDelete/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> multiDelete(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.multiDelete(userId));
    }

    @ApiOperation(value = "创建UccUsers", notes = "创建UccUsers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccUsers uccUsers) {
        boolean result = this.uccUserService.save(uccUsers) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccUsers", notes = "保存UccUsers")
    //@ApiImplicitParam(name = "uccUsers", value = "用户entity", required = true, dataType = "UccUsers")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String userId,@RequestBody UccUsers uccUsers){
        uccUsers.setUserId(userId);
        boolean result = this.uccUserService.save(uccUsers) != null;
        return new ResultBean<>(result);
    }

}
