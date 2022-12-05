package coms.w4156.moviewishlist.controllers;

import coms.w4156.moviewishlist.models.Client;
import coms.w4156.moviewishlist.services.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@GraphQlTest(GraphqlController.class)
class MutationControllerTest {
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

    Client client;

    @BeforeAll
    void createClient() {
        client = Client.builder().id(Long.valueOf("1")).email("user").build();
        Mockito
                .when(clientService.findByEmail("user"))
                .thenReturn(Optional.of(client));
    }

    // @WithMockUser
    // @Test
    // void createClientTest() {
    //     String query = """
    //             mutation createClient($email: String!){
    //               createClient(email: $email){
    //                 email
    //               }
    //             }
    //             """;

    //     graphQlTester.document(query)
    //             .variable("email", "test92@test.com")
    //             .execute()
    //             .path("createClient")
    //             .entity(Client.class)
    //             .satisfies(client -> {assertEquals("test92@test.com", client.getEmail());
    //                 // assertEquals("test90@test.com", client.getProfiles().get(0).getClient().getEmail());
    //             });
    // }

//    @Test
//    void updateClientTest() {
//        String q = """
//                mutation updateClient($id: ID!, $email: String!){
//                  updateClient(id: $id,
//                  email: $email){
//                    id,
//                    email
//                  }
//                }
//                """;
//
//        tester.document(q)
//                .variable("id", 1)
//                .variable("email","new@test.com")
//                .execute()
//                .path("updateClient")
//                .entity(Client.class)
//                .satisfies(client -> {
//                    assertEquals("new@test.com", client.getEmail());
//                });
//    }
//
//    @Test
//    void deleteClientTest() {
//        String query = """
//                mutation deleteClient($id: ID!){
//                  createClient(id: $id){
//                    id,
//                    email
//                  }
//                }
//                """;
//
//        tester.document(query)
//                .variable("id", 3)
//                .execute()
//                .path("deleteClient")
//                .entity(Client.class)
//                .satisfies(client -> {
//                    assertEquals(3, client.getId());
//                });
//    }
//
//    @Test
//    void createProfileTest() {
//        String q = """
//                 mutation createProfile($clientID: ID!,
//                  $name: String!){
//                  createProfile( clientID: $id, name: $name){
//                    id,
//                    name
//                  }
//                }
//
//                """;
//
//        tester.document(q)
//                .variable("id", 1)
//                .variable("name", "test name")
//                .execute()
//                .path("createProfile")
//                .entity(Profile.class)
//                .satisfies(profile -> {
//                    assertEquals("test name", profile.getName());
//                });
//    }
//
//    @Test
//    void updateProfileTest() {
//        String q = """
//                 mutation updateProfile($id: ID!, $name: String!){
//                  createProfile( id: $id, name: $name){
//                    id,
//                    name
//                  }
//                }
//
//                """;
//
//        tester.document(q)
//                .variable("id", 1)
//                .variable("name", "test name updated")
//                .execute()
//                .path("updateProfile")
//                .entity(Profile.class)
//                .satisfies(profile -> {
//                    assertEquals("test name updated", profile.getName());
//                });
//    }
//
//    @Test
//    void deleteProfileTest() {
//        String q = """
//                 mutation deleteProfile($id: ID!){
//                  deleteProfile( id: $id{
//                    id,
//                    name
//                  }
//                }
//
//                """;
//
//        tester.document(q)
//                .variable("id", 1)
//                .execute()
//                .path("deleteProfile")
//                .entity(Profile.class)
//                .satisfies(profile -> {
//                    assertEquals(1, profile.getId());
//                });
//    }
//
//    @Test
//    void createWishlistTest() {
//        String q = """
//                mutation createWishlist($profileID: ID!,
//                 $wishlistName: String!){
//                  createWishlist(profileID: $id,
//                  wishlistName: $name){
//                    id,
//                    name
//                  }
//                }
//                """;
//
//        tester.document(q)
//                .variable("id", 3)
//                .variable("name", "wishlist #3")
//                .execute()
//                .path("createWishlist")
//                .entity(Wishlist.class)
//                .satisfies(wishlist -> {
//                    assertEquals("wishlist #3", wishlist.getName());
//                });
//    }
//
//    @Test
//    void updateWishlistTest() {
//        String q = """
//                mutation updateWishlist($id: ID!,
//                 $name: String){
//                  updateWishlist(id: $id,
//                  name: $name){
//                    id,
//                    name
//                  }
//                }
//                """;
//
//        tester.document(q)
//                .variable("id", 3)
//                .variable("name", "updated wishlist #3")
//                .execute()
//                .path("updateWishlist")
//                .entity(Wishlist.class)
//                .satisfies(wishlist -> {
//                    assertEquals("updated wishlist #3", wishlist.getName());
//                });
//    }
//
//    @Test
//    void deleteWishlistTest() {
//        String q = """
//                mutation deleteWishlist($id: ID!){
//                  deleteWishlist(id: $id){
//                    id,
//                    name
//                  }
//                }
//                """;
//
//        tester.document(q)
//                .variable("id", 3)
//                .execute()
//                .path("deleteWishlist")
//                .entity(Wishlist.class)
//                .satisfies(wishlist -> {
//                    assertEquals(3, wishlist.getId());
//                });
//    }
//
////    @Test
////    void addMovieToWishlistTest() {
////        String q = """
////                """;
////
////        tester.document().execute().path()
////    }
//
//    @Test
//    void createRatingTest(){
//
//        String q = """
//                mutation createRating( profileId : String!,
//                movieId : String!,
//                review : String,
//                rating : Float){
//                    createRating( profileId : $profile_id,
//                    movieId : $movie_id,
//                    review : $review,
//                    rating : $rating){
//                            id,
//                            rating
//                    }
//                }
//                """;
//
//        //tester.document().execute().path()
//
//    }

//    @Test
//    void updateRatingTest(){
//        String q = """
//
//                """;
//
//        tester.document().execute().path()
//
//    }
//
//    @Test
//    void deleteRating(){
//        String q = """
//                """;
//
//        tester.document().execute().path()
//
//    }
}