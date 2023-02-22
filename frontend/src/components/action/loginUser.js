import axios from "axios";
import EventSourceObject from "../instance/EventSource";
import header from "../instance/Header";
const clearFunc = () => {
  window.location.reload();
};

export async function loginUser(
  data,
  setEmailError,
  setPasswordError,
  history
) {
  const eventSource = new EventSourceObject();
  await axios
    .post("/user-service/user/login", data)
    .then((response) => {
      console.log(response.data);
      setEmailError("");
      setPasswordError("");
      if (response.data === "NotFoundUser") {
        setEmailError("사용자를 찾을 수 없습니다.");
        history("/");
      } else if (response.data === "WrongPassword") {
        setPasswordError("비밀번호가 일치하지 않습니다.");
        history("/");
      } else if (response.data === "NotEmailAuthUser") {
        history("/signup/mailAuth", { state: { email: data.email } });
      }
      const accessToken = response.data["accessToken"];
      const refreshToken = response.data["refreshToken"];
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      axios
        .get("/user-service/accessToken/get-pk", {
          headers: header(accessToken),
        })
        .then((res) => {
          eventSource.subscribe(res.data);
        })
        .catch((err) => console.log(err));

      clearFunc();
    })
    .catch((error) => console.log(error));
}
