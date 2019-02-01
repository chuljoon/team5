package dev.mvc.gallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.gallery.FileVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class GalleryCont {
  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryProc")
  private GalleryProcInter galleryProc = null;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  /**
   * 갤러리 등록 폼
   * @param galleryno
   * @return
   */
  @RequestMapping(value = "/gallery/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("/member/login_need.jsp");
    } else {
      mav.setViewName("/gallery/create"); // /webapp/gallery/create.jsp
    }
    

    return mav;
  }

  /**
   * 갤러리 등록 처리
   * 
   * @param request
   * @param galleryVO
   * @return
   */
  @RequestMapping(value = "/gallery/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, GalleryVO galleryVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    galleryVO.setMemberno(1);

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/gallery/storage");
    List<MultipartFile> filesMF = galleryVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String sizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    System.out.println("--> 업로드된 파일 갯수 count: " + count);

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    galleryVO.setFiles(files);
    galleryVO.setSizes(sizes);
    galleryVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = galleryProc.create(galleryVO);
    /*    if (count == 1) {
      galleryProc.increaseCnt(galleryVO.getCategoryno()); // 글수 증가
    }*/

    mav.setViewName("redirect:/gallery/create_message.jsp?count=" + count); // /webapp/gallery/create_message.jsp
    // ?count=" + count + "&categoryno=" + contentsVO.getCategoryno()

    // mav.setViewName("redirect:/contents/list_by_category_search_paging.do?categoryno="
    // + contentsVO.getCategoryno());
    // mav.setViewName("redirect:/contents/list_all_category.do");

    return mav;
  }
  
  /**
   * 갤러리 전체 목록
   * @return
   */
  @RequestMapping(value = "/gallery/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<GalleryVO> list = galleryProc.list();
    mav.addObject("list", list);

    mav.setViewName("/gallery/list"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
  
  @RequestMapping(value = "/gallery/read.do", method = RequestMethod.GET)
  public ModelAndView read(int galleryno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/gallery/read"); // /webapp/contents/read.jsp

    GalleryVO galleryVO = galleryProc.read(galleryno);
    mav.addObject("galleryVO", galleryVO);

    ArrayList<FileVO> file_list = galleryProc.getThumbs(galleryVO);

    mav.addObject("file_list", file_list);

    return mav;
  }
  
//ZIP 압축 후 파일 다운로드
 @RequestMapping(value = "/gallery/download.do", method = RequestMethod.GET)
 public ModelAndView download(HttpServletRequest request, int galleryno, HttpSession session) {
   ModelAndView mav = new ModelAndView();

   GalleryVO galleryVO = galleryProc.read(galleryno);

   String files = galleryVO.getFiles();
   String[] files_array = files.split("/");

   String dir = "/gallery/storage";
   String upDir = Tool.getRealPath(request, dir);

   String zip = "download_files.zip";
   String zip_filename = upDir + zip;

   String[] zip_src = new String[files_array.length]; // 파일 갯수만큼 배열 선언

   for (int i = 0; i < files_array.length; i++) {
     zip_src[i] = upDir + "/" + files_array[i]; // 절대 경로 조합
   }

   byte[] buffer = new byte[4096]; // 4 KB

   try {
     ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zip_filename));

     for (int index = 0; index < zip_src.length; index++) {
       FileInputStream in = new FileInputStream(zip_src[index]);

       Path path = Paths.get(zip_src[index]);
       String zip_src_file = path.getFileName().toString();
       // System.out.println("zip_src_file: " + zip_src_file);

       ZipEntry zipEntry = new ZipEntry(zip_src_file);
       zipOutputStream.putNextEntry(zipEntry);

       int length = 0;
       // 4 KB씩 읽어서 buffer 배열에 저장후 읽은 바이트수를 length에 저장
       while ((length = in.read(buffer)) > 0) {
         zipOutputStream.write(buffer, 0, length); // 기록할 내용, 내용에서의 시작 위치, 기록할
                                                   // 길이

       }
       zipOutputStream.closeEntry();
       in.close();
     }
     zipOutputStream.close();

     File file = new File(zip_filename);

     if (file.exists() && file.length() > 0) {
       System.out.println(zip_filename + "이 압축되어 생성?습니다.");
     }

     // if (file.delete() == true) {
     // System.out.println(zip_filename + " 파일을 삭제했습니다.");
     // }

   } catch (FileNotFoundException e) {
     e.printStackTrace();
   } catch (IOException e) {
     e.printStackTrace();
   }

   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("/member/login_need_g.jsp");
   } else {
  // download 서블릿 연결
     mav.setViewName("redirect:/download?dir=" + dir + "&filename=" + zip);
   }
   

   return mav;
 }
 
 /**
  * 다중 파일 수정 폼
  * @param galleryno
  * @return
  */
 @RequestMapping(value = "/gallery/update.do", method = RequestMethod.GET)
 public ModelAndView update(int galleryno, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("/member/login_need_g.jsp");
   } else {
     mav.setViewName("/gallery/update"); // /webapp/contents/update.jsp
   }
   

   GalleryVO galleryVO = galleryProc.read(galleryno);
   mav.addObject("galleryVO", galleryVO);

