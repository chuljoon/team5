package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.gallery.FileVO;
import nation.web.tool.Tool;

@Component("dev.mvc.gallery.GalleryProc")
public class GalleryProc implements GalleryProcInter {
  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryDAO")
  private GalleryDAOInter galleryDAO = null;
  
  public GalleryProc() {
    System.out.println("--> GalleryProc created.");
  }

  @Override
  public int create(GalleryVO galleryVO) {
    return galleryDAO.create(galleryVO);
  }

  @Override
  public List<GalleryVO> list() {
    return galleryDAO.list();
  }

  @Override
  public int total_count() {
    return galleryDAO.total_count();
  }

  @Override
  public GalleryVO read(int galleryno) {
    return galleryDAO.read(galleryno);
  }


  /**
   * 이미지 목록중에 첫번째 이미지 파일명을 추출하여 리턴
   * @param galleryVO
   * @return
   */
  public String getThumb(GalleryVO galleryVO) {
    String thumbs = galleryVO.getThumbs();
    String thumb ="";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count >0) {
        thumb = thumbs_array[0];
      }
    }
    return thumb;
  }

  @Override
  public ArrayList<FileVO> getThumbs(GalleryVO galleryVO) {
ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = galleryVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = galleryVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = galleryVO.getSizes();        // 272558/404087... 
    
    String[] thumbs_array = thumbs.split("/");  // Thumbs
    String[] files_array = files.split("/");   // 파일명 추출
    String[] sizes_array = sizes.split("/"); // 파일 사이즈
 
    int count = sizes_array.length;
    // System.out.println("sizes_array.length: " + sizes_array.length);
    // System.out.println("sizes: " + sizes);
    // System.out.println("files: " + files);
 
    if (files.length() > 0) {
      for (int index = 0; index < count; index++) {
        sizes_array[index] = Tool.unit(new Integer(sizes_array[index]));  // 1024 -> 1KB
      
        FileVO fileVO = new FileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }

  @Override
  public int update(GalleryVO galleryVO) {
    return galleryDAO.update(galleryVO);
  }

  @Override
  public int delete(int galleryno) {
    return galleryDAO.delete(galleryno);
  }

  @Override
  public List<GalleryVO> list_search(HashMap hashMap) {
    List<GalleryVO> list = galleryDAO.list_search(hashMap);
    
    int count = list.size();
    for(int i=0; i < count; i++) {
      GalleryVO galleryVO = list.get(i);
      galleryVO.setThumbs(getThumb(galleryVO)); 
    }
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    return galleryDAO.search_count(hashMap);
  }

  @Override
  public int increaseCnt(int galleryno) {
    return galleryDAO.increaseCnt(galleryno);
  }
  
  
  
}
