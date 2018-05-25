package cc.mrbird.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.MD5Utils;
import cc.mrbird.common.util.vcode.Captcha;
import cc.mrbird.common.util.vcode.GifCaptcha;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.service.UserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseBo login(String username, String password, String code, Boolean rememberMe) {
		if (!StringUtils.isNotBlank(code)) {
			return ResponseBo.warn("验证码不能为空！");
		}
		Session session = super.getSession();
		String sessionCode = (String) session.getAttribute("_code");
		if (!code.toLowerCase().equals(sessionCode)) {
			return ResponseBo.warn("验证码错误！");
		}
		password = MD5Utils.encrypt(username.toLowerCase(), password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			super.login(token);
			this.userService.updateLoginTime(username);
			return ResponseBo.ok();
		} catch (UnknownAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return ResponseBo.error(e.getMessage());
		} catch (LockedAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (AuthenticationException e) {
			return ResponseBo.error("认证失败！");
		}
	}

	@GetMapping(value = "gifCode")
	public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");

			Captcha captcha = new GifCaptcha(146, 33, 4);
			captcha.out(response.getOutputStream());
			Session session = super.getSession();
			session.removeAttribute("_code");
			session.setAttribute("_code", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/")
	public String redirectIndex() {
		return "redirect:/index";
	}

	@GetMapping("/403")
	public String forbid() {
		return "403";
	}

	@Log("访问系统")
	@RequestMapping("/index")
	public String index(Model model) {
		User user = super.getCurrentUser();
		model.addAttribute("user", user);
		return "index";
	}
}
