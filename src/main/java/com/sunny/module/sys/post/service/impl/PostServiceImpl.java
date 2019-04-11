package com.sunny.module.sys.post.service.impl;

import com.sunny.core.base.service.CudServiceImpl;
import com.sunny.module.sys.post.entity.Post;
import com.sunny.module.sys.post.mapper.PostMapper;
import com.sunny.module.sys.post.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends CudServiceImpl<Post, PostMapper> implements PostService {



}
