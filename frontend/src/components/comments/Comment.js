import React from "react";
import styles from "./Comment.module.scss";
import { useState } from "react";
import axios from "axios";

const Comment = ({ postId, refreshfunc }) => {
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
      .post(`http://localhost:8000/content-command/comments/${postId}`, body)
      .then((response) => {
        console.log(response);
        setComment("");
      })
      .then((res) => {
        refreshfunc(postId);
      });
  };

  return (
    <div>
      <form className={styles.formLayout} onSubmit={onSubmit}>
        <div className={styles.commentLayout}>
          <textarea
            className={styles.comment}
            placeholder="댓글 달기..."
            onChange={handleClickComment}
            value={comment}
          ></textarea>
          <button className={styles.button} onClick={onSubmit}>
            게시
          </button>
        </div>
      </form>
    </div>
  );
};

export default Comment;
