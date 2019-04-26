package kr.hs.dgsw.web_190326.Service;

import kr.hs.dgsw.web_190326.Domain.User;
import kr.hs.dgsw.web_190326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User viewUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    @Override
    public User loginUser(User user) {
        List<User> userList = this.userRepository.findAll();

        for(User f : userList) {
            if(f.getEmail().equals(user.getEmail()) && f.getPassword().equals(user.getPassword())) {
                return f;
            }
        }

        return null;
    }

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent()) return null;
        return this.userRepository.save(user);
    }

    @Override
    public boolean removeUser(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User editUser(Long id, User user) {
        return this.userRepository.findById(id)
                .map(f -> {
                    f.setPath(Optional.ofNullable(user.getPath()).orElse(f.getPath()));
                    f.setImagename(Optional.ofNullable(user.getImagename()).orElse(f.getImagename()));
                    return this.userRepository.save(f);
                })
                .orElse(null);
    }
}
