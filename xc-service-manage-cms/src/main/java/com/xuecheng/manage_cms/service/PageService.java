package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;

import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     *
     * @param page 当前页码
     * @param size  每页显示条数
     * @param queryPageRequest  查询条件
     * @return 页面列表
     */
    public QueryResponseResult findList(int page,int size,QueryPageRequest queryPageRequest){

        if (page<=0){
            page=1;
        }
        page=page-1;
        if (size<=0){
            size=20;
        }

        Pageable pageable= new PageRequest(page,size);//分页查询对象
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        QueryResult<CmsPage> list =new QueryResult();
        list.setList(all.getContent());//获取查询结果中的集合
        list.setTotal(all.getTotalElements());//获取查询结果中的信息

        return new QueryResponseResult(CommonCode.SUCCESS,list);

    }

}
