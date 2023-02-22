import axios from "axios";
import "./FollowerBoxMiddle.css";
import "../../FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const NotificationBoxMiddle = (props) => {
  // 현재 선택된 아이콘을 관리하는 state

  return (
    <div className="notificationboxmiddle">
      <span>{props.item.id}</span>
    </div>
  );
};

export default NotificationBoxMiddle;
