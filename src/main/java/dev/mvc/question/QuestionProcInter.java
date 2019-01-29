package dev.mvc.question;

import java.util.HashMap;
import java.util.List;

public interface QuestionProcInter {
  
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
   * �Խ��� �� ��ü ����Դϴ�.
   * <select id="list_by_member" resultType="QuestionVO">
   * @return
   */
  public List<QuestionVO> list_by_member(int memberno); 
  
  /**
   * ��ü ���ڵ� ����
   * <select id="total_count" resultType="int"> 
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
   * question�� �˻��� ���ڵ� ����
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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int memberno, int search_count, int nowPage, String question_word);
  
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
