package com.sunny.module.sys.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunny.core.ControllerHelper;
import com.sunny.core.SpringContextHolder;
import com.sunny.core.base.controller.AbstractInternalController;
import com.sunny.module.sys.SysRestApiConstants;
import com.sunny.module.sys.post.dto.PostDto;
import com.sunny.module.sys.post.entity.Post;
import com.sunny.module.sys.post.service.PostService;

@Controller
@RequestMapping(SysRestApiConstants.REST_API_POST)
public class PostController extends AbstractInternalController<Post, PostDto> {
	
	private PostService postService = SpringContextHolder.getBean(PostService.class);

	@Override
	protected ControllerHelper<Post, PostDto> helper() {
		return ControllerHelper.all(postService, Post.of(), new PostDto(), SysRestApiConstants.REST_API_POST_LIST);
	}

//	@RequiresPermissions("sys:org:view")
	public String list() {
		return toView();
	}

}
