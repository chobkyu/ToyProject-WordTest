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

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	

	@Autowired
	private JavaMailSender mailSender;
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
		
		
		
		model.addAttribute("last",level);
		
		
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
		List<WordVO> elist = new ArrayList<WordVO>();  //단어 시험 문제
				
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
	public String score(HttpServletRequest request, Model model) throws Exception {
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
			sendMailTest(sVO.getScore(),sVO.getChapter(),sVO.getDate());
		}
		
		List<ScoreVO> slist = new ArrayList<ScoreVO>();
		slist = scoreService.selectScore();
		
		int score = 0;
		for(int i=0; i<slist.size();i++) {
			score+=slist.get(i).getScore();
		}
		score = score/slist.size();  //평균점수
		
		model.addAttribute("score",score);
		model.addAttribute("slist",slist);
		return "score";
	}
	
	@RequestMapping(value = "/word", method = {RequestMethod.GET,RequestMethod.POST})
	public String word(HttpServletRequest request, Model model) {
		int countChapter = 20;
		String option = request.getParameter("option");
		int level= 0;
		try {
			level = (wordService.lastLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(level);
		
		model.addAttribute("last",level);
		
		if(option == null) {
			
		}else if(option.equals("study")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			List<WordVO> wlist = wordService.exam(seq);
			
			model.addAttribute("seq",seq);
			model.addAttribute("wlist",wlist);
			
			return "wordStudy";
		}
		model.addAttribute("count",countChapter);
		return "word";
	}
	
	public void sendMailTest(int score, String chapter, String date) throws Exception{
        
        String subject = "노예준 영단어 시험 점수";
        String content = date+"에 응시한 노예준의 "+chapter+" 과 점수는 "+score+"점 입니다";
        String from = "qudqud97@naver.com";
        String to = "rtw2343@naver.com";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.
            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}