/*   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                  // 정보
                                                                                  // 추출
   mav.addObject("categoryVO", categoryVO);*/

   ArrayList<FileVO> file_list = galleryProc.getThumbs(galleryVO);

   mav.addObject("file_list", file_list);

   return mav;
 }
 
 /**
  * 글만 수정하는 경우의 구현
  * 파일만 수정하는 경우의 구현
  * 글과 파일을 동시에 수정하는 경우의 구현
  * @param redirectAttributes
  * @param request
  * @param galleryVO
  * @return
  */
 @RequestMapping(value = "/gallery/update.do", method = RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, GalleryVO galleryVO) {
   ModelAndView mav = new ModelAndView();

   // -------------------------------------------------------------------
   // 파일 전송 코드 시작
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/gallery/storage");
   List<MultipartFile> filesMF = galleryVO.getFilesMF(); // Spring이 File 객체를
                                                          // 저장해둠.

   // System.out.println("--> filesMF.get(0).getSize(): " +
   // filesMF.get(0).getSize());

   String files = ""; // 컬럼에 저장할 파일명
   String files_item = ""; // 하나의 파일명
   String sizes = "";
   long sizes_item = 0; // 하나의 파일 사이즈
   String thumbs = ""; // Thumb 파일들
   String thumbs_item = ""; // 하나의 Thumb 파일명

   int count = filesMF.size(); // 업로드된 파일 갯수

   // 기존의 등록 정보 조회
   GalleryVO galleryVO_old = galleryProc.read(galleryVO.getGalleryno());
   if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
     // thumbs 파일 삭제
     String thumbs_old = galleryVO_old.getThumbs();
     StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
     while (thumbs_st.hasMoreTokens()) {
       String fname = upDir + thumbs_st.nextToken();
       Tool.deleteFile(fname);
     }

     // 원본 파일 삭제
     String files_old = galleryVO_old.getFiles();
     StringTokenizer files_st = new StringTokenizer(files_old, "/");
     while (files_st.hasMoreTokens()) {
       String fname = upDir + files_st.nextToken();
       Tool.deleteFile(fname);
     }

     // --------------------------------------------
     // 새로운 파일의 등록 시작
     // --------------------------------------------
     // for (MultipartFile multipartFile: filesMF) {
     for (int i = 0; i < count; i++) {
       MultipartFile multipartFile = filesMF.get(i); // 0 ~
       System.out.println("multipartFile.getName(): " + multipartFile.getName());

       // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
       if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
         // System.out.println("전송 파일이 있습니다.");
         files_item = Upload.saveFileSpring(multipartFile, upDir);
         sizes_item = multipartFile.getSize();

         if (Tool.isImage(files_item)) {
           thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                   // 생성
         }

         if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
           // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
           files = files + "/" + files_item;
           // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
           sizes = sizes + "/" + sizes_item;
           // 미니 이미지를 조합하여 하나의 컬럼에 저장
           thumbs = thumbs + "/" + thumbs_item;
         } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                   // 크기 체크
           files = files_item; // file1.jpg
           sizes = "" + sizes_item; // 123456
           thumbs = thumbs_item; // file1_t.jpg
         }

       }
     } // for END
     // --------------------------------------------
     // 새로운 파일의 등록 종료
     // --------------------------------------------

   } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
     files = galleryVO_old.getFiles();
     sizes = galleryVO_old.getSizes();
     thumbs = galleryVO_old.getThumbs();
   }
   galleryVO.setFiles(files);
   galleryVO.setSizes(sizes);
   galleryVO.setThumbs(thumbs);

   galleryVO.setMemberno(1); // 회원 개발후 session으로 변경

   count = galleryProc.update(galleryVO);

   redirectAttributes.addAttribute("count", count);

   // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
   redirectAttributes.addAttribute("galleryno", galleryVO.getGalleryno());
