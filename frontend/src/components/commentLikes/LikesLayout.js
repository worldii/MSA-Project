import React from 'react';
import styles from './LikesLayout.module.scss';
import imageProfile from '../../images/profile.svg';

function LikesLayout({ likesItem }) {
  console.log(likesItem);
  const userProfileUrl = likesItem?.profileUrl;
  return (
    <div className={styles.layout}>
      <div className={styles.profileLayout}>
        {userProfileUrl === undefined || userProfileUrl === null ? (
          <img src={imageProfile} alt="" />
        ) : (
          <img src={userProfileUrl} alt="" />
        )}
      </div>
      <div className={styles.contentLayout}>
        <div className={styles.userName}>{likesItem.username}</div>
        <div className={styles.fullNmae}>{likesItem.fullName}</div>
      </div>
    </div>
  );
}
export default LikesLayout;
