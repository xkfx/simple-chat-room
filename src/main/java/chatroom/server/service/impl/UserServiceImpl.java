package chatroom.server.service.impl;

import chatroom.common.entity.User;
import chatroom.common.message.Message;
import chatroom.server.dao.UserDAO;
import chatroom.server.dao.impl.UserDAOImpl;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;
import chatroom.server.service.UserService;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final Map<Socket, User> socketUserMap = new HashMap<>();
    private final Map<Long, User> longUserMap = new HashMap<>();
    private final Map<Long, Socket> longSocketMap = new HashMap<>();

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public Socket getSocket(Long userId) {
        return longSocketMap.get(userId);
    }

    @Override
    public User getUser(Socket socket) {
        return socketUserMap.get(socket);
    }

    @Override
    public User getUser(Long userId) {
        return longUserMap.get(userId);
    }

    @Override
    public List<User> getFriendList(Long userId) {
        return userDAO.getFriendList(userId);
    }

    @Override
    public Message register(Register reg) {
        String regName = reg.getUsername();
        String existName = userDAO.getUserByUsername(regName).getUsername();
        if (!regName.equals(existName)) {
            User user = new User();
            user.setUsername(reg.getUsername());
            user.setPassword(reg.getPassword());
            if (userDAO.saveUser(user)) {
                return Message.ok("恭喜你，注册成功");
            } else {
                return Message.fail("服务器异常,注册失败");
            }
        } else {
            return Message.fail("该用户名已经被注册了哦~");
        }
    }

    @Override
    public Message login(Socket socket, Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        User user = userDAO.getUserByUsername(username);

        if (user !=null && user.getPassword() != null
                && user.getPassword().equals(password)) {

            socketUserMap.put(socket, user);
            longSocketMap.put(user.getUserId(), socket);
            longUserMap.put(user.getUserId(), user);

            return Message.ok("");
        }
        return Message.fail("用户名或者密码错误");
    }



    @Override
    public Message logout(Socket socket) {
        User user = null;
        if (getUser(socket) != null) {
            user = getUser(socket);
            longUserMap.remove(user.getUserId());
            socketUserMap.remove(user.getUserId());
        }
        socketUserMap.remove(socket);
        return null;
    }
}
