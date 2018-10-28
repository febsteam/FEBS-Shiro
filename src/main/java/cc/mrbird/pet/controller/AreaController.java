package cc.mrbird.pet.controller;

/**
 * @author:shenshen
 * date:2018/10/17
 */
import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.pet.domain.Area;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cc.mrbird.pet.service.AreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AreaController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaService areaService;

    @Log("获取区域信息")
    @RequestMapping("area")
    @RequiresPermissions("area:list")
    public String index() {
        return "pet/area/area";
    }

    @RequestMapping("area/tree")
    @ResponseBody
    public ResponseBo getAreaTree() {
        try {
            Tree<Area> tree = this.areaService.getAreaTree();
            return ResponseBo.ok(tree);
        } catch (Exception e) {
            log.error("获取区域树失败", e);
            return ResponseBo.error("获取区域树失败！");
        }
    }

    @RequestMapping("area/getArea")
    @ResponseBody
    public ResponseBo getArea(Long areaId) {
        try {
            Area area = this.areaService.findById(areaId);
            return ResponseBo.ok(area);
        } catch (Exception e) {
            log.error("获取区域信息失败", e);
            return ResponseBo.error("获取区域信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("area/list")
    @RequiresPermissions("area:list")
    @ResponseBody
    public List<Area> areaList(Area area) {
        System.out.println("获取区域列表");
        try {
            return this.areaService.findAllAreas(area);
        } catch (Exception e) {
            log.error("获取区域列表失败", e);
            return new ArrayList<>();
        }
    }

    @RequestMapping("area/excel")
    @ResponseBody
    public ResponseBo areaExcel(Area area) {
        try {
            List<Area> list = this.areaService.findAllAreas(area);
            return FileUtils.createExcelByPOIKit("区域表", list, Area.class);
        } catch (Exception e) {
            log.error("导出区域信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("area/csv")
    @ResponseBody
    public ResponseBo areaCsv(Area area) {
        try {
            List<Area> list = this.areaService.findAllAreas(area);
            return FileUtils.createCsv("区域表", list, Area.class);
        } catch (Exception e) {
            log.error("获取区域信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("area/checkAreaName")
    @ResponseBody
    public boolean checkAreaName(String areaName, String oldAreaName) {
        if (StringUtils.isNotBlank(oldAreaName) && areaName.equalsIgnoreCase(oldAreaName)) {
            return true;
        }
        Area result = this.areaService.findByName(areaName);
        return result == null;
    }

    @Log("新增区域")
    @RequiresPermissions("area:add")
    @RequestMapping("area/add")
    @ResponseBody
    public ResponseBo addArea(Area area) {
        try {
            this.areaService.addArea(area);
            return ResponseBo.ok("新增区域成功！");
        } catch (Exception e) {
            log.error("新增区域失败", e);
            return ResponseBo.error("新增区域失败，请联系网站管理员！");
        }
    }

    @Log("删除区域")
    @RequiresPermissions("area:delete")
    @RequestMapping("area/delete")
    @ResponseBody
    public ResponseBo deleteAreas(String ids) {
        try {
            this.areaService.deleteAreas(ids);
            return ResponseBo.ok("删除区域成功！");
        } catch (Exception e) {
            log.error("删除区域失败", e);
            return ResponseBo.error("删除区域失败，请联系网站管理员！");
        }
    }

    @Log("修改区域 ")
    @RequiresPermissions("area:update")
    @RequestMapping("area/update")
    @ResponseBody
    public ResponseBo updateArea(Area area) {
        try {
            this.areaService.updateArea(area);
            return ResponseBo.ok("修改区域成功！");
        } catch (Exception e) {
            log.error("修改区域失败", e);
            return ResponseBo.error("修改区域失败，请联系网站管理员！");
        }
    }

}
