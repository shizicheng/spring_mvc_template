package com.hotcloud.action;

import com.hotcloud.model.ResAreaNllj;
import com.hotcloud.model.ResAreaNlljPK;
import com.hotcloud.redis.JedisPoolFactory;
import com.hotcloud.service.IPortalService;
import com.hotcloud.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PortalAction {

    @Autowired
    IPortalService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listConfig(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.addAttribute("config", ConfigUtil.config);
        return "config";
    }

    @ResponseBody
    @RequestMapping(value = "/redis/set/{key}/{val}", method = RequestMethod.GET)
    public String redis(
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model,                                                                                                                           /**/
            @PathVariable(value = "key") String key,
            @PathVariable(value = "val") String val) {
        Jedis jedis = JedisPoolFactory.getInstance().getResource();
        jedis.set(key, val);
        jedis.close();
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/redis/get/{key}", method = RequestMethod.GET)
    public String redis(
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model,                                                                                                                           /**/
            @PathVariable(value = "key") String key) {
        Jedis jedis = JedisPoolFactory.getInstance().getResource();
        String val = jedis.get(key);
        jedis.close();
        return val;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<ResAreaNllj> list = service.findAll();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping("/api/heat/{dateline}/{areaguid}")
    public String totalHeat(
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model,                                                                                                                           /**/
            @PathVariable(value = "dateline") String dateline,
            @PathVariable(value = "areaguid") Integer areaguid) {
        ResAreaNlljPK id = new ResAreaNlljPK();
        id.setDateline(String.valueOf(dateline));
        id.setAreaguid(areaguid);
        ResAreaNllj one = service.findById(id);
        model.addAttribute("dateline", dateline);
        model.addAttribute("areaguid", areaguid);
        model.addAttribute("one", one);
        return "portal";
    }
}
