package cc.mrbird.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.service.DictService;

@Controller
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	@Log("获取字典信息")
	@RequestMapping("dict")
	@RequiresPermissions("dict:list")
	public String index() {
		return "system/dict/dict";
	}

	@RequestMapping("dict/list")
	@ResponseBody
	public Map<String, Object> dictList(QueryRequest request, Dict dict) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<Dict> list = this.dictService.findAllDicts(dict);
		PageInfo<Dict> pageInfo = new PageInfo<>(list);
		return getDataTable(pageInfo);
	}

	@RequestMapping("dict/excel")
	@ResponseBody
	public ResponseBo dictExcel(Dict dict) {
		try {
			List<Dict> list = this.dictService.findAllDicts(dict);
			return FileUtils.createExcelByPOIKit("字典表", list, Dict.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("dict/csv")
	@ResponseBody
	public ResponseBo dictCsv(Dict dict){
		try {
			List<Dict> list = this.dictService.findAllDicts(dict);
			return FileUtils.createCsv("字典表", list, Dict.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}
	
	@RequestMapping("dict/getDict")
	@ResponseBody
	public ResponseBo getDict(Long dictId) {
		try {
			Dict dict = this.dictService.findById(dictId);
			return ResponseBo.ok(dict);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取字典信息失败，请联系网站管理员！");
		}
	}

	@Log("新增字典 ")
	@RequiresPermissions("dict:add")
	@RequestMapping("dict/add")
	@ResponseBody
	public ResponseBo addDict(Dict dict) {
		try {
			this.dictService.addDict(dict);
			return ResponseBo.ok("新增字典成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("新增字典失败，请联系网站管理员！");
		}
	}

	@Log("删除字典")
	@RequiresPermissions("dict:delete")
	@RequestMapping("dict/delete")
	@ResponseBody
	public ResponseBo deleteDicts(String ids) {
		try {
			this.dictService.deleteDicts(ids);
			return ResponseBo.ok("删除字典成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除字典失败，请联系网站管理员！");
		}
	}

	@Log("修改字典 ")
	@RequiresPermissions("dict:update")
	@RequestMapping("dict/update")
	@ResponseBody
	public ResponseBo updateDict(Dict dict) {
		try {
			this.dictService.updateDict(dict);
			return ResponseBo.ok("修改字典成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("修改字典失败，请联系网站管理员！");
		}
	}
}
