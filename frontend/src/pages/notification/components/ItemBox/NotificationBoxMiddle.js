import axios from "axios";
import "./NotificationBoxMiddle.css";
import "../../FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const NotificationBoxMiddle = (props) => {
  // 현재 선택된 아이콘을 관리하는 state

  const isRead = props.item.read;

  function readNotification() {
    const url =
      "http://localhost:8000/notification-server/read?notificationId=" +
      props.item.id.toString();

    console.log(url);
    axios.get(url).then((response) => {
      console.log(response.data.data);
    });
  }

  return (
    <div className="notificationboxmiddle" onClick={() => readNotification()}>
      <img
        className="notificationboxmiddle-profileImg"
        src="https://img.youtube.com/vi/N7iIvIV-ZCs/mqdefault.jpg"
      ></img>
      <p className="notificationboxmiddle-text-area">
        <span style={isRead ? { color: "gray" } : { color: "black" }}>
          {props.item.content}
        </span>
        <span className="notificationboxmiddle-createdAt">
          {props.item.createdAt}
        </span>
      </p>
    </div>
  );
};

export default NotificationBoxMiddle;
