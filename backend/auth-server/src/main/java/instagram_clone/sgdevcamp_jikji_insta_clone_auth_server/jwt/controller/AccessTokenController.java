package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.jwt.service.JwtService;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.User;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.dto.UserInfoDto;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "AccessToken 관련 컨트롤")
@RequestMapping("/accessToken")
@RequiredArgsConstructor
public class AccessTokenController {

    private final JwtService jwtService;
    private final UserService userService;

    @Operation(summary = "accessToken 검증", description = "accessToken 검증 API")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/validation")
    public ResponseEntity<?> validateAccessToken(
        @ApiParam(value = "accessToken") final HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        Boolean validatedAccessToken = jwtService.validatedAccessToken(accessToken);
        if (validatedAccessToken) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "유저 권한", description = "accessToken을 통해 유저 권한 전달 API")
    @ApiResponse(code = 200, message = "OK")
    @PostMapping("/get-roles")
    public ResponseEntity<?> getRoles(
        @RequestBody @ApiParam(value = "accessToken") final HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        String roles = jwtService.getRoles(accessToken);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Operation(summary = "유저 이메일", description = "accessToken을 통해 유저 이메일 전달 API")
    @ApiResponse(code = 200, message = "0K")
    @GetMapping("/get-email")
    public ResponseEntity<?> getEmails(
        @ApiParam(value = "accessToken") final HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        String email = jwtService.getEmail(accessToken);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @Operation(summary = "유저 정보", description = "accessToken을 통해 유저 정보 전달 API")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/get-user-info")
    public ResponseEntity<?> getUserInfo(
        @ApiParam(value = "accessToken") @RequestHeader(value = "Authorization") String authorization) {
        String accessToken = authorization.split(" ")[1];
        Boolean validatedAccessToken = jwtService.validatedAccessToken(accessToken);
        if (validatedAccessToken) {
            String email = jwtService.getEmail(accessToken);
            User user = userService.findByEmail(email);
            UserInfoDto userInfoDto = UserInfoDto.builder().id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profile(user.getProfile())
                .build();
            return new ResponseEntity<>(userInfoDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("NOTVALIDATEDACCESSTOKEN", HttpStatus.OK);
    }

    @Operation(summary = "유저 PK", description = "accessToken을 통해 유저 PK 전달 API")
    @ApiResponse(code = 200, message = "0K")
    @GetMapping("/get-pk")
    public ResponseEntity<?> getPK(@ApiParam(value = "accessToken") final HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        String email = jwtService.getEmail(accessToken);
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }
}
