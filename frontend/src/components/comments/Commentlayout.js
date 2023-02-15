import React, { useEffect } from "react";
import styles from "./Commentlayout.module.scss";
import { CiHeart } from "react-icons/ci";
import imageProfile from "../../assets/profile.svg";
import { useState } from "react";
import { IoClose } from "react-icons/io5";
import Modal from "react-modal";
import LikesList from "../CommentLikes/LikesList";
import axios from "axios";

function Commentlayout({ commentitem, toggleLikeMutation, deleteFunc }) {
  const description = commentitem.description;
  const userName = commentitem.userName;
  const commentId = commentitem?.id;
  const likes = commentitem.likes;
  const userProfileUrl = commentitem?.profileUrl;

  const [isLiked, setIsLiked] = useState(false);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [likesList, setLikesList] = useState([]);

  const showLikesList = async (commentId) => {
    axios.get(`/comments/like/${commentId}`).then((response) => {
      console.log(response.data.data);
      setLikesList(response.data.data);
      setModalIsOpen(true);
    });
  };

  useEffect(() => {
    let userId = 1;
    axios.get(`/comments/isLiked/${commentId}/${userId}`).then((response) => {
      console.log(response.data.data);
      setIsLiked(JSON.parse(response.data.data));
    });
  }, [commentId]);

  return (
    <div>
      {modalIsOpen && (
        <Modal isOpen={modalIsOpen} className={styles.modalOverlay}>
          <div className={styles.modalContent}>
            <div className={styles.modalTitle}>
              <div className={styles.modalTitleLikes}>좋아요</div>
              <div className={styles.modalTitleIcon}>
                <IoClose
                  onClick={() => {
                    setModalIsOpen(false);
                    setLikesList(null);
                  }}
                ></IoClose>
              </div>
            </div>
            <hr></hr>
            {likesList && <LikesList likesList={likesList}></LikesList>}
          </div>
        </Modal>
      )}
      <div className={styles.layout}>
        <div className={styles.layout2}>
          <div className={styles.profileLayout}>
            {userProfileUrl === undefined || userProfileUrl === null ? (
              <img src={imageProfile} alt="" />
            ) : (
              <img src={userProfileUrl} alt="" />
            )}
          </div>
          <div className={styles.contentLayout}>
            <div className={styles.content}>
              <div className={styles.userName}>{userName}</div>
              <div className={styles.description}>{description}</div>
            </div>
            <div className={styles.detail}>
              <div
                className={styles.likes_list}
                onClick={(e) => {
                  e.preventDefault();
                  showLikesList(commentitem?.id);
                }}
              >
                좋아요 {likes}개
              </div>

              <div
                className={styles.delete}
                onClick={(e) => {
                  e.preventDefault();
                  deleteFunc(commentitem?.id);
                }}
              >
                삭제
              </div>
            </div>
          </div>
        </div>
        <div
          className={styles.like_box}
          onClick={(e) => {
            e.preventDefault();
            toggleLikeMutation(commentitem?.id, isLiked);
            setIsLiked(!isLiked);
          }}
        >
          <CiHeart size={15} color={isLiked === true ? "#ff6f8b" : "gray"} />
        </div>
      </div>
    </div>
  );
}

export default Commentlayout;
