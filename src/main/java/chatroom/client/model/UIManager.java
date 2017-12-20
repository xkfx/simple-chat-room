package chatroom.client.model;

import chatroom.client.ui.component.UserFrame;
import chatroom.client.ui.component.LoginFrame;
import chatroom.client.ui.component.MessageDisplayPanel;

import javax.swing.*;

/**
 * 便于组件之间交互
 */
public class UIManager {

    private ClientMessageService clientMessageService;
    private MessageDisplayPanel messageDisplayPanel;

    public MessageDisplayPanel getChatBox() {
        return messageDisplayPanel;
    }

    public void setChatBox(MessageDisplayPanel messageDisplayPanel) {
        this.messageDisplayPanel = messageDisplayPanel;
    }

    public ClientMessageService getClientMessageService() {
        return clientMessageService;
    }

    public void setClientMessageService(ClientMessageService clientMessageService) {
        this.clientMessageService = clientMessageService;
    }

    private LoginFrame loginFrame;

    private UserFrame userFrame;

    /**
     * 创建一个登陆界面
     */
    public void init() {
        loginFrame = new LoginFrame();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public UserFrame getUserFrame() {
        if (userFrame == null) {
            userFrame = new UserFrame();
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return userFrame;
    }
}
