package dev.mvc.question;

import org.springframework.web.multipart.MultipartFile;

public class QuestionVO {
  /** 아이디, member */
  private String m_nickname = "";
  
  public String getM_nickname() {
    return m_nickname;
  }
  public void setM_nickname(String m_nickname) {
    this.m_nickname = m_nickname;
  }
  /* 질문 번호 */
  private int questionno;
  /* 회원 번호 */
  private int memberno;
  /* 질문 제목 */
  private String question_title;
  /* 질문 내용 */
  private String question_contents;
  /* 질문 Thumb파일 */
  private String question_thumb = "";
  /* 질문 파일들의 이름 */
  private String question_file1 = "";
  /* 질문 파일들의 크기 */
  private long question_size1 = 0;
  /* 질문 조회수 */
  private int question_cnt;
  /* 댓글수 */
  private int question_replycnt;
  /* 그룹 번호 */
  private int question_grpno;
  /* 답변차수 */
  private int question_indent;
  /* 답변 순서 */
  private int question_ansnum;
  /* 검색어 */
  private String question_word = "";
  /* 등록일 */
  private String rdate;
  
  // ---------------------------------------------------------
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
*/  
  private MultipartFile file1MF;

/** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size1Label;
  
  // ---------------------------------------------------------

  public int getQuestionno() {
    return questionno;
  }
  public int getQuestion_grpno() {
    return question_grpno;
  }
  public void setQuestion_grpno(int question_grpno) {
    this.question_grpno = question_grpno;
  }
  public void setQuestionno(int questionno) {
    this.questionno = questionno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
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
    if (question_word == null) {
      question_word = "";
    }
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
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getSize1Label() {
    return size1Label;
  }
  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  
  
  
  
  
  
}