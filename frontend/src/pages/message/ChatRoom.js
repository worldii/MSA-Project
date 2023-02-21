import {useParams} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import axios from "axios";
import * as StompJS from "@stomp/stompjs";

const ChatRoom = () => {
    const { userId, chatroomId } = useParams()

    const [messages, setMessages] = useState([])
    const [chatMessage, setChatMessage] = useState('')
    const [like, setLike] = useState({})
    const stompClient = useRef(null)

    const connect = () => {
        stompClient.current = new StompJS.Client({
            brokerURL: 'ws://localhost:8000/chat-service/chat',
            onConnect: () => {
                console.log('good')
                subscribe()
                subscribeUser()
            }
        })
        stompClient.current.activate()
    }

    const disconnect = () => {
        stompClient.current.deactivate()
    }

    const subscribe = () => {
        stompClient.current.subscribe(`/sub/${chatroomId}`, (body) => {
            const message = JSON.parse(body.body).data
            setMessages(prev => [...prev, message])
        })
    }

    const subscribeUser = () => {
        stompClient.current.subscribe(`/sub/${userId}`, (body) => {
            setLike(prev => [...prev, !like])
        })
    }

    const publish = (message) => {
        stompClient.current.publish({
            destination: '/pub/messages',
            body: JSON.stringify({
                chatroomId: chatroomId,
                userId: userId,
                text: message
            }),
        })
        setChatMessage('')
    }

    const publishLike = (messageId, userId) => {
        stompClient.current.publish({
            destination: '/pub/messages/like',
            body: JSON.stringify({
                userId: userId,
                messageId: messageId
            }),
        })
    }

    useEffect(() => {
        connect()
        return () => disconnect()
    }, [])

    const handleChangeChatMessage = (e) => {
        setChatMessage(e.target.value)
    }

    const findChatMessages = () => {
        axios.get(`/chat-service/chatrooms/${userId}/${chatroomId}`)
            .then(res => {
                console.log(res.data)
                setMessages(res.data?.messages || [])
            })
    }

    useEffect(() => {
        findChatMessages()
    }, [])

    return (
        <div>
            <h1>Direct message</h1>
            <div style={{overflow: 'scroll', width: '100%', height: '500px', border:'solid 1px'}}>
                {messages.map(message => {
                    const { messageId, text, userId, createdAt, isLiked } = message
                    return (
                        <div>{`${userId} -> ${text} ${createdAt} `}
                            {
                                isLiked === true
                                    ? <button onClick={() => publishLike(messageId, userId)}>좋아요 취소</button>
                                    : <button onClick={() => publishLike(messageId, userId)}>좋아요</button>
                            }
                        </div>
                    )
                })}
            </div>
            <input type={'text'} onChange={handleChangeChatMessage} value={chatMessage} />
            <button onClick={() => publish(chatMessage)}>보내기</button>
        </div>
    )
}

export default ChatRoom