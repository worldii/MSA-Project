import "./ItemBoxBottom.css";
import "../../FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState } from "react";
import axios from "axios";

const ItemBoxBottom = ({ item, refreshfunc }) => {
  console.log("item", item);
  const data = item.item;
  // 현재 선택된 아이콘을 관리하는 state
  const [comment, setComment] = useState("");
  const handleClickComment = (e) => {
    setComment(e.currentTarget.value);
  };

  const onSubmit = (event) => {
    event.preventDefault();
    let body = {
      description: comment,
      userId: 1,
    };
    axios
      .post(`http://localhost:8000/comments/${data.contentId}`, body)
      .then((response) => {
        console.log(response);
        setComment("");
        refreshfunc(data.contentId);
      });
  };

  return (
    <div className="itemboxbottom">
      <div className="itemboxbottom-toComment">댓글 n개 모두 보기</div>
      <form>
        <input
          type="text"
          placeholder="댓글 달기..."
          className="itemboxbottom-input"
          onChange={handleClickComment}
        ></input>
        <button onClick={onSubmit}>게시</button>{" "}
      </form>
    </div>
  );
};

export default ItemBoxBottom;
