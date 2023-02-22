import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Comment from "./Comment";
import styles from "./CommentList.module.scss";
import Commentlayout from "./Commentlayout";
const CommentList = ({ postId, refreshfunc }) => {
  //const postId = useParams().postId;
  let userId = 1;
  const [commentList, setCommentList] = useState([]);

  const refreshComment = async () => {
    getDetail();
  };

  const getDetail = () => {
    axios
      .get(`http://localhost:8000/content-query/comments/${postId}`)
      .then((response) => {
        // console.log("데이터 가져옴", response.data.data);
        setCommentList(response.data.data);
      });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8000/content-query/comments/${postId}`)
      .then((response) => {
        setCommentList(response.data.data);
      });
  }, []);

  const toggleLikeMutation = async (commentId, isLiked) => {
    if (isLiked === false) {
      axios
        .post(
          `http://localhost:8000/content-command/comments/like/${commentId}/${userId}`
        )
        .then((response) => {
          console.log("좋아요 켜짐" + response, " ", commentId);
        })
        .then((res) => {
          getDetail();
        });
    } else {
      axios
        .post(
          `http://localhost:8000/content-command/comments/unlike/${commentId}/${userId}`
        )
        .then((response) => {
          console.log("좋아요 꺼짐" + response, " ", commentId);
        })
        .then((res) => {
          getDetail();
        });
    }
  };

  const deleteFunc = async (commentId) => {
    axios
      .delete(`http://localhost:8000/content-command/comments/${commentId}`)
      .then((response) => {
        console.log("삭제", response);
      })
      .then((res) => {
        getDetail();
      });
  };

  return (
    <div>
      <div className={styles.commentListLayout}>
        {commentList.map((item, index) => (
          <div key={index}>
            <Commentlayout
              commentitem={item}
              toggleLikeMutation={toggleLikeMutation}
              deleteFunc={deleteFunc}
              refreshfunc={refreshComment}
            ></Commentlayout>
          </div>
        ))}
        <Comment postId={postId} refreshfunc={refreshComment}></Comment>
      </div>
    </div>
  );
};

export default CommentList;
