package com.qfedu.controller;

import com.github.pagehelper.Page;
import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.TradeService;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import com.qfedu.vo.VTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")//trade/list.do
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String,Object> listTradeInfo (HttpSession session, Date beginTime, Date endTime, Integer page, Integer limit) {
        User user = (User) session.getAttribute(StrUtils.LOGIN_USER);
        List<VTradeInfo> list = tradeService.findAllTrades(user.getId(),beginTime,endTime,page,limit);
        long total = ((Page) list).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",total);//获取总条数
        map.put("data",list);//获取分页的数据
        return map;
    }
    @RequestMapping("/transfer.do")
    @ResponseBody
    public JsonResult transfer(String otherCode,Double money,HttpSession session){
        User u = (User) session.getAttribute(StrUtils.LOGIN_USER);
        tradeService.transfer(u.getBankCode(),otherCode,money);
        User user = userService.selectByCode(u.getBankCode());
        session.setAttribute(StrUtils.LOGIN_USER,user);
        return new JsonResult(1,"转账成功");
    }
}
