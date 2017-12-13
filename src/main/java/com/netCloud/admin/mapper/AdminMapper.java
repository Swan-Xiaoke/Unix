package com.netCloud.admin.mapper;

import com.netCloud.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminMapper {

    List<Admin> login(Admin record);

}