package interview.interviewproject.Community;

import com.epages.restdocs.apispec.ResourceSnippetDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import interview.interviewproject.Community.domain.*;
import interview.interviewproject.Community.repository.CommunityCommentRepository;
import interview.interviewproject.Community.repository.CommunityLikeRepository;
import interview.interviewproject.Community.repository.CommunityRepository;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.repository.MemberRepository;
import interview.interviewproject.common.BaseControllerTest;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class CommunityControllerTest extends BaseControllerTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommunityLikeRepository communityLikeRepository;

    @Autowired
    private CommunityCommentRepository communityCommentRepository;

    @BeforeEach
    void beforeClean(){
        communityCommentRepository.deleteAll();
        communityLikeRepository.deleteAll();
        communityRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @AfterEach
    void afterClean(){
        communityCommentRepository.deleteAll();
        communityLikeRepository.deleteAll();
        communityRepository.deleteAll();
        memberRepository.deleteAll();
    }

    private static final Snippet REGISTER_REQUEST_FIELDS = requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("??????"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("??????"),
            fieldWithPath("category").type(JsonFieldType.STRING).description("????????????"),
            fieldWithPath("communityTagList").type(JsonFieldType.ARRAY).description("?????? ??????"),
            fieldWithPath("communityTagList[].tagName").type(JsonFieldType.STRING).description("?????? ??????")
    );

    private static final Snippet COMMUNITY_LIST_RESPONSE_FIELDS = responseFields(
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("???????????? id"),
            fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("????????? ?????????"),
            fieldWithPath("[].title").type(JsonFieldType.STRING).description("???????????? ??????"),
            fieldWithPath("[].contents").type(JsonFieldType.STRING).description("???????????? ??????"),
            fieldWithPath("[].category").type(JsonFieldType.STRING).description("????????????"),
            fieldWithPath("[].views").type(JsonFieldType.NUMBER).description("?????????"),
            fieldWithPath("[].likes").type(JsonFieldType.NUMBER).description("????????? ???"),
            fieldWithPath("[].liked").type(JsonFieldType.BOOLEAN).description("????????? ???"),
            fieldWithPath("[].comments").type(JsonFieldType.NUMBER).description("?????? ???"),
            fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("???????????? ?????? ??????"),
            fieldWithPath("[].careerType").type(JsonFieldType.NULL).description("???????????? ????????? ?????? ??????"),
            fieldWithPath("[].owner").type(JsonFieldType.BOOLEAN).description("???????????? ??? ?????? ??????"),
            fieldWithPath("[].communityTagList").type(JsonFieldType.ARRAY).description("?????? ??????"),
            fieldWithPath("[].communityTagList[].tagName").type(JsonFieldType.STRING).description("?????? ??????"),
            fieldWithPath("[].communityCommentList").type(JsonFieldType.ARRAY).description("?????? ??????"),
            fieldWithPath("[].communityCommentList[].nickname").type(JsonFieldType.STRING).description("?????? ?????? ????????? ?????????"),
            fieldWithPath("[].communityCommentList[].createAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
            fieldWithPath("[].communityCommentList[].content").type(JsonFieldType.STRING).description("?????? ??????"),
            fieldWithPath("[].communityCommentList[].owner").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????")

    );

    private static final Snippet COMMUNITY_RESPONSE_FIELDS = responseFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("???????????? id"),
            fieldWithPath("nickName").type(JsonFieldType.STRING).description("????????? ?????????"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("???????????? ??????"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("???????????? ??????"),
            fieldWithPath("category").type(JsonFieldType.STRING).description("????????????"),
            fieldWithPath("views").type(JsonFieldType.NUMBER).description("?????????"),
            fieldWithPath("likes").type(JsonFieldType.NUMBER).description("????????? ???"),
            fieldWithPath("liked").type(JsonFieldType.BOOLEAN).description("????????? ???"),
            fieldWithPath("comments").type(JsonFieldType.NUMBER).description("?????? ???"),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("???????????? ?????? ??????"),
            fieldWithPath("careerType").type(JsonFieldType.NULL).description("???????????? ????????? ?????? ??????"),
            fieldWithPath("owner").type(JsonFieldType.BOOLEAN).description("???????????? ??? ?????? ??????"),
            fieldWithPath("communityTagList").type(JsonFieldType.ARRAY).description("?????? ??????"),
            fieldWithPath("communityTagList[].tagName").type(JsonFieldType.STRING).description("?????? ??????"),
            fieldWithPath("communityCommentList").type(JsonFieldType.ARRAY).description("?????? ??????"),
            fieldWithPath("communityCommentList[].nickname").type(JsonFieldType.STRING).description("?????? ?????? ????????? ?????????"),
            fieldWithPath("communityCommentList[].createAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
            fieldWithPath("communityCommentList[].content").type(JsonFieldType.STRING).description("?????? ??????"),
            fieldWithPath("communityCommentList[].owner").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????")

    );

    @Test
    @DisplayName("???????????? ??? ?????? api")
    void registerCommunity() throws JsonProcessingException {

        List<CommunityTagDTO> communityTagList = new ArrayList<>();
        communityTagList.add(CommunityTagDTO.builder().tagName("????????? ??????1").build());
        communityTagList.add(CommunityTagDTO.builder().tagName("????????? ??????2").build());


        CommunityDTO.Request request = CommunityDTO.Request.builder()
                .title("????????? ??????")
                .contents("????????? ??????")
                .category(CommunityType.QnA)
                .communityTagList(communityTagList)
                .build();

        String jwtTokenResponse = getJwtTokenResponse();


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, REGISTER_REQUEST_FIELDS)) // API ?????? ?????? ?????? ??????
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .body(request)
                .log().all()

                .when()
                .post("/api/v1/community")


                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("???????????? ?????? ???????????? api")
    void getCommunityList() throws JsonProcessingException {

        Member member = getMember();

        Community community = getCommunity(member);

        createCommunityComment(member , community);

        String jwtTokenResponse = getJwtTokenResponse();


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, COMMUNITY_LIST_RESPONSE_FIELDS)) // API ?????? ?????? ?????? ??????
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .log().all()

                .when()
                .get("/api/v1/community")


                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("???????????? ??? ?????? api")
    void getCommunity() throws JsonProcessingException {

        Member member = getMember();

        Community community = getCommunity(member);

        createCommunityComment(member , community);

        String jwtTokenResponse = getJwtTokenResponse();


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, COMMUNITY_RESPONSE_FIELDS)) // API ?????? ?????? ?????? ??????
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .log().all()

                .when()
                .get("/api/v1/community/{communityId}" , community.getId())


                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("???????????? ????????? ???????????? api")
    void createLike() throws JsonProcessingException {

        Member member = getMember();

        Community community = getCommunity(member);

        String jwtTokenResponse = getJwtTokenResponse();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH)) // API ?????? ?????? ?????? ??????
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .log().all()

                .when()
                .post("/api/v1/community/like/{communityId}", community.getId())


                .then()
                .statusCode(HttpStatus.OK.value());

        Optional<Community> communityOptional = communityRepository.findById(community.getId());

        Assertions.assertThat(communityOptional.get().isLiked()).isTrue();


    }

    @Test
    @DisplayName("???????????? ?????? ???????????? api")
    void createComment() throws JsonProcessingException {

        Member member = getMember();

        Community community = getCommunity(member);

        String jwtTokenResponse = getJwtTokenResponse();

        String content = "????????? ??????";

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH)) // API ?????? ?????? ?????? ??????
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .body(content)
                .log().all()

                .when()
                .post("/api/v1/community/comment/{communityId}", community.getId())


                .then()
                .statusCode(HttpStatus.OK.value());

        List<CommunityComment> communityCommentList = communityCommentRepository.findAll();

        Assertions.assertThat(communityCommentList.size()).isEqualTo(1);

    }

    private Community getCommunity(Member member) {
        List<CommunityTagDTO> communityTagList = new ArrayList<>();
        communityTagList.add(CommunityTagDTO.builder().tagName("????????? ??????1").build());
        communityTagList.add(CommunityTagDTO.builder().tagName("????????? ??????2").build());

        CommunityDTO.Request request = CommunityDTO.Request.builder()
                .title("????????? ??????")
                .contents("????????? ??????")
                .category(CommunityType.QnA)
                .communityTagList(communityTagList)
                .build();

        Community community = Community.createCommunity(request, member);

        communityRepository.save(community);

        return community;
    }

    private void createCommunityComment(Member member , Community community) {

        CommunityComment comment = CommunityComment.builder()
                .contents("????????? ??????")
                .nickName(member.getNickname())
                .community(community)
                .build();

        communityCommentRepository.save(comment);
    }

    @NotNull
    private Member getMember() {
        Member member = Member.builder().nickname("????????? ?????????")
                .username("testID")
                .password("1234")
                .build();

        memberRepository.save(member);
        return member;
    }
}
