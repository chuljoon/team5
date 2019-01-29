package dev.mvc.question;

public class Member_QuestionVO {
  // member table
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ȸ�� �̸� */
  private String m_name;
  /** ȸ�� �̸��� */
  private String m_email;
  
  // question table
  /* ���� ��ȣ */
  private int questionno;
  /* ���� ���� */
  private String question_title;
  /* ���� ���� */
  private String question_contents;
  /* ���� Thumb���� */
  private String question_thumb = "";
  /* ���� ���ϵ��� �̸� */
  private String question_file1 = "";
  /* ���� ���ϵ��� ũ�� */
  private long question_size1 = 0;
  /* ���� ��ȸ�� */
  private int question_cnt;
  /* ��ۼ� */
  private int question_replycnt;
  /* �亯���� */
  private int question_indent;
  /* �亯 ���� */
  private int question_ansnum;
  /* �˻��� */
  private String question_word = "";
  /* ����� */
  private String rdate;
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getM_name() {
    return m_name;
  }
  public void setM_name(String m_name) {
    this.m_name = m_name;
  }
  public String getM_email() {
    return m_email;
  }
  public void setM_email(String m_email) {
    this.m_email = m_email;
  }
  public int getQuestionno() {
    return questionno;
  }
  public void setQuestionno(int questionno) {
    this.questionno = questionno;
  }
  public String getQuestion_title() {
    return question_title;
  }
  public void setQuestion_title(String question_title) {
    this.question_title = question_title;
  }
  public String getQuestion_contents() {
    return question_contents;
  }
  public void setQuestion_contents(String question_contents) {
    this.question_contents = question_contents;
  }
  public String getQuestion_thumb() {
    return question_thumb;
  }
  public void setQuestion_thumb(String question_thumb) {
    this.question_thumb = question_thumb;
  }
  public String getQuestion_file1() {
    return question_file1;
  }
  public void setQuestion_file1(String question_file1) {
    this.question_file1 = question_file1;
  }
  public long getQuestion_size1() {
    return question_size1;
  }
  public void setQuestion_size1(long question_size1) {
    this.question_size1 = question_size1;
  }
  public int getQuestion_cnt() {
    return question_cnt;
  }
  public void setQuestion_cnt(int question_cnt) {
    this.question_cnt = question_cnt;
  }
  public int getQuestion_replycnt() {
    return question_replycnt;
  }
  public void setQuestion_replycnt(int question_replycnt) {
    this.question_replycnt = question_replycnt;
  }
  public int getQuestion_indent() {
    return question_indent;
  }
  public void setQuestion_indent(int question_indent) {
    this.question_indent = question_indent;
  }
  public int getQuestion_ansnum() {
    return question_ansnum;
  }
  public void setQuestion_ansnum(int question_ansnum) {
    this.question_ansnum = question_ansnum;
  }
  public String getQuestion_word() {
    return question_word;
  }
  public void setQuestion_word(String question_word) {
    this.question_word = question_word;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
  
}
