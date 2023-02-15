import React from 'react';
import LikesLayout from './LikesLayout';

function LikesList({ likesList }) {
  console.log(likesList);
  return (
    <div>
      {likesList.map((item, index) => (
        <div key={index}>
          <LikesLayout likesItem={item}></LikesLayout>
        </div>
      ))}
    </div>
  );
}
export default LikesList;
