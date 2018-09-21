package com.nico.web.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nico.web.mybatis.entity.SysNotes;
import com.nico.web.mybatis.mapper.SysNotesMapper;
import com.nico.web.service.SysNotesService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2018-06-26
 */
@Service
public class SysNotesServiceImpl extends ServiceImpl<SysNotesMapper, SysNotes> implements SysNotesService {

}
