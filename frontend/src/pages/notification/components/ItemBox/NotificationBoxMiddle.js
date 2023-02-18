import "./NotificationBoxMiddle.css";
import "../../FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const NotificationBoxMiddle = (props) => {
  // 현재 선택된 아이콘을 관리하는 state

  return (
    <div className="notificationboxmiddle">
        <span>{props.item.content}</span>
        <span className="notificationboxmiddle-createdAt">{props.item.createdAt}</span>
    </div>
  );
};

export default NotificationBoxMiddle;
