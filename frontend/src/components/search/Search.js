import React, {useEffect, useState} from "react";
import {Box, Button, Grommet, TextInput} from "grommet";
import axios from "axios";

export default function SearchBar() {
    const [value, setValue] = React.useState('');
    const onChange = (event) => {
        setValue(event.target.value);
        findKeyword(event.target.value)
    }

    const [hashtag, setHashtag] = useState("")
    const [suggestions, setSuggestions] = React.useState([])

    const findKeyword = async  (keyword) => {
        const response = await axios.get(`/search-service/search?q=${keyword}`)
        setHashtag(response?.data?.hashtagResponse || [])
        let suggestionList = []
        if (response?.data?.userResponse) {
            for (const u of response?.data?.userResponse) {
                const user = await findUserInfo(u.userId)
                suggestionList.push(user)
            }
        }
        setSuggestions(suggestionList)
    }

    const findUserInfo = async (userId) => {
        const response = await axios.get(`/user-service/user/${userId}`)
        return response?.data
    }

    useEffect(() => {
        console.log(suggestions)
    }, [suggestions])

    return (
        <>
            <Grommet>
                <Box fill align="center" justify="start" pad="large">
                    <Box width="medium" direction="row">
                            <TextInput fill={true}
                                value={value}
                                onChange={onChange}
                            />
                        <Button fill="horizontal">검색</Button>
                    </Box>
                    <Box>
                        <p>결과</p>
                        <Button href={`/search/tags/${hashtag.name}`}>#{hashtag?.name} {hashtag?.count} </Button>
                        {
                            suggestions.map((suggestion) =>
                                <Button>{suggestion?.userId} {suggestion?.name} {suggestion?.nickname}</Button>
                            )
                        }
                    </Box>
                </Box>

            </Grommet>
        </>
    )
}