import React, { useState } from 'react';
import 'react-slideshow-image/dist/styles.css';
import axios from "axios";
import { Slide } from 'react-slideshow-image';
import { TextInput, Button, Box } from 'grommet';
import { AiOutlineHeart, AiFillHeart } from "react-icons/ai";
import { BsBookmark, BsAspectRatio, BsBoxArrowUpRight, BsArrowReturnLeft, BsFillBookmarkFill } from "react-icons/bs";
import UserContentHeader from "./UserContentHeader";

export default function ContentFeedType(props) {
    const userId = 1
    const userName = ""
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        addComment(userId, userName, "", data.get('description'));
    }

    const addComment = (userId, userName, profileUrl, description) => {
        axios.post(`/content-command/comments/${props.content.id}`, {
                userId, userName, profileUrl, description
            }
        ).then()
    }

    return (
        <div style={{border: '1px solid grey', width: 500, borderRadius: '1%', height: 800}}>
            <UserContentHeader username={props.username}
                               profile={props.profile}
                               isMyPost={props.isMyPost}
                               isFollowed={props.isFollowed}
            />
            <Box style={{width: 500, height: "80%", justifyContent: 'center', backgroundColor: 'black', marginBottom: 10}}>
                <Slide autoplay={false}>
                    {props.images.map(image =>{
                        return (
                            <div className="each-slide-effect">
                                <img src={`${image}`}
                                     style={{width: 500, height: "80%"}}/>
                            </div>
                        )})
                    }
                </Slide>
            </Box>
            <div style={{display: 'inline-block', width: 490}}>
                <div style={{display: 'flex'}}>
                    {   props.isLiked === false
                        ? <Button><AiOutlineHeart size="25" style={{marginRight: 10}} /></Button>
                        : <Button><AiFillHeart size="25" style={{marginRight: 10}} /></Button>
                    }
                    <Button>
                        <BsAspectRatio size="25" style={{marginRight: 10}}/>
                    </Button>
                    <Button>
                        <BsBoxArrowUpRight size="25" style={{marginRight: 10}}/>
                    </Button>
                    {
                        props.isBookmarked === false
                            ? <Button><BsBookmark size="25" style={{marginRight: 10}}/></Button>
                            : <Button><BsFillBookmarkFill size="25" style={{marginRight: 10}}/></Button>
                    }
                </div>
                <hr/>
                <p style={{fontWeight: 'bold', fontSize: 14, lineHeight: 0.01, float:"left"}}>좋아요 {props.likes}</p>
                <p style={{fontSize: 10 , lineHeight: 0.1, float:"left", marginLeft: 5}}>   {props.date}</p>
                <br/>
                <br/>
                <div style={{display: 'flex', marginRight: 1}} onSubmit={handleSubmit}>
                    <TextInput placeholder="댓글 달기..." plain={true} name="description" label="description" size="xsmall"/>
                    <Button color="grey" size="small" type="submit">
                        <BsArrowReturnLeft />
                    </Button>
                </div>
            </div>
        </div>
    )
}