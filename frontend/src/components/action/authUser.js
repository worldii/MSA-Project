import axios from "axios";

<<<<<<< HEAD:frontend/auth-server/action/authUser.js
export async function authUser(
  data,
  setEmailError,
  setNicknameError,
  setPhoneError,
  history
) {
  await axios
    .post("searchPassword/authUser", data)
    .then((response) => {
      console.log(response.data);
      setEmailError("");
      setNicknameError("");
      setPhoneError("");
      if (response.data === "NotExistsUser") {
        setEmailError("존재하지 않는 사용자입니다.");
      } else if (response.data === "WrongNickname") {
        setNicknameError("닉네임이 다릅니다.");
      } else if (response.data === "WrongPhone") {
        setPhoneError("전화번호가 다릅니다.");
      } else {
        alert("새로운 비밀번호를 사용하신 이메일로 보내드렸습니다.");
        history.push("/");
      }
    })
    .catch((error) => console.log(error));
}
=======
export async function authUser(data, setEmailError, setNicknameError, setPhoneError,history) {
    await axios.post("/user-service/searchPassword/authUser", data)
        .then(response => {
            console.log(response.data);
            setEmailError("")
            setNicknameError("")
            setPhoneError("")
            if (response.data === "NotExistsUser") {
                setEmailError("존재하지 않는 사용자입니다.");
            } else if (response.data === "WrongNickname") {
                setNicknameError("닉네임이 다릅니다.");
            } else if (response.data === "WrongPhone") {
                setPhoneError("전화번호가 다릅니다.");
            }else{
                alert("새로운 비밀번호를 사용하신 이메일로 보내드렸습니다.");
                history("/");
            }

        })
        .catch(error => console.log(error))
}
>>>>>>> a7f73cb194ac80cb01f3d21e2a07fdaa4f794ce1:frontend/src/components/action/authUser.js
