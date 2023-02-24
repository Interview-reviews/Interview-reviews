package interview.interviewproject.Member;

import com.fasterxml.jackson.core.JsonProcessingException;
import interview.interviewproject.Member.domain.*;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberLanguageRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import interview.interviewproject.common.BaseControllerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.spec.EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class MemberControllerTest extends BaseControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberDetailRepository detailRepository;

    @Autowired
    MemberLanguageRepository languageRepository;

    @BeforeEach
    void beforeClean() {
        memberRepository.deleteAll();
        detailRepository.deleteAll();
        languageRepository.deleteAll();
    }

    @AfterEach
    void afterClean() {
        memberRepository.deleteAll();
        detailRepository.deleteAll();
        languageRepository.deleteAll();
    }

    private static final Snippet JOIN_REQUEST_FIELDS = requestFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
            fieldWithPath("username").type(JsonFieldType.STRING).description("아이디"),
            fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("전화번호"),
            fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
            fieldWithPath("birthDate").type(JsonFieldType.ARRAY).description("생일"),
            fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
            fieldWithPath("bcryptPasswordEncoder").type(JsonFieldType.NULL).description("비밀번호 인코더")
    );

    @Test
    @DisplayName("회원가입-1")
    void joinMember() throws JsonProcessingException {

        String jwtTokenResponse = getJwtTokenResponse();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        MemberDTO.Request request = MemberDTO.Request.builder()
//                .bCryptPasswordEncoder(bCryptPasswordEncoder)
                .nickname("정유석")
                .username("정유석1234")
                .phoneNumber("010-5387-4816")
                .password("dbt12#")
                .email("elwlahstmxjf@naver.com")
                .birthDate(LocalDate.ofEpochDay(2000-2-15))
                .gender(GenderType.valueOf("MAN"))
                .build();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, JOIN_REQUEST_FIELDS))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
                .body(request)
                .log().all()

                .when()
                .post("/api/v1/member/join")

                .then()
                .statusCode(HttpStatus.OK.value());
    }



}
