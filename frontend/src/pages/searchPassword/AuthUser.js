import React, {useState} from "react";
import {authUser} from "../../components/action/authUser";
import {useNavigate} from "react-router-dom";
import "../../assets/css/login.css"
import "../../assets/css/signup.css"
import "../../assets/css/main.css"
import lock from "../../assets/img/lock.png"

function AuthUser(){
    const [Email, setEmail] = useState("");
    const [Nickname, setNickname] = useState("");
    const [Phone, setPhone] = useState("");
    const [EmailError, setEmailError] = useState("");
    const [NicknameError, setNicknameError] = useState("");
    const [PhoneError, setPhoneError] = useState("");

    const navigate = useNavigate();
    const onEmailHandler=(event)=>{
        setEmail(event.currentTarget.value);
    }

    const onNicknameHandler=(event)=>{
        setNickname(event.currentTarget.value);
    }

    const onPhoneHandler=(event)=>{
        setPhone(event.currentTarget.value);
    }

    const onSubmitHandler =(event)=>{
        event.preventDefault();

        let body = {
            email:Email,
            nickname:Nickname,
            phone:Phone
        }

        authUser(body,setEmailError,setNicknameError,setPhoneError,navigate);
    }
    return (
        <div className={"contentsWrap"}>
            <form onSubmit={onSubmitHandler}>
                <div className={"loginWindow"}>
                    <img src={lock} alt={""} className={"lock"}/>
                    <h3>로그인에 문제가 있나요?</h3>
                    <h6 className="font_size">이메일 주소, 전화번호, 사용자 이름을 입력하시면 가입하신 이메일에 임시 비밀번호를 보내드립니다.</h6>
                    <input id={"email"} type={"email"} value={Email} onChange={onEmailHandler} placeholder={"이메일"} className={"inlineToBlock"}/>
                    <span className={"field-error"}>{EmailError}</span>
                    <br/>
                    <input id={"nickname"} type={"text"} value={Nickname} onChange={onNicknameHandler} placeholder={"닉네임"} className={"inlineToBlock"}/>
                    <span className={"field-error"}>{NicknameError}</span>
                    <br/>
                    <input id={"phone"} type={"text"} value={Phone} onChange={onPhoneHandler} placeholder={"전화번호"} className={"inlineToBlock"}/>
                    <span className={"field-error"}>{PhoneError}</span>
                    <button className="inlineToBlock ordinaryLogin activatedLoginColor" formAction={""}>비밀번호 찾기</button>
                </div>
            </form>
        </div>
    )
}

export default AuthUser;