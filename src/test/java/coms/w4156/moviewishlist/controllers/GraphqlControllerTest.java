package coms.w4156.moviewishlist.controllers;

import coms.w4156.moviewishlist.models.Client;
import coms.w4156.moviewishlist.models.Profile;
import coms.w4156.moviewishlist.repositories.ClientRepository;
import coms.w4156.moviewishlist.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;


//@TestInstance(Lifecycle.PER_CLASS)
@GraphQlTest(GraphqlController.class)
class GraphqlControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean
    ClientService clientService;

    @MockBean
    ProfileService profileService;

    @MockBean
    WishlistService wishlistService;

    @MockBean
    WatchModeService watchModeService;

    @MockBean
    MovieService movieService;

    @MockBean
    RatingService ratingService;

//    Client client;

//    @BeforeAll
//    void setUp() {
//      client = Client.builder().id(Long.valueOf("1")).email("user").build();
//      Mockito
//          .when(clientService.findByEmail("client"))
//          .thenReturn(Optional.of(client));
//    }
//    @WithMockUser
    @Test
    void clientsTest() {
        String query = """
                query{
                    clients{
                        id
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("clients")
                .entityList(Client.class)
                .hasSize(0);
    }

    @Test
    void clientByIdTest() {
        String query = """
                query clientById($id: ID){
                  clientById(id: $id){
                    email,
                    profiles{
                      id,
                      wishlists{
                        id,
                        movies{
                          movieName,
                          movieRuntime
                        }
                      }
                    }
                  }
                }
                """;
        graphQlTester.document(query)
                .variable("id", 1)
                .execute()
                .path("clientById")
                .entity(Client.class)
                .satisfies(client -> {
                    assertEquals("testGrahphQL2@test.com",client.getEmail());
                    assertEquals("1", client.getId());
                });
    }

    @WithMockUser
    @Test
    void profilesTest() {
        String query = """
                query {
                  profiles{
                    id
                  }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("profiles")
                .entityList(Profile.class)
                .satisfies(profiles -> {
                    assertEquals("2", profiles.get(0).getId());
                });
    }

    @Test
    void profileByUDTest(){

    }


    @Test
    void moviesTest(){

    }

    @Test
    void moviesByGenreTest(){

    }

    @Test
    void moviesByReleaseYearTest() {

    }

    @Test
    void moviesByRuntimeTest() {
    }

    @Test
    void moviesByCriticScoreTest() {
    }

    @Test
    void searchTitlesTest() {
    }

    @Test
    void networksTest() {
    }

    @Test
    void titleDetailTest() {
    }
}