package cc.mrbird.febs.others.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrBird
 */
@Controller("othersView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "others")
public class ViewController {

    @GetMapping("febs/form")
    @RequiresPermissions("febs:form:view")
    public String febsForm() {
        return FebsUtil.view("others/febs/form");
    }

    @GetMapping("febs/form/group")
    @RequiresPermissions("febs:formgroup:view")
    public String febsFormGroup() {
        return FebsUtil.view("others/febs/formGroup");
    }

    @GetMapping("febs/tools")
    @RequiresPermissions("febs:tools:view")
    public String febsTools() {
        return FebsUtil.view("others/febs/tools");
    }

    @GetMapping("febs/icon")
    @RequiresPermissions("febs:icons:view")
    public String febsIcon() {
        return FebsUtil.view("others/febs/icon");
    }

    @GetMapping("febs/others")
    @RequiresPermissions("others:febs:others")
    public String febsOthers() {
        return FebsUtil.view("others/febs/others");
    }

    @GetMapping("apex/line")
    @RequiresPermissions("apex:line:view")
    public String apexLine() {
        return FebsUtil.view("others/apex/line");
    }

    @GetMapping("apex/area")
    @RequiresPermissions("apex:area:view")
    public String apexArea() {
        return FebsUtil.view("others/apex/area");
    }

    @GetMapping("apex/column")
    @RequiresPermissions("apex:column:view")
    public String apexColumn() {
        return FebsUtil.view("others/apex/column");
    }

    @GetMapping("apex/radar")
    @RequiresPermissions("apex:radar:view")
    public String apexRadar() {
        return FebsUtil.view("others/apex/radar");
    }

    @GetMapping("apex/bar")
    @RequiresPermissions("apex:bar:view")
    public String apexBar() {
        return FebsUtil.view("others/apex/bar");
    }

    @GetMapping("apex/mix")
    @RequiresPermissions("apex:mix:view")
    public String apexMix() {
        return FebsUtil.view("others/apex/mix");
    }

    @GetMapping("map")
    @RequiresPermissions("map:view")
    public String map() {
        return FebsUtil.view("others/map/gaodeMap");
    }

    @GetMapping("eximport")
    @RequiresPermissions("others:eximport:view")
    public String eximport() {
        return FebsUtil.view("others/eximport/eximport");
    }

    @GetMapping("eximport/result")
    public String eximportResult() {
        return FebsUtil.view("others/eximport/eximportResult");
    }

    @GetMapping("datapermission")
    public String dataPermissionTest() {
        return FebsUtil.view("others/datapermission/test");
    }

    @GetMapping("febs/i18n")
    @RequiresPermissions("i18n:view")
    public String i18n() {
        return FebsUtil.view("others/i18n/i18n");
    }
}
