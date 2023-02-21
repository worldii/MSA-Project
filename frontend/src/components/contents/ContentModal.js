import React, { useState } from 'react';
import Modal from 'react-modal';
import {TextInput, Button, Video, Image, Menu, Box} from 'grommet';
import { AiOutlineHeart, AiFillHeart, AiOutlineEllipsis } from "react-icons/ai";
import {BsBookmark, BsAspectRatio, BsBoxArrowUpRight, BsArrowReturnLeft, BsFillBookmarkFill} from "react-icons/bs";
import {Slide} from "react-slideshow-image";
import UserContentHeader from "./UserContentHeader";


export default function ContentModal(props) {
    const [modalIsOpen, setModalIsOpen] = useState(false);
    return (
        <>
            <img onClick={() => setModalIsOpen(true)}
                 style= {{height: 200, width: 200}}
                 src={props.content.imageUrl[0]} alt="사진"/>
            <Modal isOpen={modalIsOpen} onRequestClose={()=> setModalIsOpen(false)}
                   style={{
                       overlay: {
                           position: 'fixed',
                           top: 0, left: 0,
                           bottom: 0, right: 0,
                           backgroundColor: 'rgba(255, 255, 255, 0)',
                           zIndex: 10
                       }, content: {
                           backgroundColor: 'white',
                           overflow: 'auto',
                           top: '10%', left: '2%', right: '2%', bottom: '20%',
                           WebkitOverflowScrolling: 'touch',
                           outline: 'none', borderRadius: '10px', zIndex: 10, padding: '20px'
                       }
                   }}
            >
                <div style={{display: 'flex', overflow: 'hidden', justifyContent: 'center', height: '100%'}}>
                    <Box style={{width: "100%", justifyContent: 'center', backgroundColor: 'black', marginBottom: 10}}>
                        <Slide autoplay={false} infinite={false}>
                            {props.content?.imageUrl.map(image =>{
                                return (
                                    <div className="each-slide-effect">
                                        <img src={image}
                                             alt="사진"
                                             style={{width: "100%", height: "80%"}}/>
                                    </div>
                                )})
                            }
                        </Slide>
                    </Box>
                    <div style={{marginLeft: 10, width: "70%", height: "auto"}}>
                        <UserContentHeader user={props.user} content={props.content}/>
                        <hr/>
                        <div className="modal-content" style={{height: "55%", overflowY: 'auto'}}>
                            댓글

                        </div>
                        <hr/>
                        <div style={{display: 'flex'}}>
                            {   props.isLiked === false
                                ? <Button><AiOutlineHeart size="25" style={{marginRight: 10}} /></Button>
                                : <Button><AiFillHeart size="25" style={{marginRight: 10}} /></Button>
                            }
                            <Button><BsAspectRatio size="25" style={{marginRight: 10}}/></Button>
                            <Button><BsBoxArrowUpRight size="25" style={{marginRight: 10}}/></Button>
                            {
                                props.isBookmarked === false
                                    ? <Button><BsBookmark size="25" style={{marginRight: 10}}/></Button>
                                    : <Button><BsFillBookmarkFill size="25" style={{marginRight: 10}}/></Button>
                            }
                        </div>

                        <div style={{marginBottom: 2}}>
                            <p style={{fontWeight: 'bold', fontSize: 14}}>좋아요 {props.like}개</p>
                            <p style={{fontSize: 10}}>{props.date}</p>
                            <hr/>
                        </div>
                        <div style={{display: 'flex', marginRight: 1}}>
                            <TextInput placeholder="댓글 달기..." plain={true} size="xsmall"
                            ></TextInput>
                            <Button color="grey" size="small"><BsArrowReturnLeft /></Button>
                        </div>
                    </div>
                </div>
            </Modal>
        </>
    )
}