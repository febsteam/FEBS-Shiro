package cc.mrbird.pet.controller;
/**
 * @author:shenshen
 * date:2018/10/17
 */
import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.pet.domain.Dona;
import cc.mrbird.pet.service.DonaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DonaController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DonaService donaService;

    @Log("获取捐赠物品信息")
    @RequestMapping("dona")
    @RequiresPermissions("dona:list")
    public String index() {
        return "pet/dona/dona";
    }

    @RequestMapping("dona/tree")
    @ResponseBody
    public ResponseBo getDonaTree() {
        try {
            Tree<Dona> tree = this.donaService.getDonaTree();
            return ResponseBo.ok(tree);
        } catch (Exception e) {
            log.error("获取捐赠物品树失败", e);
            return ResponseBo.error("获取捐赠物品树失败！");
        }
    }

    @RequestMapping("dona/getDona")
    @ResponseBody
    public ResponseBo getDona(Long donaId) {
        try {
            Dona dona = this.donaService.findById(donaId);
            return ResponseBo.ok(dona);
        } catch (Exception e) {
            log.error("获取捐赠物品信息失败", e);
            return ResponseBo.error("获取捐赠物品信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dona/list")
    @RequiresPermissions("dona:list")
    @ResponseBody
    public List<Dona> donaList(Dona dona) {
        System.out.println("获取捐赠物品列表");
        try {
            return this.donaService.findAllDonas(dona);
        } catch (Exception e) {
            log.error("获取捐赠物品列表失败", e);
            return new ArrayList<>();
        }
    }

    @RequestMapping("dona/excel")
    @ResponseBody
    public ResponseBo donaExcel(Dona dona) {
        try {
            List<Dona> list = this.donaService.findAllDonas(dona);
            return FileUtils.createExcelByPOIKit("捐赠物品表", list, Dona.class);
        } catch (Exception e) {
            log.error("导出捐赠物品信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dona/csv")
    @ResponseBody
    public ResponseBo donaCsv(Dona dona) {
        try {
            List<Dona> list = this.donaService.findAllDonas(dona);
            return FileUtils.createCsv("捐赠物品表", list, Dona.class);
        } catch (Exception e) {
            log.error("获取捐赠物品信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("dona/checkDonaUser")
    @ResponseBody
    public boolean checkDonaUser(String donaUser, String oldDonaUser) {
        if (StringUtils.isNotBlank(oldDonaUser) && donaUser.equalsIgnoreCase(oldDonaUser)) {
            return true;
        }
        Dona result = this.donaService.findByUser(donaUser);
        return result == null;
    }

    @Log("新增捐赠物品")
    @RequiresPermissions("dona:add")
    @RequestMapping("dona/add")
    @ResponseBody
    public ResponseBo addDona(Dona dona) {
        try {
            this.donaService.addDona(dona);
            return ResponseBo.ok("新增捐赠物品成功！");
        } catch (Exception e) {
            log.error("新增捐赠物品失败", e);
            return ResponseBo.error("新增捐赠物品失败，请联系网站管理员！");
        }
    }

    @Log("删除捐赠物品")
    @RequiresPermissions("dona:delete")
    @RequestMapping("dona/delete")
    @ResponseBody
    public ResponseBo deleteDonas(String ids) {
        try {
            this.donaService.deleteDonas(ids);
            return ResponseBo.ok("删除捐赠物品成功！");
        } catch (Exception e) {
            log.error("删除捐赠物品失败", e);
            return ResponseBo.error("删除捐赠物品失败，请联系网站管理员！");
        }
    }

    @Log("修改捐赠物品")
    @RequiresPermissions("dona:update")
    @RequestMapping("dona/update")
    @ResponseBody
    public ResponseBo updateDona(Dona dona) {
        try {
            this.donaService.updateDona(dona);
            return ResponseBo.ok("修改捐赠物品成功！");
        } catch (Exception e) {
            log.error("修改捐赠物品失败", e);
            return ResponseBo.error("修改捐赠物品失败，请联系网站管理员！");
        }
    }

}

