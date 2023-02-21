import {Link, useParams} from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";


const ChatRoomList = () => {
    const { userId } = useParams()

    const [chatRooms, setChatRooms] = useState([])

    const findChatrooms = () => {
        axios.get(`/chat-service/chatrooms/user/${userId}`)
            .then(res => {
                setChatRooms(res.data.chatrooms || [])
            })
    }

    const createChatrooms = () => {
        axios.post(`/chat-service/chatrooms/${userId}/2`)
            .then(res => {
                findChatrooms()
            })
    }

    useEffect(() => {
        findChatrooms()
    }, [])

    return (
        <div>
            <h1>채팅 목록</h1>
            {chatRooms.map(chatRoom => {
                const {chatroomId, anotherId, username, anotherImage, latestMessage, latestMsgTimestamp, latestMsgDate } = chatRoom

                return (
                    <div key={chatroomId}>
                        <img src={anotherImage} alt={username}/>
                        {` | ${latestMessage} | ${latestMsgDate}`}
                        <Link to={`/chatroom/${userId}/${chatroomId}`}>
                            <button>들어가기</button>
                        </Link>
                    </div>
                )
            })}
            <button onClick={createChatrooms}>채팅방 생성</button>
        </div>
    )
}

export default ChatRoomList