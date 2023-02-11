import React from "react";

import styles from "./Profile.module.scss";
import imageProfile from "../assets/profile.svg";

const Profile = ({ User }) => {
  const userMockup = {
    userName: "jongha",
    fullName: "zzang",
    profileUrl: null,
  };
  return (
    <div className={styles.ProfileLayout}>
      <div className={styles.profilePicture}>
        {userMockup.profileUrl === undefined ||
        userMockup.profileUrl === null ? (
          <img src={imageProfile} alt="" />
        ) : (
          <img src={userMockup.profileUrl} alt="" />
        )}
      </div>
      <div className={styles.nameLayout}>
        <div className={styles.userName}>{userMockup.userName}</div>
        <div lassName={styles.fullName}>{userMockup.fullName}</div>
      </div>
    </div>
  );
};

export default Profile;
