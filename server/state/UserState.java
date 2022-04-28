package server.state;

import model.User;

import java.io.IOException;

public interface UserState {

    boolean login(User user) throws IOException;
    boolean register(User user) throws  IOException;
    void echo(String message) throws IOException;

}
