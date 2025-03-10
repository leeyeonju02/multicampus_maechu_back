package com.multipjt.multi_pjt.user.ctrl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.multipjt.multi_pjt.jwt.JwtTokenProvider;
import com.multipjt.multi_pjt.user.domain.login.ChangePwDTO;
import com.multipjt.multi_pjt.user.domain.login.EmailCertificationInputDTO;
import com.multipjt.multi_pjt.user.domain.login.EmailCertificationRequestDTO;
import com.multipjt.multi_pjt.user.domain.login.EmailRequestDTO;
import com.multipjt.multi_pjt.user.domain.login.LoginDTO;
import com.multipjt.multi_pjt.user.domain.login.NicknameRequestDTO;
import com.multipjt.multi_pjt.user.domain.login.UserRequestDTO;
import com.multipjt.multi_pjt.user.domain.login.UserResponseDTO;
import com.multipjt.multi_pjt.user.domain.login.UserUpdateRequestDTO;
import com.multipjt.multi_pjt.user.service.LoginServiceImpl;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private LoginServiceImpl loginServiceImple;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@ModelAttribute UserRequestDTO userRequestDTO,
                                               @RequestParam(value = "memberImgFile", required = false) MultipartFile memberImgFile) {
        return loginServiceImple.registerUser(userRequestDTO, memberImgFile); // 회원가입 처리
    }


    //로그인 되어있어야함 
    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(
            @ModelAttribute UserUpdateRequestDTO userUpdateRequestDTO,
            @RequestParam(value = "memberImgFile", required = false) MultipartFile memberImgFile,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            int userId = jwtTokenProvider.getUserIdFromToken(token);
            // 사용자 정보 수정 처리
            return loginServiceImple.updateUser(userId, userUpdateRequestDTO, memberImgFile);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("{\"Code\": \"UNAUTHORIZED\", \"Message\": \"인증 실패\"}");
        }
    }

    @PostMapping("/user/register/email-check")
    public ResponseEntity<String> checkEmail(@RequestBody EmailRequestDTO emailRequest) {
        String email = emailRequest.getEmail(); // DTO에서 이메일 가져오기
        boolean isValid = loginServiceImple.isValidEmail(email); // 이메일 형식 검증
        if (!isValid) { // 이메일 형식이 올바르지 않은 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("{\"Code\": \"INVALID_EMAIL\", \"Message\": \"이메일 형식이 올바르지 않습니다.\"}");
        }

        boolean exists = loginServiceImple.existsByEmail(email); // 이메일 중복 확인
        if (exists) {  // 이메일이 중복된 경우
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("{\"Code\": \"EMAIL_ALREADY_EXISTS\", \"Message\": \"이미 가입된 이메일입니다.\"}");
        } else {  // 이메일이 중복되지 않은 경우
            return ResponseEntity.ok("{\"Code\": \"SUCCESS\",\"Message\": \"사용 가능한 이메일입니다.\"}");
        }
    }

    @PostMapping("/user/register/nickname-check") 
    public ResponseEntity<String> checkNickname(@RequestBody NicknameRequestDTO nicknameRequestDTO) {
        String nickname = nicknameRequestDTO.getNickname();
        boolean exists = loginServiceImple.existsByNickname(nickname);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("{\"Code\": \"NICKNAME_ALREADY_EXISTS\", \"Message\": \"이미 존재하는 닉네임입니다.\"}");
        } else {
            return ResponseEntity.ok("{\"Code\": \"SUCCESS\", \"Message\": \"사용 가능한 닉네임입니다.\"}");
        }
    }



    @PostMapping("/user/register/email-certification")
    public ResponseEntity<String> emailCertification(@RequestBody EmailCertificationRequestDTO emailDTO) {
        String email = emailDTO.getEmail();
        try {
            boolean result = loginServiceImple.sendCertificationEmail(email);
            if (result) {
                return ResponseEntity.ok("{\"Code\": \"SUCCESS\", \"Message\": \"인증 메일이 발송되었습니다.\"}");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("{\"Code\": \"ERROR\", \"Message\": \"인증 메일 발송에 실패했습니다.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"Code\": \"ERROR\", \"Message\": \"서버 오류로 인해 인증 메일 발송에 실패했습니다.\"}");
        }
    }


    @PostMapping("/user/register/verify-certification")
    public ResponseEntity<String> verifyCertification(@RequestBody EmailCertificationInputDTO emailDTO) {
        String email = emailDTO.getEmail();
        String code = emailDTO.getCertificationCode(); // 인증 코드를 가져옴

        boolean isVerified = loginServiceImple.verifyCertificationCode(email, code); // 인증 코드 확인

        if (isVerified) {
            return ResponseEntity.ok("{\"Code\": \"SUCCESS\", \"Message\": \"인증이 완료되었습니다.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("{\"Code\": \"INVALID_CODE\", \"Message\": \"유효하지 않은 인증 코드입니다.\"}");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginDTO loginDTO) {
        // 로그인 메서드 호출
        ResponseEntity<Map<String, Object>> responseEntity = loginServiceImple.login(loginDTO);

        // 로그인 서비스에서 반환된 응답을 그대로 반환
        return responseEntity; // JSON 형식으로 응답 반환
    }


    @PatchMapping("/user/changepw")
    public ResponseEntity<String> changePw(@RequestBody ChangePwDTO changePwDTO) {
        return loginServiceImple.changePw(changePwDTO); // 서비스 메서드 호출
    }
    
    
//로그인 되어 있어야함 
      @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 접두사 제거
            jwtTokenProvider.invalidateToken(token); // 토큰 무효화
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.badRequest().body("Logout failed");
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 접두사 제거
            int userId = jwtTokenProvider.getUserIdFromToken(token); // 토큰에서 사용자 ID 추출 (int형으로 변경)

            // 사용자 탈퇴 처리
            loginServiceImple.deleteUser(userId); // userId의 타입이 int이므로 수정된 메서드에 맞게 호출

            // 토큰 무효화
            jwtTokenProvider.invalidateToken(token);

            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("탈퇴 실패");
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponseDTO> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        // Authorization 헤더에서 토큰 추출
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 접두사 제거
            int userId = jwtTokenProvider.getUserIdFromToken(token); // 토큰에서 사용자 ID 추출

            // 사용자 정보 조회
            UserResponseDTO userInfo = loginServiceImple.getUserInfo(userId);
            if (userInfo != null) {
                return ResponseEntity.ok(userInfo); // 사용자 정보 반환
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(null); // 사용자 정보가 없을 경우 404 반환
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 인증 실패
        }
    }
}




