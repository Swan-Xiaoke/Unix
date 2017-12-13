package com.netCloud.admin.service;

import com.netCloud.admin.domain.Admin;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dllo on 17/12/7.
 */
public interface AdminService {

    List login(Admin admin, String verifyCode);

}
