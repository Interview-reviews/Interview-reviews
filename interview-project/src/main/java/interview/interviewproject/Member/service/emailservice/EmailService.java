package interview.interviewproject.Member.service.emailservice;

import interview.interviewproject.Member.domain.MemberFindPwDTO;
import interview.interviewproject.Member.domain.MemberFindUsernameDTO;

public interface EmailService {
    String memberJoin(String email)throws Exception;

    MemberFindUsernameDTO findUserName(String email)throws Exception;

    MemberFindPwDTO findPw(String email)throws Exception;
}
