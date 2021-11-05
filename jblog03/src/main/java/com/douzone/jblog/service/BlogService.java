package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryDTO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;

@Service
public class BlogService {
	private static String SAVE_PATH = "/upload-jblog";
	private static String URL_BASE = "/upload/images";
	
	@Autowired
	private BlogRepository blogRepository;

	//블로그 정보 호출
	public BlogVO getBlogContent(String id) {
		BlogVO boardVO = blogRepository.blogFindById(id);
		
		return boardVO;
	}

	//전체 카테고리 리스트 호출
	public List<CategoryVO> getCategoryContent(String id) {
		return blogRepository.categoryFind(id);
	}
	
	//전체 포스트 리스트 호출
	public List<PostVO> getPostContent(String id) {
		return blogRepository.postFind(id);
	}

	//선택한 카테고리 내용 호출
	public CategoryVO getCategoryContent(Optional<Integer> categoryNo) {
		CategoryVO categoryVO = blogRepository.categoryFindByNo(categoryNo);
		
		return categoryVO;
	}

	//블로그 수정
	public boolean modifyBlogContent(BlogVO blogVO) {
		
		int count = blogRepository.update( blogVO );
		return count == 1;		
	}

	//가장 최신 포스트
	public PostVO getRecentPost(String blogid) {
		PostVO postVO = blogRepository.getRecentPost(blogid);
		
		return postVO;
	}

	//이미지 구하기
	public String saveImage(MultipartFile logo) throws Exception {
		BlogVO vo = new BlogVO();
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdir();
			}
			
			if(logo.isEmpty()) {
				throw new Exception("file upload error: image empty");
			}
			
			String originFilename = logo.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			
			byte[] data = logo.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			
			vo.setLogo(URL_BASE + "/" + saveFilename);
//			vo.setComments(comments);
			
//			galleryRepository.insert(vo);
		} catch(IOException ex) {
			throw new Exception("file upload error:" + ex);
		}
		
		return vo.getLogo();
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

	public List<CategoryDTO> getCategoryContentWithCount(String id) {
		return blogRepository.categorywithcountFind(id);
	}

	public boolean addCategoryPost(PostVO postVO) {
		
		int count = blogRepository.addPost( postVO );
		return count == 1;					
	}

	
}
