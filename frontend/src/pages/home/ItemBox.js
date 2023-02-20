import "./ItemBox.css";
import "./FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styled from "styled-components";
import { Link } from "react-router-dom";
import ItemBoxTop from "./components/ItemBox/ItemBoxTop.js";
import ItemBoxMiddle from "./components/ItemBox/ItemBoxMiddle.js";
import ItemBoxBottom from "./components/ItemBox/ItemBoxBottom.js";

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

const ItemBox = (props) => {
  // 현재 선택된 아이콘을 관리하는 state
  // console.log(props.item);
  return (
    <ItemWrap>
      <ItemBoxTop id={props.item.contentId} createdAt={props.item.createdAt} />
      {props.item.imageUrl !== null && (
        <img src={props.item.imageUrl[0].url} alt="" />
      )}
      <div>
        <ItemBoxMiddle
          likes={props.item.likes}
          id={props.item.contentId}
          text={props.item.text}
        />
        <ItemBoxBottom />
      </div>
    </ItemWrap>
  );
};

export default ItemBox;
