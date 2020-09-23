package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.dao.TradeDao;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.Trade;
import com.qfedu.entity.User;
import com.qfedu.service.TradeService;
import com.qfedu.vo.VTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeDao tradeDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<VTradeInfo> findAllTrades(Integer id, Date beginTime, Date endTime,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<VTradeInfo> trades = tradeDao.findAll(id,beginTime,endTime);
        return trades;
    }

    @Override
    public void transfer(String bankCode, String otherCode, Double money) {
        User loginUser = userDao.findByCode(bankCode);
        User otherUser = userDao.findByCode(otherCode);
        if (otherCode == null) {
            throw new RuntimeException("用户不存在");
        }
        if(loginUser.getBankCode().equals(otherCode)){
            throw new RuntimeException("不能转给自己");
        }
        if (loginUser.getBalance()<money){
            throw new RuntimeException("余额不足");
        }
        Trade outTrade = new Trade();
        outTrade.setUid(loginUser.getId());
        outTrade.setBalance(loginUser.getBalance()-money);
        outTrade.setMoney(0-money);
        outTrade.setCreateTime(new Date());
        tradeDao.add(outTrade);
        loginUser.setBalance(loginUser.getBalance()-money);
        userDao.update(loginUser);

        Trade inTrade = new Trade();
        inTrade.setUid(otherUser.getId());
        inTrade.setBalance(otherUser.getBalance()+money);
        inTrade.setMoney(money);
        inTrade.setCreateTime(new Date());
        tradeDao.add(inTrade);
        otherUser.setBalance(otherUser.getBalance()+money);
        userDao.update(otherUser);
    }
}
