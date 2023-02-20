import "./NotificationBox.css";
import "./FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styled from "styled-components";
import { Link } from "react-router-dom";
import NotificationBoxMiddle from "./components/ItemBox/NotificationBoxMiddle.js";

const ItemWrap = styled.div`
  width: 480px;
  height: auto;
  color: black;
  background-color: white;
  border-bottom: 1px solid lightgray;
  text-align: left;
  text-decoration: none;
  display: inline-block;
`;

const NotificationBox = (props) => {
  // 현재 선택된 아이콘을 관리하는 state

  return (
    <ItemWrap>
      <Link to="../" className="notificationLink">
        <NotificationBoxMiddle item={props.item} />
      </Link>
    </ItemWrap>
  );
};

export default NotificationBox;
