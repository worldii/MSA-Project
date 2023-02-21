import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import ContentModal from "../contents/ContentModal";

export default function HashTagSearch() {
    const { hashTag } = useParams()
    const [contents, setContents] = useState([])
    const [contentIds, setContentIds] = useState([])

    const [me, setMe] = useState({
        id: 1,
        profileImage: "https://ssl.pstatic.net/sstatic/search/mobile/img/ico_common_ban2_cdf26fbe.png",
        name: "asdf",
        nickname: "hello hi"
    })

    const getContentsByHashTag = async () => {
        const response = await axios.get("/search-service/contents?q=" + hashTag)
        if (response?.data) {
            setContentIds(response?.data.map(c => c.contentId ))
        }
    }

    const findContent = async (contentIds) => {
        console.log(contentIds)
        const response = await axios.get(`/content-query/contents/all`,
            {params: {ids: contentIds.join(',')}}
        )
        if (response?.data) {
            console.log(response.data)
            setContents(response?.data)
        }
    }

    useEffect(() => {
        getContentsByHashTag()
    }, [])

    useEffect(() => {
        if (contentIds.length) {
            findContent(contentIds)
            // getContentsByIds()
        }
    }, [contentIds])

    return (
        <div>
            <h1>#{hashTag}</h1>
            <h3>{contentIds.length}</h3>
            <hr/>
            <div style={{display: "grid",
                gridTemplateColumns: "1fr 1fr 1fr",
                gridTemplateRows: "1fr",
                padding: "30px",
                width: "400px"}}>
                {
                    contents.length &&
                        contents.map(content => {
                        return (
                            <div style={{margin: "10px", height: "200px"}}>
                                <ContentModal content={content}
                                              user={me}
                                              isLiked={false}
                                              isBookmarked={true}/>
                            </div>
                        )
                })}

            </div>
        </div>
    )
}