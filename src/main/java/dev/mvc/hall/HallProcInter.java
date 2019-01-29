package dev.mvc.hall;

import java.util.List;

public interface HallProcInter {
  
  /**
   * ������ ���
   * @param hallVO
   * @return
   */
  public int create(HallVO hallVO);
  
  /**
   * ���
   * @return
   */
  public List<HallVO> list();
  
  /**
   * ��ȸ
   * @param hallno
   * @return
   */
  public HallVO read(int hallno);
  
  /**
   * ������
   * @param hallno
   * @return
   */
  public HallVO update(int hallno);
  
  /**
   * ����ó��
   * @param hallVO
   * @return
   */
  public int update(HallVO hallVO);
  
  /**
   * ����
   * @param hallno
   * @return
   */
  public int delete(int hallno);

}
