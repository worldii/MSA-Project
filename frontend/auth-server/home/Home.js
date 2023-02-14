import React, {useState} from "react";
import {loginUser} from "../action/loginUser";
import {Link, useHistory} from "react-router-dom";
import "./Home.css"
import "./login.css"
import logo from '../img/instagramLogo.png';

function Home() {

    const [Email, setEmail] = useState("");
    const [Password, setPassword] = useState("");
    const [EmailError, setEmailError] = useState("");
    const [PasswordError, setPasswordError] = useState("");

    const history = useHistory();
    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    }
    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }
    const onSubmitHandler = (event) => {
        // 버튼만 누르면 리로드 되는것을 막아줌
        event.preventDefault();

        console.log('Email', Email);
        console.log('Password', Password);

        let body = {
            email: Email,
            password: Password,
        }
        loginUser(body, setEmailError, setPasswordError, history);
    }
    return (
        <div className={"contentsWrap"}>
            <form onSubmit={onSubmitHandler}>
                <div className={"loginWindow"}>
                    <img src={logo} alt={React}/>
                    <input type="email" value={Email} onChange={onEmailHandler} placeholder={"이메일"}
                           className={"inlineToBlock"}/>
                    <span className="field-error">{EmailError}</span>
                    <br/>
                    <input type="password" value={Password} onChange={onPasswordHandler} placeholder={"비밀번호"}
                           className={"inlineToBlock"}/>
                    <span className="field-error">{PasswordError}</span>
                    <br/>
                    <button formAction="" className="inlineToBlock ordinaryLogin activatedLoginColor">
                        로그인
                    </button>
                    <Link to={"/searchPassword"}>
                        <a className="button_signup">비밀번호를 잊으셨나요?</a>
                    </Link>
                </div>
            </form>
            <Link to="/signup">
                <div className="haveAccount">
                    <p>계정이 없으신가요?
                        <a className="noneunderline">가입하기</a>
                    </p>
                </div>
            </Link>
        </div>
    )
}

export default Home;