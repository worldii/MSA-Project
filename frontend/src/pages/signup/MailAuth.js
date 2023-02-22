import {useState,useEffect} from "react";
import {MailAuthUser} from "../../components/action/mailAuthUser";
import axios from "axios";
import {useLocation, useNavigate, Link} from "react-router-dom";
import "../../assets/css/login.css"
import "../../assets/css/signup.css"
import "../../assets/css/main.css"
import logo from "../../assets/img/lock.png"

function MailAuth() {
    const location = useLocation();
    const navigate = useNavigate();
    const [Email, setEmail] = useState("");
    const [Code, setCode] = useState("");
    const [Error, setError] = useState("");

    useEffect(() => {
        setEmail(location.state.email);
        const email = location.state.email.toString()
        console.log(email)
        let body = {
            email: email
        }
        axios.post("/user-service/signup/sendMail", body)
            .then(response => console.log(response))
            .catch(error => console.log(error))
    },[])
    const onCodeHandler = (event) => {
        setCode(event.currentTarget.value);
    }

    const onSubmitHandler = (event) => {
        // 버튼만 누르면 리로드 되는것을 막아줌
        event.preventDefault();

        console.log('Email', Email);
        console.log('Code', Code);

        let body = {
            email: Email,
            code: Code,
        }
        MailAuthUser(body, setError, navigate);
    }
    return (
        <div className={"contentsWrap"}>
            <form onSubmit={onSubmitHandler}>
                <div className={"loginWindow"}>
                    <img src={logo} alt={""}/>
                    <input type="hidden" value={Email}/>
                    <input type={"text"} value={Code} onChange={onCodeHandler} placeholder={"코드를 입력해주세요."} className={"inlineToBlock"}/>
                    <br/>
                    <span className={"field-error"}>{Error}</span>
                    <button formAction={""} className={"inlineToBlock ordinaryLogin activatedLoginColor"}>
                        이메일 인증
                    </button>
                </div>
            </form>
            <Link to="/">
                <div className={"haveAccount"}>
                    <p>이메일이 도착하지 않나요?
                        <a className="noneunderline">홈화면 가기</a>
                    </p>
                </div>
            </Link>
        </div>
    )
}

export default MailAuth;