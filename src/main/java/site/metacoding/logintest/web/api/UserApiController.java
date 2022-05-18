package site.metacoding.logintest.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.logintest.domain.User;
import site.metacoding.logintest.service.UserService;
import site.metacoding.logintest.util.RespScript;
import site.metacoding.logintest.util.Script;
import site.metacoding.logintest.web.dto.ResponseDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

        private final UserService userService;
        private final HttpSession session;
        private final HttpServletResponse response;
    
    // 회원 탈퇴
    @DeleteMapping("/s/api/user/{id}/delete")
    public @ResponseBody ResponseDto<?> userDelete(@PathVariable Integer id) {
        // 권한체크하기 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");

        // 권한이 없으면
        if (principal.getId() != id) {
            String scriptMsg = Script.back("회원탈퇴할 권한이 없습니다.");
            RespScript.스크립트로응답하기(scriptMsg, response);
        }

        userService.회원탈퇴(id);
        session.invalidate();
        return new ResponseDto<>(1, "성공", null);
    }

}
