package toy.wordTest.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import toy.wordTest.DAO.wordTestDao;
import toy.wordTest.service.ScoreService;
import toy.wordTest.service.wordTestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private wordTestService wordService;
	@Inject
	private ScoreService scoreService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		return "index";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		int level= 0;
		try {
			level = (wordService.lastLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(level);
		
		
		
		return "wordTest";
	}
	
	@RequestMapping(value = "/goTest", method = RequestMethod.GET)
	public String goTest(HttpServletRequest request, Model model) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		System.out.println(seq+"과 시험 시작!!!");
		
		List<WordVO> wlist = wordService.exam(seq);
		System.out.println(wlist.get(0).getEn());
		int size = wlist.size();
		
		Collections.shuffle(wlist);
		int temp = size/2;
		List<WordVO> slist = new ArrayList<WordVO>();  //문장 시험 문제
		List<WordVO> elist = new ArrayList<WordVO>();
				
		for(int i=0; i<temp; i++) {
			slist.add(wlist.get(i));
		}
		for(int i=temp; i<size; i++) {
			elist.add(wlist.get(i));
		}
		
		model.addAttribute("seq",seq);
		model.addAttribute("slist",slist);
		model.addAttribute("elist",elist);
		model.addAttribute("size",size);
		return "goTest";
	}
	

	@RequestMapping(value = "/score", method = {RequestMethod.GET,RequestMethod.POST})
	public String score(HttpServletRequest request, Model model) {
		String option = request.getParameter("option");
		
		if(option==null) {
			
		}
		else if(option.equals("insert")) {
			ScoreVO sVO = new ScoreVO();
			sVO.setScore(Integer.parseInt(request.getParameter("score")));
			sVO.setDate(request.getParameter("time"));
			sVO.setGrade(request.getParameter("grade"));
			sVO.setChapter(request.getParameter("chapter"));
			scoreService.insertScore(sVO);
		}
		
		List<ScoreVO> slist = new ArrayList<ScoreVO>();
		slist = scoreService.selectScore();
		
		model.addAttribute("slist",slist);
		return "score";
	}
}
