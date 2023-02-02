package interview.interviewproject.Member;

import com.fasterxml.jackson.core.JsonProcessingException;
import interview.interviewproject.Member.domain.CareerType;
import interview.interviewproject.Member.domain.GenderType;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class MemberControllerTest extends BaseControllerTest {

    private static final Snippet CHECKNICKNAME_REQUEST_FIELDS = requestFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임")
    );

    private static final Snippet CHECKNICKNAME_RESPONSE_FIELDS = responseFields(
            fieldWithPath("flag").type(JsonFieldType.BOOLEAN).description("중복여부")
    );

    @Test
    @DisplayName("닉네임 중복확인")
    void checkNickname() throws JsonProcessingException {
        String nickname = "정유석";
        LocalDate now = LocalDate.now();
        Member member = new Member("닭강정", "유석123", "010-5387-4816", "dbtjrz12#",
                "elwlahstmxjf@naver.com", now, GenderType.MAN, "테스트");

        MemberDetail memberDetail = new MemberDetail("4년제", "서경대", 4.2, "컴공",
                1, "백엔드", CareerType.NEWCOMER);



        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CHECKNICKNAME_REQUEST_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .body(nickname)
                .log().all()

                .when()
                .post("/api/v1/member/check-nickname")


                .then()
                .statusCode(HttpStatus.OK.value());

    }

}
