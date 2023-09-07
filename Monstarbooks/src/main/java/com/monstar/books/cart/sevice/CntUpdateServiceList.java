package com.monstar.books.cart.sevice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.monstar.books.cart.dao.CartDao;

@Service
public class CntUpdateServiceList implements CartService {

	@Autowired
	private SqlSession sqlSession;

	// 생성자
	public CntUpdateServiceList(SqlSession session) {
		this.sqlSession = session;
	}

	@Override
	public void execute(Model model) {
		
		System.out.println(">>>수량 감소");
		
//		map 변환
		Map<String, Object> map = model.asMap();
		
//		map에서 request 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		CartDao dao = sqlSession.getMapper(CartDao.class);

		// 세션에서 회원 ID 가져오기
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("id");
        System.out.println("id :"+memberId);
        
        int memberno = dao.getMemberno(memberId);		
        System.out.println("memberno :"+memberno);
        
		String cnt = request.getParameter("ccount");
		String cartno = request.getParameter("cartno");
		System.out.println(cnt);
		
		dao.cntUpdate(cnt,cartno,memberno);


	}// override method

}// class