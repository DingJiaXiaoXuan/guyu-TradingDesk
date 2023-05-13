package com.guyu.service.impl;

import com.guyu.entity.Size;
import com.guyu.mapper.SizeMapper;
import com.guyu.service.SizeService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeMapper sizeMapper;

    @Override
    public int SizeAdd(Size size) {
        return sizeMapper.AddSize(size);
    }

    @Override
    public List<Size> FindSize(Size size) {
        return sizeMapper.QuerySize(size.getPro_id());
    }

    @Override
    public List<Size> FindSizeAfter(Size size) {
        return sizeMapper.QuerySizeAfter(size.getPro_id());
    }

    @Override
    public int SizeDel(Size size) {
        return sizeMapper.DelSize(size.getSize_id());
    }

    @Override
    public int SizeUpdata(Size size) {
        sizeMapper.UpdateTime(size.getSize_id());
        return sizeMapper.UpdateSize(size);
    }

    @Override
    public int UpdateStatus(Size size) {
        sizeMapper.UpdateTime(size.getSize_id());
        return sizeMapper.UpdateStatus(size.getSize_status(),size.getSize_id());
    }
}
