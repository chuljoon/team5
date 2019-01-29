package dev.mvc.question;

import java.util.HashMap;
import java.util.List;

public interface QuestionDAOInter {
  
  /**
   * 등록
   * @param questionVO
   * @return
   */
  public int create(QuestionVO questionVO);
  
  /**
   * 게시판 글 전체 목록입니다.
   * <select id="list" resultType="QuestionVO">
   * @return
   */
  public List<QuestionVO> list();
  
  /**
   * 회원 번호별 목록입니다.
   * <select id="list_by_member" resultType="QuestionVO">
   * @return
   */
  public List<QuestionVO> list_by_member(int memberno); 
  
  /**
   * 전체 글수
   * @return
   */
  public int total_count();
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="BlogVO" parameterType="int">
   * @param blogno 글 번호
   * @return
   */
  public QuestionVO read(int questionno);
  
  /**
   * 수정폼
   * <select id="read" resultType="QuestionVO" parameterType="int">
   * @param questionno 글 번호
   * @return
   */
  public QuestionVO upeate(int questionno);
  
  /**
   * 수정처리
   * <update id="update" parameterType="QuestionVO"> 
   * @param vo
   * @return
   */
  public int update(QuestionVO questionVO);
  
  /**
   * 삭제
   * @param contentsno
   * @return
   */
  public int delete(int questionno);
  
  /**
   * 검색 목록
   * @param questionno
   * @return
   */
  public List<QuestionVO> list_by_member_search(HashMap hashMap);

  /**
   * question에서 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<QuestionVO> list_by_member_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * 신규 답변을 최우선으로 출력하기위한 답변 순서 조절
   * @param questionVO
   * @return
   */
  public int update_question_Ansnum(QuestionVO questionVO);
  
  /**
   * 답변
   * @param questionVO
   * @return
   */
  public int reply(QuestionVO questionVO);

}
