import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Comment from './Comment';
import styles from './CommentList.module.scss';
import Commentlayout from './Commentlayout';
const CommentList = () => {
  const postId = useParams().postId;
  const [commentList, setCommentList] = useState([]);
  const [isLiked, setIsLiked] = useState(false);

  const refreshComment = async (id) => {
    getDetail();
  };

  const getDetail = () => {
    axios.get(`/comments/${postId}`).then((response) => {
      console.log(response.data.data);
      setCommentList(response.data.data);
    });
  };

  useEffect(() => {
    axios.get(`/comments/${postId}`).then((response) => {
      // console.log(response.data.data);
      setCommentList(response.data.data);
    });
  }, []);

  const toggleLikeMutation = async (commentId, isLiked) => {
    let userId = 1;
    if (isLiked === false) {
      axios.post(`/comments/like/${commentId}/${userId}`).then((response) => {
        console.log(response);
        getDetail();
      });
    } else {
      axios.post(`/comments/unlike/${commentId}/${userId}`).then((response) => {
        console.log(response);
        getDetail();
      });
    }
  };

  const deleteFunc = async (commentId) => {
    axios.delete(`/comments/${commentId}`).then((response) => {
      console.log(response);
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
            ></Commentlayout>
          </div>
        ))}
        <Comment postId={postId} refreshfunc={refreshComment}></Comment>
      </div>
    </div>
  );
};

export default CommentList;
