import axios from "axios";
import { memo, useCallback, useEffect, useState, useTransition } from "react";
import "./FollowerBoxMiddle.css";
import "../../FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const NotificationBoxMiddle = (props) => {
  const url = "/user-service/user/id/" + props.item.id;
  //const url = "/user-service/user/id/1";
  const [nickname, setNickname] = useState("");
  const [name, setName] = useState("");

  useEffect(() => {
    axios.get(url).then((response) => {
      setNickname(response.data.nickname);
      setName(response.data.name);
    });
  }, []);

  return (
    <div className="notificationboxmiddle">
      <img
        className="notificationboxmiddle-profileImg"
        src="https://img.youtube.com/vi/N7iIvIV-ZCs/mqdefault.jpg"
      ></img>
      <div className="notificationboxmiddle-textarea">
        <span className="notificationboxmiddle-nickname">{nickname}</span>
        <span className="notificationboxmiddle-name">{name}</span>
      </div>
    </div>
  );
};

export default NotificationBoxMiddle;
