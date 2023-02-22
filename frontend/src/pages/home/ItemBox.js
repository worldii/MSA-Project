import "./ItemBox.css";
import "./FontAwesome.js";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styled from "styled-components";
import { Link } from "react-router-dom";
import ItemBoxTop from "./components/ItemBox/ItemBoxTop.js";
import ItemBoxMiddle from "./components/ItemBox/ItemBoxMiddle.js";
import ItemBoxBottom from "./components/ItemBox/ItemBoxBottom.js";
import Comment from "../../components/comments/Comment";
import CommentList from "../../components/comments/CommentList";
import React, { useEffect, useState } from "react";
import { IoClose } from "react-icons/io5";
import Modal from "react-modal";
import styles from "./ItemBox.module.scss";
import axios from "axios";

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
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const refreshfunc = () => {
    setModalIsOpen(true);
  };
  const refreshfunc2 = () => {};
  const onSubmit2 = (e) => {
    e.preventDefault();
    refreshfunc();
  };
  return (
    <ItemWrap>
      {modalIsOpen && (
        <Modal isOpen={modalIsOpen} className={styles.modalOverlay}>
          <div className={styles.modalContent}>
            <div className={styles.modalTitle}>
              <div className={styles.modalTitleLikes}>
                <CommentList
                  postId={props.item.contentId}
                  refreshfunc={refreshfunc2}
                ></CommentList>
              </div>
              <div className={styles.modalTitleIcon}>
                <IoClose
                  onClick={() => {
                    setModalIsOpen(false);
                  }}
                ></IoClose>
              </div>
            </div>
          </div>
        </Modal>
      )}
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
        <div className="itemboxbottom-toComment" onClick={onSubmit2}>
          댓글 n개 모두 보기
        </div>
        <Comment
          postId={props.item.contentId}
          refreshfunc={refreshfunc}
        ></Comment>
      </div>
    </ItemWrap>
  );
};

export default ItemBox;
