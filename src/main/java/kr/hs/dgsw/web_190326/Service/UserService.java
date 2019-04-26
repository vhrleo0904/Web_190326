package kr.hs.dgsw.web_190326.Service;

import kr.hs.dgsw.web_190326.Domain.User;

import java.util.List;

public interface UserService {

    List<User> listUser();

    User viewUser(Long id);

    User loginUser(User user);

    User addUser(User user);

    boolean removeUser(Long id);

    User editUser(Long id, User user);
}
