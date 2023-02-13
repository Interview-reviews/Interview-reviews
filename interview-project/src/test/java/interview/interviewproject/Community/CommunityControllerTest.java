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

import java.util.ArrayList;
import java.util.List;

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
        communityRepository.deleteAll();
        memberRepository.deleteAll();
        communityLikeRepository.deleteAll();
        communityCommentRepository.deleteAll();
    }

    @AfterEach
    void afterClean(){
        communityRepository.deleteAll();
        memberRepository.deleteAll();
        communityLikeRepository.deleteAll();
        communityCommentRepository.deleteAll();
    }

    private static final Snippet REGISTER_REQUEST_FIELDS = requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("내용"),
            fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
            fieldWithPath("communityTagList").type(JsonFieldType.ARRAY).description("태그 목록"),
            fieldWithPath("communityTagList[].tagName").type(JsonFieldType.STRING).description("태그 이름")
    );

    private static final Snippet COMMUNITY_LIST_RESPONSE_FIELDS = responseFields(
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("커뮤니티 id"),
            fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("사용자 닉네임"),
            fieldWithPath("[].title").type(JsonFieldType.STRING).description("커뮤니티 제목"),
            fieldWithPath("[].contents").type(JsonFieldType.STRING).description("커뮤니티 내용"),
            fieldWithPath("[].category").type(JsonFieldType.STRING).description("카테고리"),
            fieldWithPath("[].views").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
            fieldWithPath("[].comments").type(JsonFieldType.NUMBER).description("댓글 수"),
            fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("커뮤니티 생성 날짜"),
            fieldWithPath("[].communityTagList").type(JsonFieldType.ARRAY).description("태그 목록"),
            fieldWithPath("[].communityTagList[].tagName").type(JsonFieldType.STRING).description("태그 이름")

    );

    @Test
    @DisplayName("커뮤니티 글 작성 api")
    void registerCommunity() throws JsonProcessingException {

        List<CommunityTagDTO> communityTagList = new ArrayList<>();
        communityTagList.add(CommunityTagDTO.builder().tagName("테스트 태그1").build());
        communityTagList.add(CommunityTagDTO.builder().tagName("테스트 태그2").build());

        CommunityDTO.Request request = CommunityDTO.Request.builder()
                .title("테스트 제목")
                .contents("테스트 내용")
                .category(CommunityType.QnA)
                .communityTagList(communityTagList)
                .build();

        String jwtTokenResponse = getJwtTokenResponse();


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, REGISTER_REQUEST_FIELDS)) // API 문서 관련 필터 추가
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
    @DisplayName("커뮤니티 목록 가져오기 api")
    void getCommunityList() throws JsonProcessingException {

        Member member = getMember();

        Community community = getCommunity(member);

        String jwtTokenResponse = getJwtTokenResponse();


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, COMMUNITY_LIST_RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .log().all()

                .when()
                .get("/api/v1/community")


                .then()
                .statusCode(HttpStatus.OK.value());
    }

    private Community getCommunity(Member member) {
        List<CommunityTagDTO> communityTagList = new ArrayList<>();
        communityTagList.add(CommunityTagDTO.builder().tagName("테스트 태그1").build());
        communityTagList.add(CommunityTagDTO.builder().tagName("테스트 태그2").build());

        CommunityDTO.Request request = CommunityDTO.Request.builder()
                .title("테스트 제목")
                .contents("테스트 내용")
                .category(CommunityType.QnA)
                .communityTagList(communityTagList)
                .build();

        Community community = Community.createCommunity(request, member);

        communityRepository.save(community);

        return community;
    }

    @NotNull
    private Member getMember() {
        Member member = Member.builder().nickname("화서동 개발자")
                .username("testID")
                .password("1234")
                .build();

        memberRepository.save(member);
        return member;
    }
}
