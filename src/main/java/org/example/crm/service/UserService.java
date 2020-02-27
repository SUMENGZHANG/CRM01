package org.example.crm.service;


import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseService;
import org.example.crm.Utils.AssterUtil;
import org.example.crm.Utils.Md5Util;
import org.example.crm.Utils.UserIDBase64;
import org.example.crm.dao.UserMapper;
import org.example.crm.model.UserModel;
import org.example.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired
    private UserMapper userMapper;
    public UserModel login(String userName,String userPwd){
        /**
         * args: username and userpwd are not null;
         *  userName is valid or not
         *  password is right or not
         *
         *
         */
        checkLoginParams(userName,userPwd);
        User user = userMapper.queryUserByUserName(userName);
        AssterUtil.isTrue(null==user,"用户已注销或者不存在");
        AssterUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"密码错误");
        return buildUserModelInfo(user);

    }
    public UserModel buildUserModelInfo(User user){
        return new UserModel(UserIDBase64.encoderUserID(user.getId()),user.getUserName(),user.getTrueName());
    }
    private void checkLoginParams(String userName,String userPwd){
        AssterUtil.isTrue(StringUtils.isBlank(userName),"用户不能为空");
        AssterUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){
        /**
         *  userId is not null
         *  old password is not null and must be right;
         *  new password is not null
         *  confirm password should be same as the new password;
         *
         *  encode new password
         */
        checkParams(userId,oldPassword,newPassword,confirmPassword);
        User user = selectByPrimaryKey(userId);
        user.setUserPwd(Md5Util.encode(newPassword));
        AssterUtil.isTrue(updateByPrimaryKeySelective(user)<1,"更新失败");


    }

    private void checkParams(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = selectByPrimaryKey(userId);
        AssterUtil.isTrue(null==userId|| null==selectByPrimaryKey(userId),"用户未登陆或不存在");
        AssterUtil.isTrue(StringUtils.isBlank(oldPassword),"请输入原始密码");
        AssterUtil.isTrue(StringUtils.isBlank(newPassword),"请输入新的密码");
        AssterUtil.isTrue(StringUtils.isBlank(confirmPassword),"请输入确认密码");
        AssterUtil.isTrue(!(newPassword.equals(confirmPassword)),"新密码输入不一致");
        AssterUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))),"原来密码不正确");
        AssterUtil.isTrue(oldPassword.equals(newPassword),"新密码不能和原来密码一样");

    }


}
