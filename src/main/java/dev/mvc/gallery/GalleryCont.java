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
   * ������ ��� ��
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
   * ������ ��� ó��
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/gallery/storage");
    List<MultipartFile> filesMF = galleryVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String sizes = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    System.out.println("--> ���ε�� ���� ���� count: " + count);

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    count = galleryProc.create(galleryVO);
    /*    if (count == 1) {
      galleryProc.increaseCnt(galleryVO.getCategoryno()); // �ۼ� ����
    }*/

    mav.setViewName("redirect:/gallery/create_message.jsp?count=" + count); // /webapp/gallery/create_message.jsp
    // ?count=" + count + "&categoryno=" + contentsVO.getCategoryno()

    // mav.setViewName("redirect:/contents/list_by_category_search_paging.do?categoryno="
    // + contentsVO.getCategoryno());
    // mav.setViewName("redirect:/contents/list_all_category.do");

    return mav;
  }
  
  /**
   * ������ ��ü ���
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
  
//ZIP ���� �� ���� �ٿ�ε�
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

   String[] zip_src = new String[files_array.length]; // ���� ������ŭ �迭 ����

   for (int i = 0; i < files_array.length; i++) {
     zip_src[i] = upDir + "/" + files_array[i]; // ���� ��� ����
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
       // 4 KB�� �о buffer �迭�� ������ ���� ����Ʈ���� length�� ����
       while ((length = in.read(buffer)) > 0) {
         zipOutputStream.write(buffer, 0, length); // ����� ����, ���뿡���� ���� ��ġ, �����
                                                   // ����

       }
       zipOutputStream.closeEntry();
       in.close();
     }
     zipOutputStream.close();

     File file = new File(zip_filename);

     if (file.exists() && file.length() > 0) {
       System.out.println(zip_filename + "�� ����Ǿ� ����?���ϴ�.");
     }

     // if (file.delete() == true) {
     // System.out.println(zip_filename + " ������ �����߽��ϴ�.");
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
  // download ���� ����
     mav.setViewName("redirect:/download?dir=" + dir + "&filename=" + zip);
   }
   

   return mav;
 }
 
 /**
  * ���� ���� ���� ��
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

/*   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                  // ����
                                                                                  // ����
   mav.addObject("categoryVO", categoryVO);*/

   ArrayList<FileVO> file_list = galleryProc.getThumbs(galleryVO);

   mav.addObject("file_list", file_list);

   return mav;
 }
 
 /**
  * �۸� �����ϴ� ����� ����
  * ���ϸ� �����ϴ� ����� ����
  * �۰� ������ ���ÿ� �����ϴ� ����� ����
  * @param redirectAttributes
  * @param request
  * @param galleryVO
  * @return
  */
 @RequestMapping(value = "/gallery/update.do", method = RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, GalleryVO galleryVO) {
   ModelAndView mav = new ModelAndView();

   // -------------------------------------------------------------------
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/gallery/storage");
   List<MultipartFile> filesMF = galleryVO.getFilesMF(); // Spring�� File ��ü��
                                                          // �����ص�.

   // System.out.println("--> filesMF.get(0).getSize(): " +
   // filesMF.get(0).getSize());

   String files = ""; // �÷��� ������ ���ϸ�
   String files_item = ""; // �ϳ��� ���ϸ�
   String sizes = "";
   long sizes_item = 0; // �ϳ��� ���� ������
   String thumbs = ""; // Thumb ���ϵ�
   String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

   int count = filesMF.size(); // ���ε�� ���� ����

   // ������ ��� ���� ��ȸ
   GalleryVO galleryVO_old = galleryProc.read(galleryVO.getGalleryno());
   if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
     // thumbs ���� ����
     String thumbs_old = galleryVO_old.getThumbs();
     StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
     while (thumbs_st.hasMoreTokens()) {
       String fname = upDir + thumbs_st.nextToken();
       Tool.deleteFile(fname);
     }

     // ���� ���� ����
     String files_old = galleryVO_old.getFiles();
     StringTokenizer files_st = new StringTokenizer(files_old, "/");
     while (files_st.hasMoreTokens()) {
       String fname = upDir + files_st.nextToken();
       Tool.deleteFile(fname);
     }

     // --------------------------------------------
     // ���ο� ������ ��� ����
     // --------------------------------------------
     // for (MultipartFile multipartFile: filesMF) {
     for (int i = 0; i < count; i++) {
       MultipartFile multipartFile = filesMF.get(i); // 0 ~
       System.out.println("multipartFile.getName(): " + multipartFile.getName());

       // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
       if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
         // System.out.println("���� ������ �ֽ��ϴ�.");
         files_item = Upload.saveFileSpring(multipartFile, upDir);
         sizes_item = multipartFile.getSize();

         if (Tool.isImage(files_item)) {
           thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                   // ����
         }

         if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
           // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
           files = files + "/" + files_item;
           // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
           sizes = sizes + "/" + sizes_item;
           // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
           thumbs = thumbs + "/" + thumbs_item;
         } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                   // ũ�� üũ
           files = files_item; // file1.jpg
           sizes = "" + sizes_item; // 123456
           thumbs = thumbs_item; // file1_t.jpg
         }

       }
     } // for END
     // --------------------------------------------
     // ���ο� ������ ��� ����
     // --------------------------------------------

   } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
     files = galleryVO_old.getFiles();
     sizes = galleryVO_old.getSizes();
     thumbs = galleryVO_old.getThumbs();
   }
   galleryVO.setFiles(files);
   galleryVO.setSizes(sizes);
   galleryVO.setThumbs(thumbs);

   galleryVO.setMemberno(1); // ȸ�� ������ session���� ����

   count = galleryProc.update(galleryVO);

   redirectAttributes.addAttribute("count", count);

   // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
   redirectAttributes.addAttribute("galleryno", galleryVO.getGalleryno());
/*   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());*/

   mav.setViewName("redirect:/gallery/update_message.jsp");

   return mav;

 }
 
 /**
  * ���� ��
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
  * ���� ó��
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

   String upDir = Tool.getRealPath(request, "/gallery/storage"); // ���� ���� ����
                                                                  // ���

   GalleryVO galleryVO = galleryProc.read(galleryno); // ������ ���� ������ �б� ����
                                                          // ����

   String thumbs_old = galleryVO.getThumbs();
   String files_old = galleryVO.getFiles();

   StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
   while (thumbs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
     String fname = upDir + thumbs_st.nextToken(); // �ܾ� ����
     Tool.deleteFile(fname);
   }

   StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
   while (files_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
     String fname = upDir + files_st.nextToken(); // �ܾ� ����
     Tool.deleteFile(fname);
   }

   int count = galleryProc.delete(galleryno); // ���ڵ� ����

/*   if (count == 1) {
     categoryProc.decreaseCnt(categoryno); // ��ϵ� �ۼ��� ����
*/     
     // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
     // 2���������� 1 �������� �ٿ����մϴ�. 
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

   // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("galleryno", galleryVO.getGalleryno());
/*   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());
   redirectAttributes.addAttribute("word", word);
   redirectAttributes.addAttribute("nowPage", nowPage);*/

   mav.setViewName("redirect:/gallery/delete_message.jsp");

   return mav;
 }
 

}
