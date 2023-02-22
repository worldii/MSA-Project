import axios from "axios";

export async function MailAuthUser(data, setError, history) {
    console.log(data)
    await axios.post('/user-service/signup/mailAuth', data)
        .then(response => {
                console.log(response.data);
                setError("");
                if (response.data === "NotExistsEmail" || response.data === "WrongCode") {
                    setError(response.data);
                } else {
                    alert("이메일 인증이 성공하였습니다.");
                    const userId = parseInt(response.data.toString())

                    axios.post("/follow-service/user", JSON.stringify({"id": userId}),
                        {
                            headers:
                                {"Content-Type": 'application/json'}
                        })
                        .then(res => console.log(res))
                        .catch(err => console.log(err))
                    history("/")
                }
            }
        )
        .catch(error => console.log(error));
}