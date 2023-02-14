import {useState} from "react";
import {SignupUser} from "../action/SignupUser";
import {Link, useHistory} from "react-router-dom";
import "../css/login.css";
import "../css/signup.css";
import "../css/main.css";
import logo from "../img/instagramLogo.png";
function Signup() {
    const [Email, setEmail] = useState("");
    const [Password, setPassword] = useState("");
    const [Name, setName] = useState("");
    const [Nickname, setNickname] = useState("");
    const [Phone, setPhone] = useState("");
    const [EmailError, setEmailError] = useState("");
    const [PasswordError,setPasswordError] = useState("");
    const [NicknameError,setNicknameError] = useState("");
    const [PhoneError, setPhoneError] = useState("");
    const history = useHistory();
    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    }
    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }

    const onNameHandler = (event) => {
        setName(event.currentTarget.value);
    }

    const onNicknameHandler = (event) => {
        setNickname(event.currentTarget.value);
    }

    const onPhoneHandler = (event) => {
        setPhone(event.currentTarget.value);
    }

    const onSubmitHandler = (event) => {
        // 버튼만 누르면 리로드 되는것을 막아줌
        event.preventDefault();

        let body = {
            email: Email,
            password: Password,
            name: Name,
            nickname: Nickname,
            phone: Phone
        }

        SignupUser(body, setEmailError, setPasswordError, setNicknameError,setPhoneError,history);

    }

    return (
        <div className={"contentsWrap"}>
            <form onSubmit={onSubmitHandler}>
                <div className={"loginWindow"}>
                    <img src={logo} alt=""/>
                    <h2 className="font_color">친구들의 사진과 동영상을 보려면 가입하세요.</h2>
                    <input type="email" value={Email} onChange={onEmailHandler} placeholder={"이메일 주소"} className="inlineToBlock"/>
                    <span className={"field-error"}>{EmailError}</span>
                    <br/>
                    <input type="password" value={Password} onChange={onPasswordHandler} placeholder={"비밀번호"} className="inlineToBlock"/>
                    <span className={"field-error"}>{PasswordError}</span>
                    <br/>
                    <input type="name" value={Name} onChange={onNameHandler} placeholder={"성명"} className="inlineToBlock"/>
                    <br/>
                    <input type="nickname" value={Nickname} onChange={onNicknameHandler} placeholder={"사용자 이름"} className="inlineToBlock"/>
                    <span className={"field-error"}>{NicknameError}</span>
                    <br/>
                    <input type="phone" value={Phone} onChange={onPhoneHandler} placeholder={"휴대폰 번호"} className="inlineToBlock"/>
                    <span className={"field-error"}>{PhoneError}</span>
                    <br/>
                    <button formAction="" className="inlineToBlock ordinaryLogin activatedLoginColor">
                        가입
                    </button>
                </div>
            </form>
            <Link to="/" >
                <div className={"haveAccount"}>
                    <p>계정이 이미 있으신가요?
                        <a className="noneunderline">로그인</a>
                    </p>
                </div>
            </Link>
        </div>
    )
}

export default Signup;