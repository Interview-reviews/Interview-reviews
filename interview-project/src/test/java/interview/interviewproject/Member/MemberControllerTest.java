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
import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static java.util.Map.entry;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class MemberControllerTest extends BaseControllerTest {

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberDetailRepository detailRepository;
//
//    @Autowired
//    MemberLanguageRepository languageRepository;
//
//    @BeforeEach
//    void beforeClean() {
//        memberRepository.deleteAll();
//        detailRepository.deleteAll();
//        languageRepository.deleteAll();
//    }
//
//    @AfterEach
//    void afterClean() {
//        memberRepository.deleteAll();
//        detailRepository.deleteAll();
//        languageRepository.deleteAll();
//    }
//
//    private static final Snippet JOIN_REQUEST_FIELDS = requestFields(
//            fieldWithPath("nickname").type(JsonFieldType.STRING).description("?????????"),
//            fieldWithPath("username").type(JsonFieldType.STRING).description("?????????"),
//            fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("????????????"),
//            fieldWithPath("password").type(JsonFieldType.STRING).description("????????????"),
//            fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
//            fieldWithPath("birthDate").type(JsonFieldType.ARRAY).description("??????"),
//            fieldWithPath("gender").type(JsonFieldType.STRING).description("??????"),
//            fieldWithPath("bcryptPasswordEncoder").type(JsonFieldType.NULL).description("???????????? ?????????")
//    );
//
//    @Test
//    @DisplayName("????????????-1")
//    void joinMember() throws JsonProcessingException {
//
//        String jwtTokenResponse = getJwtTokenResponse();
//
//        MemberDTO.Request request = MemberDTO.Request.builder()
//                .nickname("?????????")
//                .username("?????????1234")
//                .phoneNumber("010-5387-4816")
//                .password("dbt12#")
//                .email("elwlahstmxjf@naver.com")
//                .birthDate(LocalDate.ofEpochDay(2000-2-15))
//                .gender(GenderType.valueOf("MAN"))
//                .build();
//
//        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, JOIN_REQUEST_FIELDS))
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .header("Content-type", "application/json")
//                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
//                .body(request)
//                .log().all()
//
//                .when()
//                .post("/api/v1/member/join")
//
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }
//
//    private static final Snippet DetailJoin_REQUEST_FIELDS = requestFields(
//            fieldWithPath("graduate").type(JsonFieldType.STRING).description("????????????"),
//            fieldWithPath("school").type(JsonFieldType.STRING).description("?????????"),
//            fieldWithPath("grades").type(JsonFieldType.NUMBER).description("??????"),
//            fieldWithPath("major").type(JsonFieldType.STRING).description("??????"),
//            fieldWithPath("intern").type(JsonFieldType.NUMBER).description("????????????"),
//            fieldWithPath("job").type(JsonFieldType.STRING).description("??????"),
//            fieldWithPath("careerType").type(JsonFieldType.STRING).description("????????????"),
//            fieldWithPath("language[].language").type(JsonFieldType.STRING).description("??????"),
//            fieldWithPath("language[].languageScore").type(JsonFieldType.NUMBER).description("????????????")
//    );

//    @Test
//    @DisplayName("????????????-2")
//    void joinMemberDetail() throws JsonProcessingException {
//
//        String jwtTokenResponse = getJwtTokenResponse();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        MemberLanguageDTO.Request req = new MemberLanguageDTO.Request("toeic", 990L);
//
//        List<MemberLanguageDTO.Request> list = new ArrayList<>();
//        list.add(req);
//
//        MemberDTO.Request memberDTO = MemberDTO.Request.builder()
//                .bCryptPasswordEncoder(bCryptPasswordEncoder)
//                .nickname("?????????")
//                .username("?????????1234")
//                .phoneNumber("010-5387-4816")
//                .password("dbt12#")
//                .email("elwlahstmxjf@naver.com")
//                .birthDate(LocalDate.ofEpochDay(2000-2-15))
//                .gender(GenderType.valueOf("MAN"))
//                .build();
//
//        Member member = Member.createMember(memberDTO);
//        memberRepository.save(member);
//
//        MemberDetailDTO.Request request = MemberDetailDTO.Request.builder()
//                .graduate("4?????? ??????")
//                .school("???????????????")
//                .grades(3.8)
//                .major("??????????????????")
//                .intern(2)
//                .job("??? ?????????")
//                .careerType(CareerType.valueOf("NEWCOMER"))
//                .language(list)
//                .member(member)
//                .build();
//
//        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, DetailJoin_REQUEST_FIELDS))
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .header("Content-type", "application/json")
//                .header(AUTHORIZATION, "Bearer " + jwtTokenResponse)
//                .body(request)
//                .log().all()
//
//                .when()
//                .post("/api/v1/member/detail-join")
//
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }




}
