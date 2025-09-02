package kr.co.securityjwt.controller;

import jakarta.validation.Valid;
import kr.co.securityjwt.dto.LoginRequestDto;
import kr.co.securityjwt.dto.MemberRequestDto;
import kr.co.securityjwt.entity.Member;
import kr.co.securityjwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @PostMapping("login")
    public ResponseEntity<String> getMemberProfile(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String token = memberService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        Member entity = modelMapper.map(memberRequestDto, Member.class);
        Long id = memberService.signup(entity);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
