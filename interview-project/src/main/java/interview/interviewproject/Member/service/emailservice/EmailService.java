package interview.interviewproject.Member.service.emailservice;

import interview.interviewproject.Member.domain.MemberFindPw;
import interview.interviewproject.Member.domain.MemberFindUsernameDTO;

public interface EmailService {
    String memberJoin(String email)throws Exception;

    MemberFindUsernameDTO findUserName(String email)throws Exception;

    MemberFindPw findPw(String email)throws Exception;
}
