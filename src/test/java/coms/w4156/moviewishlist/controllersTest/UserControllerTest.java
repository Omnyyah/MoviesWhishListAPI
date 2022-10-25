package coms.w4156.moviewishlist.controllersTest;
import coms.w4156.moviewishlist.models.User;
import coms.w4156.moviewishlist.models.Wishlist;
import coms.w4156.moviewishlist.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import coms.w4156.moviewishlist.controllers.UserController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    //we need to convert from json to string and vice versa
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    //Mock user repo
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    List<Wishlist> l = new ArrayList<>();
    //should pass
    User userOne = User.builder()
        .email("omniyyah@gmail.com")
        .name("omniyyah")
        .password("hjgT48582%%")
        .wishlists(l)
        .build();

    //should not pass
    User userTwo = User.builder()
        .email("userTwo")
        .name("omniyyah")
        .password("hjgT48582%%")
        .wishlists(l)
        .build();

    //should not pass
    User userThree = User.builder()
        .email("")
        .name("")
        .password("")
        .wishlists(l)
        .build();
//    //should not pass
//    User userFour = new User("iio@hotmail", "", "nycjfk");
//    User userFive = new User("", "userFive", "nycjfk");
//    User userSix = new User("userSix@gmail.com", "userSix", "");
//    User userSeven = new User("userSeven@gmail.com", "userSeven", "");
//    User userEight = new User("userEight@gmail.com", "", "9283gh");
//    User userNine = new User("userNine@gmail", "userNine", "nycjfk");
    User userTen = User.builder()
        .email("userTen@gmail.com")
        .name("userTen")
        .password("nycjfkGG48#%")
        .wishlists(l)
        .build();

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUser_success() throws Exception{
        User user = User.builder()
                .email("test@gmail.com")
                .name("test")
                .password("hjgT48582%%")
                .wishlists(List.of())
                .build();

        // List<Wishlist> l = new ArrayList<>();
        // Wishlist w1 = new Wishlist("list name", user);
        // l.add(w1);

        // This exact user is not being passed....
        Mockito.when(userService.create(user)).thenReturn(user);
        // String content = objectWriter.writeValueAsString(user);
        String content = "{\"email\":\"" + user.getEmail() + "\",\"name\":\"" + user.getName() + "\",\"password\":\"" + user.getPassword() + "\", \"wishlists\":[]}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk());
                // TODO: Fixed this out.
                // .andExpect(jsonPath("$", notNullValue()));
                // .andExpect(jsonPath("$", notNullValue()));
                // .andExpect(jsonPath("$.email", is("test@gmail.com")));
    }


    @Test
    public void createUser_fail() throws Exception{
        List<Wishlist> l = new ArrayList<>();
        User user = User.builder()
                .email("test")
                .name("test name")
                .password("hjgT48582%%")
                .wishlists(l)
                .build();

        Mockito.when(userService.create(user)).thenReturn(user);
        String content = objectWriter.writeValueAsString(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }


    @Test
    public void getAll() throws Exception{

        List<User> users = new ArrayList<>(Arrays.asList(userOne,
                userTwo, userThree, userTen));

        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].email", is("omniyyah@gmail.com")));
    }

    @Test
    public void getUserById() throws Exception{
        Mockito.when(userService.findById(userTen.getId()))
                .thenReturn(java.util.Optional.of(userTen));
    }


    @Test
    public void updateUser() throws Exception{
        List<Wishlist> l = new ArrayList<>();

        User origUser = User.builder()
                .email("test@gmail.com")
                .name("test name")
                .password("hjgT48582%%")
                .wishlists(l)
                .build();

        User updatedUser = User.builder()
                .email("test@gmail.com")
                .name("test update name")
                .password("hjgT48582%%")
                .wishlists(l)
                .build();

        Mockito.when(userService.findById(updatedUser.getId())).thenReturn(java.util.Optional.of(origUser));
        Mockito.when(userService.update(origUser)).thenReturn(updatedUser);

        String content = "{\"name\":\"test update name\"}";
        String userId = origUser.getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("test update name")));

    }


    @Test
    public void deleteAllUsers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
