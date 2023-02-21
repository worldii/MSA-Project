function Header(accessToken){
    return {
        'content-type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem(accessToken)
    }
}

export default Header;