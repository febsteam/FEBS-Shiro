package cc.mrbird.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.HttpUtils;
import cc.mrbird.common.util.UrlUtils;
import cc.mrbird.web.domain.IdList;

import java.util.Objects;

@Controller
public class OneIsAllController extends BaseController {

	private static final String STATIC_URL = "channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";

	@Log("获取One--绘画信息")
	@RequestMapping("one/painting")
	@RequiresPermissions("one:painting")
	public String paintIndex() {
		return "web/one/paint";
	}

	@Log("获取One--文章信息")
	@RequestMapping("one/yuwen")
	@RequiresPermissions("one:yuwen")
	public String yuwenIndex() {
		return "web/one/yuwen";
	}

	@Log("获取One--散文信息")
	@RequestMapping("one/essay")
	@RequiresPermissions("one:essay")
	public String essayIndex() {
		return "web/one/essay";
	}

	@RequestMapping("one/oneList")
	@ResponseBody
	public ResponseBo getOneList() {
		try {
			Long id = Objects.requireNonNull(getIdList()).getData()[0];
			String data = HttpUtils.sendGet(UrlUtils.ONE_LIST_URL + id + "/0", STATIC_URL);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取oneList失败，请联系网站管理员！");
		}
	}
	
	@RequestMapping("one/readingList")
	@ResponseBody
	public ResponseBo getReadingList() {
		try {
			Long id = Objects.requireNonNull(getIdList()).getData()[0];
			String data = HttpUtils.sendGet(UrlUtils.ONE_LIST_URL + id + "/0", STATIC_URL);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取readingList失败，请联系网站管理员！");
		}
	}

	@RequestMapping("one/yuwenDetail")
	@ResponseBody
	public ResponseBo getYuwenDetail(String itemId, String id) {
		try {
			String data = HttpUtils.sendGet(UrlUtils.ONE_ESSAY_URL + itemId, "channel=wdj&source=summary&source_id="
					+ id + "&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android");
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取语文信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("one/yuwenComments")
	@ResponseBody
	public ResponseBo getYuwenComments(String itemId) {
		try {
			String data = HttpUtils.sendGet(UrlUtils.ONE_ESSAY_COMMENT_URL + itemId + "/0", STATIC_URL);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取语文评论失败，请联系网站管理员！");
		}
	}

	@RequestMapping("one/essayDetail")
	@ResponseBody
	public ResponseBo getEssayDetail(String itemId, String id) {
		try {
			String data = HttpUtils.sendGet(UrlUtils.ONE_ESSAY_URL + itemId,
					"channel=wdj&source=summary&source_id=" + id
							+ "&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android");
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取散文评论失败，请联系网站管理员！");
		}
	}
	
	@RequestMapping("one/essayComments")
	@ResponseBody
	public ResponseBo getEssayComments(String itemId) {
		try {
			String data = HttpUtils.sendGet(UrlUtils.ONE_ESSAY_COMMENT_URL + itemId + "/0", STATIC_URL);
			return ResponseBo.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取散文评论失败，请联系网站管理员！");
		}
	}

	private static IdList getIdList() {
		IdList il = new IdList();
		try {
			String idList = HttpUtils.sendGet(UrlUtils.ONE_ID_LIST_URL, STATIC_URL);
			il = JSON.parseObject(idList, IdList.class);
			return il;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
