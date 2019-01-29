package dev.mvc.question;

import java.util.HashMap;
import java.util.List;

public interface QuestionDAOInter {
  
  /**
   * ���
   * @param questionVO
   * @return
   */
  public int create(QuestionVO questionVO);
  
  /**
   * �Խ��� �� ��ü ����Դϴ�.
   * <select id="list" resultType="QuestionVO">
   * @return
   */
  public List<QuestionVO> list();
  
  /**
   * ȸ�� ��ȣ�� ����Դϴ�.
   * <select id="list_by_member" resultType="QuestionVO">
   * @return
   */
  public List<QuestionVO> list_by_member(int memberno); 
  
  /**
   * ��ü �ۼ�
   * @return
   */
  public int total_count();
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="BlogVO" parameterType="int">
   * @param blogno �� ��ȣ
   * @return
   */
  public QuestionVO read(int questionno);
  
  /**
   * ������
   * <select id="read" resultType="QuestionVO" parameterType="int">
   * @param questionno �� ��ȣ
   * @return
   */
  public QuestionVO upeate(int questionno);
  
  /**
   * ����ó��
   * <update id="update" parameterType="QuestionVO"> 
   * @param vo
   * @return
   */
  public int update(QuestionVO questionVO);
  
  /**
   * ����
   * @param contentsno
   * @return
   */
  public int delete(int questionno);
  
  /**
   * �˻� ���
   * @param questionno
   * @return
   */
  public List<QuestionVO> list_by_member_search(HashMap hashMap);

  /**
   * question���� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<QuestionVO> list_by_member_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * �ű� �亯�� �ֿ켱���� ����ϱ����� �亯 ���� ����
   * @param questionVO
   * @return
   */
  public int update_question_Ansnum(QuestionVO questionVO);
  
  /**
   * �亯
   * @param questionVO
   * @return
   */
  public int reply(QuestionVO questionVO);

}
