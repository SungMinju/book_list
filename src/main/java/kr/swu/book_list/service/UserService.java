package kr.swu.book_list.service;

import kr.swu.book_list.entity.UserVo;
import kr.swu.book_list.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private static UserMapper userMapper;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    public static UserVo getUserById(String id) {
        return userMapper.getUserById(id);
    }


    public static void signup(UserVo userVo) { // 회원 가입

        // password는 암호화해서 DB에 저장
        userVo.setPw(passwordEncoder.encode(UserVo.getPw()));
        userMapper.insertUser(userVo);

    }

    public static void edit(UserVo userVo) { // 회원 정보 수정
        // password는 암호화해서 DB에 저장
        userVo.setPw(passwordEncoder.encode(UserVo.getPw()));
        userMapper.updateUser(userVo);
    }
    public static void withdraw(String id) { // 회원 탈퇴
        userMapper.deleteUser(id);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
