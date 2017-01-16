package vin.hotelAutomation.HotelAutomationMVC.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vin.hotelAutomation.HotelAutomationMVC.dao.MemberDao;
import vin.hotelAutomation.HotelAutomationMVC.model.LoginInfo;
import vin.hotelAutomation.HotelAutomationMVC.model.MemberInfo;
import vin.hotelAutomation.HotelAutomationMVC.service.LoginService;
import vin.hotelAutomation.HotelAutomationMVC.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need To use RedirectAttributes
@EnableWebMvc
public class MainController {

	@Autowired
	private MemberDao memberDAO;

	@Autowired
	private MemberValidator memberValidator;

	@Autowired
	private LoginService loginService;

	/*
	 * @RequestMapping("/") public String homePage(Model model) {
	 * 
	 * return "welcomePage"; }
	 */
	@RequestMapping("/")
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Welcome to the Hotel");
		return "welcomePage";
	}

	@RequestMapping("/welcomePage")
	public String successPage(Model model) {
		return "welcomePage";
	}

	@RequestMapping("/loginPage")
	public String loginPage(Model model) {

		LoginInfo loginInfo = new LoginInfo();
		model.addAttribute("title", "Login");
		model.addAttribute("message", "Please login");
		return this.loginForm(model, loginInfo);

	}

	@RequestMapping("/cancelPage")
	public String cancelPage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "New member sign up cancelled");
		return "welcomePage";
	}

	@RequestMapping("/memberList")
	public String memberList(Model model) {
		List<MemberInfo> list = memberDAO.listMemberInfos();
		model.addAttribute("memberInfos", list);
		return "memberList";
	}
	
	@RequestMapping("/welcomeMemberPage")
	public String welcomeMemberPage(Model model) {
		model.addAttribute("title", "Welcome User");
		model.addAttribute("message", "You have logged in succesfully");
		return "welcomeMember";
	}

	/*
	 * private Map<String, String> dataForPositions() { Map<String, String>
	 * positionMap = new LinkedHashMap<String, String>();
	 * positionMap.put("Developer", "Developer"); positionMap.put("Leader",
	 * "Leader"); positionMap.put("Tester", "Tester"); return positionMap; }
	 * 
	 * private List<String> dataForSkills() { List<String> list = new
	 * ArrayList<String>(); list.add("Java"); list.add("C/C++"); list.add("C#");
	 * return list; }
	 */

	private String formMember(Model model, MemberInfo memberInfo) {
		model.addAttribute("memberForm", memberInfo);
		String userName = memberInfo.getUserName();

		if (userName == null) {
			model.addAttribute("formTitle", "Create Member");
		} /*
			 * else { model.addAttribute("formTitle", "Edit Applicant"); }
			 */

		return "formMember";
	}

	private String loginForm(Model model, LoginInfo loginInfo) {
		model.addAttribute("memberForm", loginInfo);
		return "login";
	}

	@RequestMapping("/createMember")
	public String createMember(Model model) {

		MemberInfo memberInfo = new MemberInfo();

		return this.formMember(model, memberInfo);
	}

	/*
	 * @RequestMapping("/editApplicant") public String editApplicant(Model
	 * model, @RequestParam("id") String id) { ApplicantInfo applicantInfo =
	 * null; if (id != null) { applicantInfo =
	 * this.applicantDAO.findApplicantInfo(id); } if (applicantInfo == null) {
	 * return "redirect:/applicantList"; }
	 * 
	 * return this.formApplicant(model, applicantInfo); }
	 * 
	 * @RequestMapping("/deleteApplicant") public String deleteApplicant(Model
	 * model, @RequestParam("id") String id) { if (id != null) {
	 * this.applicantDAO.deleteApplicant(id); } return
	 * "redirect:/applicantList"; }
	 */

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == MemberInfo.class) {
			dataBinder.setValidator(memberValidator);
		}
		
		if (target.getClass() == LoginInfo.class) {
			dataBinder.setValidator(memberValidator);
		}
	}
	

	// Save or update Member
	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value = "/saveMember", method = RequestMethod.POST)
	public String saveMember(Model model, //
			@ModelAttribute("memberForm") @Validated MemberInfo memberInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return this.formMember(model, memberInfo);
		}

		this.memberDAO.saveMember(memberInfo);

		// Important!!: Need @EnableWebMvc
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("title", "Success");
		redirectAttributes.addFlashAttribute("message", "Member Saved Successfully");

		return "redirect:/welcomePage";

	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String loginMember(Model model, //
			@ModelAttribute("memberForm") @Validated LoginInfo loginInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return this.loginForm(model, loginInfo);
		}

		String userName = loginInfo.getUserName();
		String password = loginInfo.getPassword();

		boolean validUser = loginService.checkLogin(userName, password);
		if (validUser) {
			System.out.println("User Exists");
			return "redirect:/welcomeMemberPage";
		} else {
			model.addAttribute("title", "Login");
			model.addAttribute("message", "Invalid uerame or password");
			return this.loginForm(model, loginInfo);
		}
		
	}

}
