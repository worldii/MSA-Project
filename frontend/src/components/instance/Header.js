function Header(accessToken){
    return {
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + accessToken
    }
}

export default Header;