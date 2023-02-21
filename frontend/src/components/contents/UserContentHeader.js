import {Menu} from "grommet";
import {AiOutlineEllipsis} from "react-icons/ai";
import React from "react";


export default function UserContentHeader (props) {

    const myPost = [
        {
            label: '삭제', onClick: () => {
            }
        },
        {
            label: '수정', onClick: () => {
            }
        },
        {
            label: '좋아요 수 숨기기 취소', onClick: () => {
            }
        },
        {
            label: '댓글 기능 해제', onClick: () => {
            }
        },
        {
            label: '공유 대상...', onClick: () => {
            }
        },
        {
            label: '링크 복사', onClick: () => {
            }
        }
    ]

    const notMyPost = [
        {
            label: "팔로우 취소", onClick: () => {}
        },
        {
            label: "게시물로 이동", onClick: () => {}
        },
        {
            label: '공유 대상...', onClick: () => {
            }
        },
        {
            label: '링크 복사', onClick: () => {
            }
        }
    ]

    return (
        <div style={{display: 'flex'}}>
            <img style={{borderRadius: '70%', width: 50, height: 50}}
                 src={props.user.profile} alt="사진"/>
            <p> {props.user.nickname}</p>
            {
                props.user.id === props.content.userId
                    ? <Menu style={{display: "inline-block", margin: 10, float: 'right', width: 50}}
                            items={myPost}><AiOutlineEllipsis/>
                    </Menu>
                    : <Menu style={{display: "inline-block", margin: 10, float: 'right', width: 50}}
                            items={notMyPost}><AiOutlineEllipsis/>
                    </Menu>
            }
        </div>
    );
}