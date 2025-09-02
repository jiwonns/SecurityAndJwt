package kr.co.securityjwt.service;

import kr.co.securityjwt.dto.CustomUserInfoDto;
import kr.co.securityjwt.dto.LoginRequestDto;
import kr.co.securityjwt.entity.Member;
import kr.co.securityjwt.repository.MemberRepository;
import kr.co.securityjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Transactional
    public String login(LoginRequestDto dto){
        String email = dto.getEmail();
        String password = dto.getPassword();
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if(member.isEmpty()){
            throw new UsernameNotFoundException("Invalid email or password");
        }

        if(!encoder.matches(password,member.get().getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);
        return jwtUtil.createAccessToken(info);

    }

    @Transactional
    public Long signup(Member member) {
        Optional<Member> validMember = memberRepository.findMemberByEmail(member.getEmail());

        if (validMember.isPresent()) {
            throw new ValidateMemberException("this member email is already exist." + member.getEmail());
        }

        member.updatePassword(encoder.encode(member.getPassword()));
        memberRepository.create(member);
        return member.getId();
    }
}
