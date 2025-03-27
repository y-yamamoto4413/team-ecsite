package jp.co.internous.team2501.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2501.model.domain.MstCategory;
import jp.co.internous.team2501.model.domain.MstProduct;
import jp.co.internous.team2501.model.form.SearchForm;
import jp.co.internous.team2501.model.mapper.MstCategoryMapper;
import jp.co.internous.team2501.model.mapper.MstProductMapper;
import jp.co.internous.team2501.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2501")
public class IndexController {
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		if (!loginSession.isLogined() && loginSession.getTmpUserId() == 0) {
			int tmpUserId = new Random().nextInt(1_000_000_000);
			tmpUserId *= -1;
			loginSession.setTmpUserId(tmpUserId);
		}

		List<MstCategory> categories = categoryMapper.find();
		List<MstProduct> products = productMapper.find();

		m.addAttribute("categories", categories);
		m.addAttribute("selected", 0);
		m.addAttribute("products", products);
		m.addAttribute("loginSession", loginSession);

		return "index";
	}
	
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {

		int category = f.getCategory();
		String key = f.getKeywords();
		
		List<MstCategory> categories = categoryMapper.find();
		
		if (key != null) {
			key = key.replaceAll("　"," ").replaceAll("\\s{2,}"," ").trim();
		}
		
		String[] keywords;
		if(key == null || key.isEmpty()) {
			keywords = new String[0];
		} else {
			keywords = key.split(" ");
		}
		
		List<MstProduct> products;
		if(category == 0 && keywords.length == 0) {
			products = productMapper.find();
		} else if(category == 0 && keywords.length > 0) {
			products = productMapper.findByProductName(keywords);
		} else {
			products = productMapper.findByCategoryAndProductName(category, keywords);
		}
		
	    m.addAttribute("categories", categories);
	    m.addAttribute("products", products);
		
	    m.addAttribute("selected",category);
	    m.addAttribute("keywords", key);
	    
	    m.addAttribute("loginSession", loginSession);
		
		return "index";
	}
}