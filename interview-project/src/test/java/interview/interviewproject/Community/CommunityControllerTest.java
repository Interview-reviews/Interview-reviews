package interview.interviewproject.Community;

import com.fasterxml.jackson.core.JsonProcessingException;
import interview.interviewproject.Community.domain.CommunityDTO;
import interview.interviewproject.Community.domain.CommunityTag;
import interview.interviewproject.Community.domain.CommunityType;
import interview.interviewproject.common.BaseControllerTest;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    private static final Snippet REGISTER_REQUEST_FIELDS = requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("내용"),
            fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
            fieldWithPath("communityTagList").type(JsonFieldType.ARRAY).description("태그 목록"),
            fieldWithPath("communityTagList[].id").type(JsonFieldType.NULL).description("태그 id"),
            fieldWithPath("communityTagList[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
            fieldWithPath("communityTagList[].community").type(JsonFieldType.NULL).description("태그가 속한 커뮤니티")

    );

    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
            fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
    );

    @Test
    @DisplayName("커뮤니티 글 작성 api")
    void registerCommunity() throws JsonProcessingException {

        List<CommunityTag> communityTagList = new ArrayList<>();
        communityTagList.add(CommunityTag.builder().tagName("테스트 태그").build());
        communityTagList.add(CommunityTag.builder().tagName("테스트 태그2").build());


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
}
