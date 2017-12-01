package services.impl;

import dao.DataDao;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import services.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Mock
    private DataDao dataDao;

    @Before
    public void setValue() {
        when(dataDao.getUserFromDB(anyString())).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocationOnMock) {
                User user = new User("User","User","password");
                String name = invocationOnMock.getArgument(0);
                return user;
            }
        });
    }

    @Test
    public void update() {
        boolean t = true;
        User user = new User("User","User","password");
        assertEquals(t, userService.update(user));
    }

    @Test
    public void getUser() {
        User user = new User("User","User","password");
        assertEquals(user, userService.getUser("User"));
    }

}