/*   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());*/

   mav.setViewName("redirect:/gallery/update_message.jsp");

   return mav;

 }
 
 /**
  * 삭제 폼
  * @param galleryno
  * @return
  */
 @RequestMapping(value = "/gallery/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int galleryno, HttpSession session) {
   // System.out.println("--> delete() GET executed");
   ModelAndView mav = new ModelAndView();
   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("/member/login_need_g.jsp");
   } else {
     mav.setViewName("/gallery/delete"); // /webapp/contents/delete.jsp
   }
   

/*   Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
   mav.addObject("categoryVO", categoryVO);*/

   GalleryVO galleryVO = galleryProc.read(galleryno);
   mav.addObject("galleryVO", galleryVO);

   return mav;
 }
 
 /**
  * 삭제 처리
  * @param redirectAttributes
  * @param request
  * @param galleryno
  * @return
  */
 @RequestMapping(value = "/gallery/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                       HttpServletRequest request, 
                                       int galleryno
/*                                       @RequestParam(value="word", defaultValue="") String word,
                                       @RequestParam(value="nowPage", defaultValue="1") int nowPage */
     ) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/gallery/delete_message"); // /webapp/contents/delete_message.jsp

   String upDir = Tool.getRealPath(request, "/gallery/storage"); // 저장 폴더 절대
                                                                  // 경로

   GalleryVO galleryVO = galleryProc.read(galleryno); // 삭제할 파일 정보를 읽기 위한
                                                          // 목적

   String thumbs_old = galleryVO.getThumbs();
   String files_old = galleryVO.getFiles();

   StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
   while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
     String fname = upDir + thumbs_st.nextToken(); // 단어 추출
     Tool.deleteFile(fname);
   }

   StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
   while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
     String fname = upDir + files_st.nextToken(); // 단어 추출
     Tool.deleteFile(fname);
   }

   int count = galleryProc.delete(galleryno); // 레코드 삭제

/*   if (count == 1) {
     categoryProc.decreaseCnt(categoryno); // 등록된 글수의 감소
*/     
     // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
     // 2페이지에서 1 페이지로 줄여야합니다. 
/*     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("categoryno", categoryno); // #{categoryno}
     hashMap.put("word", word);                  // #{word}
     if (contentsProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
       nowPage = nowPage - 1;
       if (nowPage < 1){
         nowPage = 1;
       }
     }
     
   }*/

   // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("galleryno", galleryVO.getGalleryno());
/*   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());
   redirectAttributes.addAttribute("word", word);
   redirectAttributes.addAttribute("nowPage", nowPage);*/

   mav.setViewName("redirect:/gallery/delete_message.jsp");

   return mav;
 }
 

}
